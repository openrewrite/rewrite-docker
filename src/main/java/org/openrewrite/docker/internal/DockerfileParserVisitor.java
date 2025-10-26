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
import org.antlr.v4.runtime.Token;
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
import java.util.function.BiFunction;

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
    private int codePointCursor = 0;

    public DockerfileParserVisitor(Path path, @Nullable FileAttributes fileAttributes, EncodingDetectingInputStream source) {
        this.path = path;
        this.fileAttributes = fileAttributes;
        this.source = source.readFully();
        this.charset = source.getCharset();
        this.charsetBomMarked = source.isCharsetBomMarked();
    }

    @Override
    public Dockerfile.Document visitDockerfile(DockerfileParser.DockerfileContext ctx) {
        Space prefix = prefix(ctx.getStart());
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
                Space.format(source, cursor, source.length())
        );
    }

    @Override
    public Dockerfile visitFromInstruction(DockerfileParser.FromInstructionContext ctx) {
        Space prefix = prefix(ctx.getStart());

        // Extract and skip the FROM keyword
        String fromKeyword = ctx.FROM().getText();
        skip(ctx.FROM().getSymbol());

        List<Dockerfile.Flag> flags = ctx.flags() != null ? convertFlags(ctx.flags()) : null;
        Dockerfile.Argument image = visitArgument(ctx.imageName());
        Dockerfile.From.As as = ctx.AS() != null ? visitFromAs(ctx) : null;

        // Advance cursor to end of instruction, but NOT past trailing comment
        // The trailing comment will be part of the next element's prefix
        if (ctx.getStop() != null) {
            // Only advance to the end of the last significant token, not the trailing comment
            Token stopToken = ctx.getStop();
            if (ctx.trailingComment() != null && stopToken == ctx.trailingComment().getStop()) {
                // Don't advance past the trailing comment; it will be part of next element's prefix
                if (ctx.AS() != null) {
                    stopToken = ctx.stageName().getStop();
                } else {
                    stopToken = ctx.imageName().getStop();
                }
            }
            advanceCursor(stopToken.getStopIndex() + 1);
        }

        return new Dockerfile.From(randomId(), prefix, Markers.EMPTY, fromKeyword, flags, image, as);
    }

    private Dockerfile.From.As visitFromAs(DockerfileParser.FromInstructionContext ctx) {
        Space asPrefix = prefix(ctx.AS().getSymbol());
        String asKeyword = ctx.AS().getText();
        skip(ctx.AS().getSymbol());
        return new Dockerfile.From.As(
                randomId(),
                asPrefix,
                Markers.EMPTY,
                asKeyword,
                visitArgument(ctx.stageName())
        );
    }

    @Override
    public Dockerfile visitRunInstruction(DockerfileParser.RunInstructionContext ctx) {
        Space prefix = prefix(ctx.getStart());

        // Extract and skip the RUN keyword
        String runKeyword = ctx.RUN().getText();
        skip(ctx.RUN().getSymbol());

        List<Dockerfile.Flag> flags = ctx.flags() != null ? convertFlags(ctx.flags()) : null;
        Dockerfile.CommandLine commandLine = visitCommandLine(ctx);

        // Advance cursor to end of instruction, but NOT past trailing comment
        if (ctx.getStop() != null) {
            Token stopToken = ctx.getStop();
            if (ctx.trailingComment() != null && stopToken == ctx.trailingComment().getStop()) {
                // Don't advance past the trailing comment
                if (ctx.execForm() != null) {
                    stopToken = ctx.execForm().getStop();
                } else if (ctx.shellForm() != null) {
                    stopToken = ctx.shellForm().getStop();
                } else if (ctx.heredoc() != null) {
                    stopToken = ctx.heredoc().getStop();
                }
            }
            advanceCursor(stopToken.getStopIndex() + 1);
        }

        return new Dockerfile.Run(randomId(), prefix, Markers.EMPTY, runKeyword, flags, commandLine);
    }

    @Override
    public Dockerfile visitCopyInstruction(DockerfileParser.CopyInstructionContext ctx) {
        Space prefix = prefix(ctx.getStart());

        // Extract and skip the COPY keyword
        String copyKeyword = ctx.COPY().getText();
        skip(ctx.COPY().getSymbol());

        List<Dockerfile.Flag> flags = ctx.flags() != null ? convertFlags(ctx.flags()) : null;

        // Parse source list
        List<Dockerfile.Argument> sources = new ArrayList<>();
        for (DockerfileParser.SourceContext sourceCtx : ctx.sourceList().source()) {
            sources.add(visitArgument(sourceCtx.path()));
        }

        Dockerfile.Argument destination = visitArgument(ctx.destination().path());

        // Advance cursor to end of instruction, but NOT past trailing comment
        if (ctx.getStop() != null) {
            Token stopToken = ctx.getStop();
            if (ctx.trailingComment() != null && stopToken == ctx.trailingComment().getStop()) {
                // Don't advance past the trailing comment
                stopToken = ctx.destination().getStop();
            }
            advanceCursor(stopToken.getStopIndex() + 1);
        }

        return new Dockerfile.Copy(randomId(), prefix, Markers.EMPTY, copyKeyword, flags, sources, destination);
    }

    @Override
    public Dockerfile visitArgInstruction(DockerfileParser.ArgInstructionContext ctx) {
        Space prefix = prefix(ctx.getStart());

        // Extract and skip the ARG keyword
        String argKeyword = ctx.ARG().getText();
        skip(ctx.ARG().getSymbol());

        Dockerfile.Argument name = visitArgument(ctx.argName());

        Dockerfile.Argument value = null;
        if (ctx.EQUALS() != null) {
            skip(ctx.EQUALS().getSymbol());
            value = visitArgument(ctx.argValue());
        }

        // Advance cursor to end of instruction, but NOT past trailing comment
        if (ctx.getStop() != null) {
            Token stopToken = ctx.getStop();
            if (ctx.trailingComment() != null && stopToken == ctx.trailingComment().getStop()) {
                // Don't advance past the trailing comment
                if (ctx.argValue() != null) {
                    stopToken = ctx.argValue().getStop();
                } else {
                    stopToken = ctx.argName().getStop();
                }
            }
            advanceCursor(stopToken.getStopIndex() + 1);
        }

        return new Dockerfile.Arg(randomId(), prefix, Markers.EMPTY, argKeyword, name, value);
    }

    @Override
    public Dockerfile visitCmdInstruction(DockerfileParser.CmdInstructionContext ctx) {
        Space prefix = prefix(ctx.getStart());

        String cmdKeyword = ctx.CMD().getText();
        skip(ctx.CMD().getSymbol());

        Dockerfile.CommandLine commandLine = visitCommandLineForCmd(ctx);

        // Advance cursor to end of instruction, but NOT past trailing comment
        if (ctx.getStop() != null) {
            Token stopToken = ctx.getStop();
            if (ctx.trailingComment() != null && stopToken == ctx.trailingComment().getStop()) {
                if (ctx.execForm() != null) {
                    stopToken = ctx.execForm().getStop();
                } else if (ctx.shellForm() != null) {
                    stopToken = ctx.shellForm().getStop();
                }
            }
            advanceCursor(stopToken.getStopIndex() + 1);
        }

        return new Dockerfile.Cmd(randomId(), prefix, Markers.EMPTY, cmdKeyword, commandLine);
    }

    @Override
    public Dockerfile visitEntrypointInstruction(DockerfileParser.EntrypointInstructionContext ctx) {
        Space prefix = prefix(ctx.getStart());

        String entrypointKeyword = ctx.ENTRYPOINT().getText();
        skip(ctx.ENTRYPOINT().getSymbol());

        Dockerfile.CommandLine commandLine = visitCommandLineForEntrypoint(ctx);

        // Advance cursor to end of instruction, but NOT past trailing comment
        if (ctx.getStop() != null) {
            Token stopToken = ctx.getStop();
            if (ctx.trailingComment() != null && stopToken == ctx.trailingComment().getStop()) {
                if (ctx.execForm() != null) {
                    stopToken = ctx.execForm().getStop();
                } else if (ctx.shellForm() != null) {
                    stopToken = ctx.shellForm().getStop();
                }
            }
            advanceCursor(stopToken.getStopIndex() + 1);
        }

        return new Dockerfile.Entrypoint(randomId(), prefix, Markers.EMPTY, entrypointKeyword, commandLine);
    }

    @Override
    public Dockerfile visitWorkdirInstruction(DockerfileParser.WorkdirInstructionContext ctx) {
        Space prefix = prefix(ctx.getStart());

        String workdirKeyword = ctx.WORKDIR().getText();
        skip(ctx.WORKDIR().getSymbol());

        Dockerfile.Argument path = visitArgument(ctx.path());

        // Advance cursor to end of instruction, but NOT past trailing comment
        if (ctx.getStop() != null) {
            Token stopToken = ctx.getStop();
            if (ctx.trailingComment() != null && stopToken == ctx.trailingComment().getStop()) {
                stopToken = ctx.path().getStop();
            }
            advanceCursor(stopToken.getStopIndex() + 1);
        }

        return new Dockerfile.Workdir(randomId(), prefix, Markers.EMPTY, workdirKeyword, path);
    }

    @Override
    public Dockerfile visitUserInstruction(DockerfileParser.UserInstructionContext ctx) {
        Space prefix = prefix(ctx.getStart());

        String userKeyword = ctx.USER().getText();
        skip(ctx.USER().getSymbol());

        Dockerfile.Argument userSpec = visitArgument(ctx.userSpec());

        // Advance cursor to end of instruction, but NOT past trailing comment
        if (ctx.getStop() != null) {
            Token stopToken = ctx.getStop();
            if (ctx.trailingComment() != null && stopToken == ctx.trailingComment().getStop()) {
                stopToken = ctx.userSpec().getStop();
            }
            advanceCursor(stopToken.getStopIndex() + 1);
        }

        return new Dockerfile.User(randomId(), prefix, Markers.EMPTY, userKeyword, userSpec);
    }

    @Override
    public Dockerfile visitStopsignalInstruction(DockerfileParser.StopsignalInstructionContext ctx) {
        Space prefix = prefix(ctx.getStart());

        String stopsignalKeyword = ctx.STOPSIGNAL().getText();
        skip(ctx.STOPSIGNAL().getSymbol());

        Dockerfile.Argument signal = visitArgument(ctx.signal());

        // Advance cursor to end of instruction, but NOT past trailing comment
        if (ctx.getStop() != null) {
            Token stopToken = ctx.getStop();
            if (ctx.trailingComment() != null && stopToken == ctx.trailingComment().getStop()) {
                stopToken = ctx.signal().getStop();
            }
            advanceCursor(stopToken.getStopIndex() + 1);
        }

        return new Dockerfile.Stopsignal(randomId(), prefix, Markers.EMPTY, stopsignalKeyword, signal);
    }

    @Override
    public Dockerfile visitMaintainerInstruction(DockerfileParser.MaintainerInstructionContext ctx) {
        Space prefix = prefix(ctx.getStart());

        String maintainerKeyword = ctx.MAINTAINER().getText();
        skip(ctx.MAINTAINER().getSymbol());

        Dockerfile.Argument text = visitArgument(ctx.text());

        // Advance cursor to end of instruction, but NOT past trailing comment
        if (ctx.getStop() != null) {
            Token stopToken = ctx.getStop();
            if (ctx.trailingComment() != null && stopToken == ctx.trailingComment().getStop()) {
                stopToken = ctx.text().getStop();
            }
            advanceCursor(stopToken.getStopIndex() + 1);
        }

        return new Dockerfile.Maintainer(randomId(), prefix, Markers.EMPTY, maintainerKeyword, text);
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

    private Dockerfile.CommandLine visitCommandLineForCmd(DockerfileParser.CmdInstructionContext ctx) {
        Dockerfile.CommandForm form;
        if (ctx.execForm() != null) {
            form = visitExecFormContext(ctx.execForm());
        } else if (ctx.shellForm() != null) {
            form = visitShellFormContext(ctx.shellForm());
        } else {
            form = new Dockerfile.ShellForm(randomId(), Space.EMPTY, Markers.EMPTY, emptyList());
        }

        return new Dockerfile.CommandLine(
                randomId(),
                Space.EMPTY,
                Markers.EMPTY,
                form
        );
    }

    private Dockerfile.CommandLine visitCommandLineForEntrypoint(DockerfileParser.EntrypointInstructionContext ctx) {
        Dockerfile.CommandForm form;
        if (ctx.execForm() != null) {
            form = visitExecFormContext(ctx.execForm());
        } else if (ctx.shellForm() != null) {
            form = visitShellFormContext(ctx.shellForm());
        } else {
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
            Space flagPrefix = prefix(flagCtx.getStart());
            skip(flagCtx.DASH_DASH().getSymbol());

            String flagName = flagCtx.flagName().getText();
            advanceCursor(flagCtx.flagName().getStop().getStopIndex() + 1);

            Dockerfile.Argument flagValue = null;
            if (flagCtx.EQUALS() != null) {
                skip(flagCtx.EQUALS().getSymbol());
                flagValue = visitArgument(flagCtx.flagValue());
            }

            flags.add(new Dockerfile.Flag(randomId(), flagPrefix, Markers.EMPTY, flagName, flagValue));
        }
        return flags;
    }

    private Dockerfile.ShellForm visitShellFormContext(DockerfileParser.ShellFormContext ctx) {
        return convert(ctx, (c, prefix) ->
                new Dockerfile.ShellForm(
                        randomId(),
                        prefix,
                        Markers.EMPTY,
                        singletonList(visitArgument(c.text()))
                )
        );
    }

    private Dockerfile.ExecForm visitExecFormContext(DockerfileParser.ExecFormContext ctx) {
        return convert(ctx, (c, prefix) -> {
            List<Dockerfile.Argument> args = new ArrayList<>();
            DockerfileParser.JsonArrayContext jsonArray = c.jsonArray();

            skip(jsonArray.LBRACKET().getSymbol());

            if (jsonArray.jsonArrayElements() != null) {
                for (DockerfileParser.JsonStringContext jsonStr : jsonArray.jsonArrayElements().jsonString()) {
                    Space argPrefix = prefix(jsonStr.getStart());
                    String value = jsonStr.getText();
                    // Remove surrounding quotes
                    if (value.startsWith("\"") && value.endsWith("\"")) {
                        value = value.substring(1, value.length() - 1);
                    }
                    advanceCursor(jsonStr.getStop().getStopIndex() + 1);

                    Dockerfile.QuotedString qs = new Dockerfile.QuotedString(
                            randomId(),
                            Space.EMPTY,
                            Markers.EMPTY,
                            value,
                            Dockerfile.QuotedString.QuoteStyle.DOUBLE
                    );
                    args.add(new Dockerfile.Argument(
                            randomId(),
                            argPrefix,
                            Markers.EMPTY,
                            singletonList(qs)
                    ));
                }
            }

            skip(jsonArray.RBRACKET().getSymbol());

            return new Dockerfile.ExecForm(randomId(), prefix, Markers.EMPTY, args);
        });
    }

    private Dockerfile.Argument visitArgument(ParserRuleContext ctx) {
        if (ctx == null) {
            return new Dockerfile.Argument(randomId(), Space.EMPTY, Markers.EMPTY, emptyList());
        }

        return convert(ctx, (c, prefix) -> {
            String text = c.getText();
            List<Dockerfile.ArgumentContent> contents = new ArrayList<>();

            // Simple implementation: treat as plain text for now
            // TODO: Parse environment variables and quoted strings properly
            Dockerfile.PlainText pt = new Dockerfile.PlainText(
                    randomId(),
                    Space.EMPTY,
                    Markers.EMPTY,
                    text
            );
            contents.add(pt);

            return new Dockerfile.Argument(randomId(), prefix, Markers.EMPTY, contents);
        });
    }

    // Helper methods for cursor management

    private Space prefix(ParserRuleContext ctx) {
        return prefix(ctx.getStart());
    }

    private Space prefix(Token token) {
        int start = token.getStartIndex();
        if (start < codePointCursor) {
            return Space.EMPTY;
        }
        return Space.format(source, cursor, advanceCursor(start));
    }

    private int advanceCursor(int newCodePointIndex) {
        if (newCodePointIndex <= codePointCursor) {
            return cursor;
        }
        cursor = source.offsetByCodePoints(cursor, newCodePointIndex - codePointCursor);
        codePointCursor = newCodePointIndex;
        return cursor;
    }

    private void skip(Token token) {
        if (token != null) {
            advanceCursor(token.getStopIndex() + 1);
        }
    }

    private <C extends ParserRuleContext, T> T convert(C ctx, BiFunction<C, Space, T> conversion) {
        T t = conversion.apply(ctx, prefix(ctx));
        if (ctx.getStop() != null) {
            advanceCursor(ctx.getStop().getStopIndex() + 1);
        }
        return t;
    }
}
