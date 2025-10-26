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
package org.openrewrite.docker.internal;

import org.antlr.v4.runtime.ParserRuleContext;
import org.jspecify.annotations.Nullable;
import org.openrewrite.FileAttributes;
import org.openrewrite.docker.internal.grammar.DockerfileParser;
import org.openrewrite.docker.internal.grammar.DockerfileParserBaseVisitor;
import org.openrewrite.docker.tree.Dockerfile;
import org.openrewrite.docker.tree.Space;
import org.openrewrite.internal.EncodingDetectingInputStream;
import org.openrewrite.marker.Markers;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.openrewrite.Tree.randomId;

public class DockerfileParserVisitor extends DockerfileParserBaseVisitor<Dockerfile> {
    private final Path path;
    private final String source;
    private final Charset charset;
    private final boolean charsetBomMarked;

    @Nullable
    private final FileAttributes fileAttributes;

    private int cursor = 0;

    public DockerfileParserVisitor(Path path, @Nullable FileAttributes fileAttributes, EncodingDetectingInputStream source) {
        this.path = path;
        this.fileAttributes = fileAttributes;
        this.source = source.readFully();
        this.charset = source.getCharset();
        this.charsetBomMarked = source.isCharsetBomMarked();
    }

    @Override
    public Dockerfile.Document visitDockerfile(DockerfileParser.DockerfileContext ctx) {
        Space prefix = whitespace();
        List<Dockerfile.Instruction> instructions = new ArrayList<>();

        for (DockerfileParser.InstructionContext instructionCtx : ctx.instruction()) {
            Dockerfile.Instruction instruction = (Dockerfile.Instruction) visit(instructionCtx);
            if (instruction != null) {
                instructions.add(instruction);
            }
        }

        return new Dockerfile.Document(
                randomId(),
                path,
                prefix,
                Markers.EMPTY,
                charset.name(),
                charsetBomMarked,
                null,
                fileAttributes,
                instructions,
                whitespace()
        );
    }

    @Override
    public Dockerfile visitFromInstruction(DockerfileParser.FromInstructionContext ctx) {
        return new Dockerfile.From(
                randomId(),
                whitespace(),
                Markers.EMPTY,
                ctx.flags() != null ? convertFlags(ctx.flags()) : null,
                visitArgument(ctx.imageName()),
                ctx.AS() != null ? visitFromAs(ctx) : null
        );
    }

    private Dockerfile.From.As visitFromAs(DockerfileParser.FromInstructionContext ctx) {
        return new Dockerfile.From.As(
                randomId(),
                whitespace(),
                Markers.EMPTY,
                visitArgument(ctx.stageName())
        );
    }

    @Override
    public Dockerfile visitRunInstruction(DockerfileParser.RunInstructionContext ctx) {
        return new Dockerfile.Run(
                randomId(),
                whitespace(),
                Markers.EMPTY,
                ctx.flags() != null ? convertFlags(ctx.flags()) : null,
                visitCommandLine(ctx)
        );
    }

    private Dockerfile.CommandLine visitCommandLine(DockerfileParser.RunInstructionContext ctx) {
        Dockerfile.CommandForm form;
        if (ctx.execForm() != null) {
            form = visitExecFormContext(ctx.execForm());
        } else if (ctx.shellForm() != null) {
            form = visitShellFormContext(ctx.shellForm());
        } else {
            // heredoc - not implemented yet, treat as shell form
            form = new Dockerfile.ShellForm(randomId(), Space.EMPTY, Markers.EMPTY, emptyList());
        }

        return new Dockerfile.CommandLine(
                randomId(),
                Space.EMPTY,
                Markers.EMPTY,
                form
        );
    }

    private List<Dockerfile.Flag> convertFlags(DockerfileParser.FlagsContext ctx) {
        List<Dockerfile.Flag> flags = new ArrayList<>();
        for (DockerfileParser.FlagContext flagCtx : ctx.flag()) {
            flags.add(new Dockerfile.Flag(
                    randomId(),
                    whitespace(),
                    Markers.EMPTY,
                    flagCtx.flagName().getText(),
                    flagCtx.flagValue() != null ? visitArgument(flagCtx.flagValue()) : null
            ));
        }
        return flags;
    }

    private Dockerfile.ShellForm visitShellFormContext(DockerfileParser.ShellFormContext ctx) {
        return new Dockerfile.ShellForm(
                randomId(),
                Space.EMPTY,
                Markers.EMPTY,
                singletonList(visitArgument(ctx.text()))
        );
    }

    private Dockerfile.ExecForm visitExecFormContext(DockerfileParser.ExecFormContext ctx) {
        List<Dockerfile.Argument> args = new ArrayList<>();
        DockerfileParser.JsonArrayContext jsonArray = ctx.jsonArray();
        if (jsonArray.jsonArrayElements() != null) {
            for (DockerfileParser.JsonStringContext jsonStr : jsonArray.jsonArrayElements().jsonString()) {
                String value = jsonStr.getText();
                // Remove surrounding quotes
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                }
                Dockerfile.QuotedString qs = new Dockerfile.QuotedString(
                        randomId(),
                        whitespace(),
                        Markers.EMPTY,
                        value,
                        Dockerfile.QuotedString.QuoteStyle.DOUBLE
                );
                args.add(new Dockerfile.Argument(
                        randomId(),
                        Space.EMPTY,
                        Markers.EMPTY,
                        singletonList(qs)
                ));
            }
        }

        return new Dockerfile.ExecForm(
                randomId(),
                Space.EMPTY,
                Markers.EMPTY,
                args
        );
    }

    private Dockerfile.Argument visitArgument(ParserRuleContext ctx) {
        if (ctx == null) {
            return new Dockerfile.Argument(randomId(), Space.EMPTY, Markers.EMPTY, emptyList());
        }

        String text = ctx.getText();
        List<Dockerfile.ArgumentContent> contents = new ArrayList<>();

        // Simple implementation: treat as plain text for now
        // TODO: Parse environment variables and quoted strings properly
        Dockerfile.PlainText pt = new Dockerfile.PlainText(
                randomId(),
                whitespace(),
                Markers.EMPTY,
                text
        );
        contents.add(pt);

        return new Dockerfile.Argument(
                randomId(),
                Space.EMPTY,
                Markers.EMPTY,
                contents
        );
    }

    private Space whitespace() {
        int start = cursor;
        while (cursor < source.length() && Character.isWhitespace(source.charAt(cursor))) {
            cursor++;
        }
        if (start == cursor) {
            return Space.EMPTY;
        }
        return Space.format(source, start, cursor);
    }
}
