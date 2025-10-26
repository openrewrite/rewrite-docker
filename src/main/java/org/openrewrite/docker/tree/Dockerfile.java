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
package org.openrewrite.docker.tree;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;
import org.jspecify.annotations.Nullable;
import org.openrewrite.*;
import org.openrewrite.docker.DockerfileVisitor;
import org.openrewrite.docker.internal.DockerfilePrinter;
import org.openrewrite.marker.Markers;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

public interface Dockerfile extends Tree {

    @SuppressWarnings("unchecked")
    @Override
    default <R extends Tree, P> R accept(TreeVisitor<R, P> v, P p) {
        return (R) acceptDockerfile(v.adapt(DockerfileVisitor.class), p);
    }

    default <P> @Nullable Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
        return v.defaultValue(this, p);
    }

    @Override
    default <P> boolean isAcceptable(TreeVisitor<?, P> v, P p) {
        return v.isAdaptableTo(DockerfileVisitor.class);
    }

    Space getPrefix();

    <D extends Dockerfile> D withPrefix(Space prefix);

    /**
     * Root node representing a complete Dockerfile or Containerfile
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class Document implements Dockerfile, SourceFile {
        @EqualsAndHashCode.Include
        UUID id;

        Path sourcePath;
        Space prefix;
        Markers markers;

        @With(AccessLevel.PRIVATE)
        String charsetName;

        boolean charsetBomMarked;

        @Nullable
        Checksum checksum;

        @Nullable
        FileAttributes fileAttributes;

        @Override
        public Charset getCharset() {
            return Charset.forName(charsetName);
        }

        @Override
        @SuppressWarnings("unchecked")
        public Document withCharset(Charset charset) {
            return withCharsetName(charset.name());
        }

        List<Instruction> instructions;
        Space eof;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitDocument(this, p);
        }

        @Override
        public <P> TreeVisitor<?, PrintOutputCapture<P>> printer(Cursor cursor) {
            return new DockerfilePrinter<>();
        }
    }

    /**
     * Base interface for all Dockerfile instructions
     */
    interface Instruction extends Dockerfile {
    }

    /**
     * FROM instruction - sets the base image
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class From implements Instruction {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        String keyword;

        @Nullable
        List<Flag> flags;

        Argument image;

        @Nullable
        As as;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitFrom(this, p);
        }

        @Value
        @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
        @With
        public static class As {
            @EqualsAndHashCode.Include
            UUID id;

            Space prefix;
            Markers markers;
            String keyword;
            Argument name;
        }
    }

    /**
     * RUN instruction - executes commands
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class Run implements Instruction {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        String keyword;

        @Nullable
        List<Flag> flags;

        CommandLine commandLine;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitRun(this, p);
        }
    }

    /**
     * COPY instruction - copies files from source to destination
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class Copy implements Instruction {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        String keyword;

        @Nullable
        List<Flag> flags;

        List<Argument> sources;
        Argument destination;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitCopy(this, p);
        }
    }

    /**
     * ARG instruction - defines a build argument
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class Arg implements Instruction {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        String keyword;

        Argument name;

        @Nullable
        Argument value;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitArg(this, p);
        }
    }

    /**
     * Command line that can be in shell or exec form
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class CommandLine implements Dockerfile {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        /**
         * Either ShellForm or ExecForm
         */
        CommandForm form;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitCommandLine(this, p);
        }
    }

    /**
     * Base for command forms
     */
    interface CommandForm extends Dockerfile {
    }

    /**
     * Shell form: CMD command param1 param2
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class ShellForm implements CommandForm {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        List<Argument> arguments;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitShellForm(this, p);
        }
    }

    /**
     * Exec form: CMD ["executable", "param1", "param2"]
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class ExecForm implements CommandForm {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        List<Argument> arguments;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitExecForm(this, p);
        }
    }

    /**
     * A flag like --platform=linux/amd64
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class Flag implements Dockerfile {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        String name;

        @Nullable
        Argument value;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitFlag(this, p);
        }
    }

    /**
     * An argument which can be plain text, quoted string, or environment variable
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class Argument implements Dockerfile {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        List<ArgumentContent> contents;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitArgument(this, p);
        }
    }

    /**
     * Content within an argument (text, quoted string, or variable reference)
     */
    interface ArgumentContent extends Dockerfile {
    }

    /**
     * Plain unquoted text
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class PlainText implements ArgumentContent {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        String text;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitPlainText(this, p);
        }
    }

    /**
     * Quoted string (single or double quotes)
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class QuotedString implements ArgumentContent {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        String value;
        QuoteStyle quoteStyle;

        public enum QuoteStyle {
            DOUBLE, SINGLE
        }

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitQuotedString(this, p);
        }
    }

    /**
     * Environment variable reference like $VAR or ${VAR}
     */
    @Value
    @EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
    @With
    class EnvironmentVariable implements ArgumentContent {
        @EqualsAndHashCode.Include
        UUID id;

        Space prefix;
        Markers markers;

        String name;
        boolean braced;

        @Override
        public <P> Dockerfile acceptDockerfile(DockerfileVisitor<P> v, P p) {
            return v.visitEnvironmentVariable(this, p);
        }
    }
}
