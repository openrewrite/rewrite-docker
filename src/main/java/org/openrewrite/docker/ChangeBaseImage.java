/*
 * Copyright 2025 the original author or authors.
 * <p>
 * Licensed under the Moderne Source Available License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://docs.moderne.io/licensing/moderne-source-available-license
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.docker;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jspecify.annotations.Nullable;
import org.openrewrite.ExecutionContext;
import org.openrewrite.Option;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.docker.tree.Dockerfile;
import org.openrewrite.docker.tree.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.singletonList;
import static org.openrewrite.Tree.randomId;

@Value
@EqualsAndHashCode(callSuper = false)
public class ChangeBaseImage extends Recipe {

    @Option(displayName = "Old image name",
            description = "The old image name to replace. Supports glob patterns.",
            example = "ubuntu:20.04")
    String oldImageName;

    @Option(displayName = "New image name",
            description = "The new image name to use.",
            example = "ubuntu:22.04")
    String newImageName;

    @Option(displayName = "Old platform",
            description = "Only change images with this platform. If null, matches any platform.",
            example = "linux/amd64",
            required = false)
    @Nullable
    String oldPlatform;

    @Option(displayName = "New platform",
            description = "Set the platform to this value. If null and oldPlatform is specified, removes the platform flag from matched images. If both oldPlatform and newPlatform are null, platform flags are preserved.",
            example = "linux/arm64",
            required = false)
    @Nullable
    String newPlatform;

    @Override
    public String getDisplayName() {
        return "Change Docker base image";
    }

    @Override
    public String getDescription() {
        return "Change the base image in a Dockerfile FROM instruction.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new DockerfileIsoVisitor<ExecutionContext>() {
            @Override
            public Dockerfile.From visitFrom(Dockerfile.From from, ExecutionContext ctx) {
                // Visit children first
                Dockerfile.From f = super.visitFrom(from, ctx);

                // Find the content containing the image name (PlainText or QuotedString)
                Dockerfile.ArgumentContent originalContent = f.getImage().getContents().stream()
                    .filter(content -> content instanceof Dockerfile.PlainText || content instanceof Dockerfile.QuotedString)
                    .findFirst()
                    .orElse(null);

                // Extract the text value from either PlainText or QuotedString
                String imageText;
                if (originalContent instanceof Dockerfile.PlainText) {
                    imageText = ((Dockerfile.PlainText) originalContent).getText();
                } else if (originalContent instanceof Dockerfile.QuotedString) {
                    imageText = ((Dockerfile.QuotedString) originalContent).getValue();
                } else {
                    return f;
                }

                if (!matchesGlob(imageText, oldImageName)) {
                    return f;
                }

                // Get the current platform flag value, if any
                String currentPlatform = getPlatformFlag(f);

                // Check if oldPlatform is specified and matches
                if (oldPlatform != null && !oldPlatform.equals(currentPlatform)) {
                    return f;
                }

                boolean imageChanged = !imageText.equals(newImageName);
                // Only consider platform changed if oldPlatform or newPlatform was explicitly set
                boolean shouldChangePlatform = oldPlatform != null || newPlatform != null;
                boolean platformChanged = shouldChangePlatform && !Objects.equals(currentPlatform, newPlatform);

                if (!imageChanged && !platformChanged) {
                    return f;
                }

                // Update image if needed
                Dockerfile.From result = f;
                if (imageChanged) {
                    Dockerfile.ArgumentContent newContent;
                    if (originalContent instanceof Dockerfile.PlainText) {
                        Dockerfile.PlainText pt = (Dockerfile.PlainText) originalContent;
                        newContent = new Dockerfile.PlainText(
                            randomId(),
                            pt.getPrefix(),
                            pt.getMarkers(),
                            newImageName
                        );
                    } else {
                        Dockerfile.QuotedString qs = (Dockerfile.QuotedString) originalContent;
                        newContent = new Dockerfile.QuotedString(
                            randomId(),
                            qs.getPrefix(),
                            qs.getMarkers(),
                            newImageName,
                            qs.getQuoteStyle()
                        );
                    }
                    Dockerfile.Argument newImageArg = f.getImage().withContents(singletonList(newContent));
                    result = result.withImage(newImageArg);
                }

                // Update platform flag if needed
                if (platformChanged) {
                    result = updatePlatformFlag(result, newPlatform);
                }

                return result;
            }
        };
    }

	private @Nullable String getPlatformFlag(Dockerfile.From from) {
        if (from.getFlags() == null) {
            return null;
        }

        for (Dockerfile.Flag flag : from.getFlags()) {
            if ("platform".equals(flag.getName()) && flag.getValue() != null) {
                for (Dockerfile.ArgumentContent content : flag.getValue().getContents()) {
					if (content instanceof Dockerfile.PlainText) {
						return ((Dockerfile.PlainText)content).getText();
					}
					if (content instanceof Dockerfile.QuotedString) {
						return ((Dockerfile.QuotedString)content).getValue();
					}
				}
            }
        }
        return null;
    }

    private Dockerfile.From updatePlatformFlag(Dockerfile.From from, @Nullable String platform) {
        List<Dockerfile.Flag> newFlags = new ArrayList<>();
        boolean platformFound = false;

        // Copy existing flags, updating or removing platform flag
        if (from.getFlags() != null) {
            for (Dockerfile.Flag flag : from.getFlags()) {
                if ("platform".equals(flag.getName())) {
                    platformFound = true;
                    if (platform != null) {
                        // Update existing platform flag
                        Dockerfile.Argument newValue = createPlatformValue(platform,
                            flag.getValue() != null ? flag.getValue().getPrefix() : Space.EMPTY,
                            from.getMarkers());
                        newFlags.add(flag.withValue(newValue));
                    }
                    // If platform is null, skip this flag (removes it)
                } else {
                    newFlags.add(flag);
                }
            }
        }

        // Add new platform flag if it wasn't found and platform is not null
        if (!platformFound && platform != null) {
            Dockerfile.Flag platformFlag = new Dockerfile.Flag(
                randomId(),
                from.getFlags() != null && !from.getFlags().isEmpty() ?
                    from.getFlags().get(0).getPrefix() : Space.SINGLE_SPACE,
                from.getMarkers(),
                "platform",
                createPlatformValue(platform, Space.EMPTY, from.getMarkers())
            );
            newFlags.add(0, platformFlag);
        }

        return from.withFlags(newFlags.isEmpty() ? null : newFlags);
    }

    private Dockerfile.Argument createPlatformValue(String platform, Space prefix, org.openrewrite.marker.Markers markers) {
        return new Dockerfile.Argument(
            randomId(),
            prefix,
            markers,
            singletonList(new Dockerfile.PlainText(
                randomId(),
                Space.EMPTY,
                markers,
                platform
            ))
        );
    }

    private boolean matchesGlob(String value, @Nullable String glob) {
        if (glob == null) {
            return false;
        }

        // If no glob patterns, do exact match
        if (!glob.contains("*") && !glob.contains("?")) {
            return value.equals(glob);
        }

        // Convert glob to regex by iterating through characters
        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < glob.length(); i++) {
            char c = glob.charAt(i);
            switch (c) {
                case '*':
                    regex.append(".*");
                    break;
                case '?':
                    regex.append(".");
                    break;
                // Escape regex special characters
                case '.':
                case '+':
                case '(':
                case ')':
                case '[':
                case ']':
                case '{':
                case '}':
                case '^':
                case '$':
                case '|':
                case '\\':
                    regex.append("\\").append(c);
                    break;
                default:
                    regex.append(c);
            }
        }

        return value.matches(regex.toString());
    }
}
