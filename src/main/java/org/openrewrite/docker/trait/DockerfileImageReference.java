/*
 * Copyright 2024 the original author or authors.
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
package org.openrewrite.docker.trait;

import lombok.Value;
import org.openrewrite.Cursor;
import org.openrewrite.SourceFile;
import org.openrewrite.internal.StringUtils;
import org.openrewrite.text.PlainText;
import org.openrewrite.trait.Reference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

@Value
public class DockerfileImageReference implements Reference {
    Cursor cursor;
    String value;

    @Override
    public Kind getKind() {
        return Kind.IMAGE;
    }

    public static class Provider implements Reference.Provider {
        public static final Pattern DOCKER = Pattern.compile("dockerfile", CASE_INSENSITIVE);
        public static final Pattern CONTAINER = Pattern.compile("containerfile", CASE_INSENSITIVE);
        public static final Pattern FROM = Pattern.compile("from", CASE_INSENSITIVE);

        @Override
        public boolean isAcceptable(SourceFile sourceFile) {
            if (sourceFile instanceof PlainText) {
                PlainText text = (PlainText) sourceFile;
                String fileName = text.getSourcePath().toFile().getName();
                ;
                return (DOCKER.matcher(fileName).find() || CONTAINER.matcher(fileName).find()) && FROM.matcher(text.getText()).find();
            }
            return false;
        }

        @Override
        public Set<Reference> getReferences(SourceFile sourceFile) {
            Cursor c = new Cursor(new Cursor(null, Cursor.ROOT_VALUE), sourceFile);
            String[] words = ((PlainText) sourceFile).getText()
                    .replaceAll("\\s*#.*?\\n", " ") // remove comments
                    .replaceAll("\".*?\"", " ") // remove string literals
                    .split("\\s+");

            Set<Reference> references = new HashSet<>();
            ArrayList<String> aliases = new ArrayList<>();
            for (int i = 0, wordsLength = words.length; i < wordsLength; i++) {
                if ("from".equalsIgnoreCase(words[i])) {
                    String image = words[i + 1].startsWith("--platform") ? words[i + 2] : words[i + 1];
                    if (!aliases.contains(image)) {
                        references.add(new DockerfileImageReference(c, image));
                    }
                } else if ("as".equalsIgnoreCase(words[i])) {
                    aliases.add(words[i + 1]);
                } else if (words[i].startsWith("--from") && words[i].split("=").length == 2) {
                    String image = words[i].split("=")[1];
                    if (!aliases.contains(image) && !StringUtils.isNumeric(image)) {
                        references.add(new DockerfileImageReference(c, image));
                    }
                }
            }

            return references;
        }
    }
}
