/*
 * Copyright (c) 2025 Jim Schubert
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.docker.tree;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.openrewrite.Cursor;
import org.openrewrite.PrintOutputCapture;
import org.openrewrite.docker.DockerVisitor;
import org.openrewrite.internal.StringUtils;
import org.openrewrite.marker.Marker;
import org.openrewrite.marker.Markers;

import java.util.List;
import java.util.function.UnaryOperator;

import static org.openrewrite.docker.internal.StringUtil.trimDoubleQuotes;

public class DockerfilePrinter<P> extends DockerVisitor<PrintOutputCapture<P>> {
    private static final UnaryOperator<String> MARKER_WRAPPER =
            out -> "/*~~" + out + (out.isEmpty() ? "" : "~~") + ">*/";

    protected void beforeSyntax(Docker t, PrintOutputCapture<P> p) {
        beforeSyntax(t.getPrefix(), t.getMarkers(), p);
    }

    @Override
    public @NonNull Markers visitMarkers(@Nullable Markers markers, PrintOutputCapture<P> p) {
        if (markers == null) {
            return Markers.EMPTY;
        }
        return super.visitMarkers(markers, p);
    }

    protected void beforeSyntax(Space prefix, Markers markers, PrintOutputCapture<P> p) {
        visitSpace(prefix, p);

        if (markers == null || markers.getMarkers().isEmpty()) {
            return;
        }

        for (Marker marker : markers.getMarkers()) {
            p.append(p.getMarkerPrinter().beforePrefix(marker, new Cursor(getCursor(), marker), MARKER_WRAPPER));
        }
        visitSpace(prefix, p);
        visitMarkers(markers, p);
        for (Marker marker : markers.getMarkers()) {
            p.append(p.getMarkerPrinter().beforeSyntax(marker, new Cursor(getCursor(), marker), MARKER_WRAPPER));
        }
    }

    protected void afterSyntax(Docker t, PrintOutputCapture<P> p) {
        if (t instanceof Docker.Instruction) {
            Docker.Instruction instruction = (Docker.Instruction) t;
            if (instruction.getEol() != null) {
                visitSpace(instruction.getEol(), p);
            }
        }
        afterSyntax(t.getMarkers(), p);
    }

    protected void afterSyntax(Markers markers, PrintOutputCapture<P> p) {
        if (markers == null) {
            return;
        }
        for (Marker marker : markers.getMarkers()) {
            p.append(p.getMarkerPrinter().afterSyntax(marker, new Cursor(getCursor(), marker), MARKER_WRAPPER));
        }
    }

    @Override
    public Docker visitDocument(Docker.Document dockerfile, PrintOutputCapture<P> p) {
        List<Docker.Stage> stages = dockerfile.getStages();
        for (int i = 0; i < stages.size(); i++) {
            Docker.Stage stage = stages.get(i);
            visit(stage, p);
        }

        visitSpace(dockerfile.getEof(), p);

        return dockerfile;
    }

    @Override
    public Docker visitStage(Docker.Stage stage, PrintOutputCapture<P> p) {
        beforeSyntax(stage, p);
        List<Docker> children = stage.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Docker instruction = children.get(i);
            visit(instruction, p);
        }
        afterSyntax(stage, p);
        return stage;
    }

    private void instructionName(Docker.Instruction instruction, PrintOutputCapture<P> p) {
        String defaultName = instruction.getClass().getSimpleName();
        p.append(
            instruction.getMarkers().findFirst(InstructionName.class)
                    .filter(f -> f.getName().equalsIgnoreCase(defaultName))
                    .map(InstructionName::getName)
                    .orElseGet(defaultName::toUpperCase)
        );
    }

    @Override
    public Docker visitFrom(Docker.From from, PrintOutputCapture<P> p) {
        beforeSyntax(from, p);
        instructionName(from, p);
        if (from.getPlatform() != null && !StringUtils.isBlank(from.getPlatform().getText())) {
            visitSpace(from.getPlatform().getPrefix(), p);
            if (!from.getPlatform().getText().startsWith("--")) {
                if ("".equals(from.getPlatform().getPrefix().getWhitespace())) {
                    p.append(" ");
                }
                p.append("--platform=");
            }

            p.append(from.getPlatform().getText());
            visitSpace(from.getPlatform().getTrailing(), p);
        }

        visitLiteral(from.getImage(), p);
        visitLiteral(from.getVersion(), p);

        if (from.getAlias() != null &&
            from.getAlias().getText() != null) {
            visitLiteral(from.getAs(), p);
            visitLiteral(from.getAlias(), p);
        }

        afterSyntax(from, p);
        return from;
    }

    @Override
    public Space visitSpace(Space space, PrintOutputCapture<P> p) {
        if (space == null) {
            return Space.EMPTY;
        }
        if (space.getWhitespace() != null) {
            p.append(space.getWhitespace());
        }
        return space;
    }

    @Override
    public Docker visitComment(Docker.Comment comment, PrintOutputCapture<P> p) {
        Docker.Literal literal = comment.getText();
        if (literal != null) {
            beforeSyntax(comment, p);
            p.append("#");
            visitLiteral(literal.withText(literal.getText().replaceAll("\n", "\n# ")), p);
            afterSyntax(comment, p);
        }
        return comment;
    }

    @Override
    public Docker visitDirective(Docker.Directive directive, PrintOutputCapture<P> p) {
        beforeSyntax(directive, p);
        p.append("#");
        DockerRightPadded<Docker.KeyArgs> padded = directive.getDirective();
        Docker.KeyArgs keyArgs = padded.getElement();
        visitKeyArgs(keyArgs, p);
        visitSpace(padded.getAfter(), p);
        afterSyntax(directive, p);
        return directive;
    }

    @Override
    public Docker visitRun(Docker.Run run, PrintOutputCapture<P> p) {
        beforeSyntax(run, p);
        instructionName(run, p);
        if (run.getOptions() != null) {
            run.getOptions().forEach(o -> {
                visitOption(o, p);
            });
        }

        if (run.getCommands() != null) {
            for (int i = 0; i < run.getCommands().size(); i++) {
                visitLiteral(run.getCommands().get(i), p);
            }
        }

        afterSyntax(run, p);
        return run;
    }

    @Override
    public Docker visitCmd(Docker.Cmd cmd, PrintOutputCapture<P> p) {
        beforeSyntax(cmd, p);
        instructionName(cmd, p);
        Form form = cmd.getForm();
        if (form == null) {
            form = Form.EXEC;
        }
        if (form == Form.EXEC) {
            p.append(" [");
        }

        for (int i = 0; i < cmd.getCommands().size(); i++) {
            Docker.Literal literal = cmd.getCommands().get(i);
            String text = literal.getText();
            visitSpace(literal.getPrefix(), p);

            if (form == Form.EXEC) {
                text = trimDoubleQuotes(text);
                p.append("\"").append(text).append("\"");
                if (i < cmd.getCommands().size() - 1) {
                    p.append(",");
                }
            } else {
                p.append(text);
            }
            visitSpace(literal.getTrailing(), p);
        }

        if (form == Form.EXEC) {
            p.append("]");
        }
        afterSyntax(cmd, p);
        return cmd;
    }

    private Docker.Literal visitFormLiteral(Form form, Docker.Literal literal, PrintOutputCapture<P> p) {
        if (literal == null) {
            return null;
        }
        visitSpace(literal.getPrefix(), p);
        if (literal.getText() != null) {
            p.append(literal.getText());
        }
        visitSpace(literal.getTrailing(), p);
        return literal;
    }

    @Override
    public Docker visitLiteral(Docker.Literal literal, PrintOutputCapture<P> p) {
        visitSpace(literal.getPrefix(), p);
        visitQuoting(literal.getQuoting(), p);
        if (literal.getText() != null) {
            p.append(literal.getText());
        }
        visitQuoting(literal.getQuoting(), p);
        visitSpace(literal.getTrailing(), p);
        afterSyntax(literal, p);
        return literal;
    }

    @Override
    public Docker visitOption(Docker.Option option, PrintOutputCapture<P> p) {
        if (option == null) {
            return null;
        }

        visitSpace(option.getPrefix(), p);
        visitKeyArgs(option.getKeyArgs(), p);
        afterSyntax(option, p);
        return option;
    }

    private DockerRightPadded<Docker.Literal> visitDockerRightPaddedLiteral(DockerRightPadded<Docker.Literal> padded, PrintOutputCapture<P> p) {
        if (padded == null) {
            return null;
        }

        visitLiteral(padded.getElement(), p);
        visitSpace(padded.getAfter(), p);
        afterSyntax(padded.getMarkers(), p);

        return padded;
    }

    private Docker.KeyArgs visitKeyArgs(Docker.KeyArgs keyArgs, PrintOutputCapture<P> p) {
        if (keyArgs == null) {
            return null;
        }
        visitSpace(keyArgs.getPrefix(), p);
        if (keyArgs.key() != null && !keyArgs.key().isEmpty()) {
            visitLiteral(keyArgs.getKey(), p);
        }
        if (keyArgs.isHasEquals()) {
            p.append("=");
        }
        if (keyArgs.getQuoting() == Quoting.SINGLE_QUOTED) {
            p.append("'").append(keyArgs.value()).append("'");
        } else if (keyArgs.getQuoting() == Quoting.DOUBLE_QUOTED) {
            p.append("\"").append(keyArgs.value()).append("\"");
        } else {
            visitLiteral(keyArgs.getValue(), p);
        }
        return keyArgs;
    }

    @Override
    public Docker visitLabel(Docker.Label label, PrintOutputCapture<P> p) {
        beforeSyntax(label, p);
        instructionName(label, p);
        for (DockerRightPadded<Docker.KeyArgs> padded : label.getArgs()) {
            visitKeyArgs(padded.getElement(), p);
            visitSpace(padded.getAfter(), p);
        }
        afterSyntax(label, p);
        return label;
    }

    @Override
    public Docker visitMaintainer(Docker.Maintainer maintainer, PrintOutputCapture<P> p) {
        beforeSyntax(maintainer, p);
        instructionName(maintainer, p);
        visitLiteral(maintainer.getName(), p);
        afterSyntax(maintainer, p);
        return maintainer;
    }

    @Override
    public Docker visitExpose(Docker.Expose expose, PrintOutputCapture<P> p) {
        beforeSyntax(expose, p);
        instructionName(expose, p);
        for (int i = 0; i < expose.getPorts().size(); i++) {
            DockerRightPadded<Docker.Port> padded = expose.getPorts().get(i);
            Docker.Port port = padded.getElement();
            visitSpace(port.getPrefix(), p);
            p.append(port.getPort());
            if (port.isProtocolProvided() && port.getProtocol() != null && !port.getProtocol().isEmpty()) {
                p.append("/");
                p.append(port.getProtocol());
            }
            visitSpace(padded.getAfter(), p);
        }
        afterSyntax(expose, p);
        return expose;
    }

    private void visitQuoting(Quoting quoting, PrintOutputCapture<P> p) {
        if (quoting == Quoting.SINGLE_QUOTED) {
            p.append("'");
        } else if (quoting == Quoting.DOUBLE_QUOTED) {
            p.append("\"");
        }
    }

    @Override
    public Docker visitEnv(Docker.Env env, PrintOutputCapture<P> p) {
        beforeSyntax(env, p);
        instructionName(env, p);

        for (DockerRightPadded<Docker.KeyArgs> padded : env.getArgs()) {
            Docker.KeyArgs kvp = padded.getElement();
            visitSpace(kvp.getPrefix(), p);
            p.append(kvp.key());
            if (kvp.value() != null && !kvp.value().isEmpty()) {
                if (kvp.isHasEquals()) {
                    p.append("=");
                } else {
                    p.append(" ");
                }

                visitQuoting(kvp.getQuoting(), p);
                p.append(kvp.value());
                visitQuoting(kvp.getQuoting(), p);
            }

            visitSpace(padded.getAfter(), p);
        }
        afterSyntax(env, p);
        return env;
    }

    @Override
    public Docker visitAdd(Docker.Add add, PrintOutputCapture<P> p) {
        beforeSyntax(add, p);
        instructionName(add, p);

        if (add.getOptions() != null) {
            add.getOptions().forEach(o -> {
                visitOption(o, p);
            });
        }

        if (add.getSources() != null) {
            for (int i = 0; i < add.getSources().size(); i++) {
                visitLiteral(add.getSources().get(i), p);
            }
        }

        visitLiteral(add.getDestination(), p);

        afterSyntax(add, p);
        return add;
    }

    @Override
    public Docker visitCopy(Docker.Copy copy, PrintOutputCapture<P> p) {
        beforeSyntax(copy, p);
        instructionName(copy, p);

        if (copy.getOptions() != null) {
            copy.getOptions().forEach(o -> {
                visitOption(o, p);
            });
        }

        if (copy.getSources() != null) {
            for (int i = 0; i < copy.getSources().size(); i++) {
                visitLiteral(copy.getSources().get(i), p);
            }
        }

        visitLiteral(copy.getDestination(), p);

        afterSyntax(copy, p);
        return copy;
    }

    @Override
    public Docker visitEntrypoint(Docker.Entrypoint entrypoint, PrintOutputCapture<P> p) {
        beforeSyntax(entrypoint, p);
        instructionName(entrypoint, p);

        if (entrypoint.getForm() == Form.EXEC) {
            Space before = entrypoint.getExecFormPrefix();
            if (before == null || before.isEmpty()) {
                before = Space.build(" ");
            }
            visitSpace(before, p);

            p.append("[");
        }

        List<Docker.Literal> commands = entrypoint.getCommands();
        for (int i = 0; i < commands.size(); i++) {
            Docker.Literal literal = commands.get(i);

            if (entrypoint.getForm() == Form.SHELL
                && i > 0
                && i < commands.size() - 1
                && "".equals(literal.getPrefix().getWhitespace())) {
                p.append(" ");
            }

            visitLiteral(literal, p);

            if (i < commands.size() - 1 && entrypoint.getForm() == Form.EXEC) {
                p.append(",");
            }
        }
        if (entrypoint.getForm() == Form.EXEC) {
            p.append("]");
            visitSpace(entrypoint.getExecFormSuffix(), p);
        }
        afterSyntax(entrypoint, p);
        return entrypoint;
    }

    @Override
    public Docker visitVolume(Docker.Volume volume, PrintOutputCapture<P> p) {
        beforeSyntax(volume, p);
        instructionName(volume, p);

        if (volume.getForm() == Form.EXEC) {
            Space before = volume.getExecFormPrefix();
            if (before == null || before.isEmpty()) {
                before = Space.build(" ");
            }
            visitSpace(before, p);
            p.append("[");
        }

        for (int i = 0; i < volume.getPaths().size(); i++) {
            Docker.Literal literal = volume.getPaths().get(i);
            if (volume.getForm() == Form.SHELL
                && i > 0
                && i < volume.getPaths().size() - 1
                && "".equals(literal.getPrefix().getWhitespace())) {
                p.append(" ");
            }

            visitLiteral(literal, p);

            if (volume.getForm() == Form.EXEC && i < volume.getPaths().size() - 1) {
                p.append(",");
            }
        }

        if (volume.getForm() == Form.EXEC) {
            p.append("]");
            visitSpace(volume.getExecFormSuffix(), p);
        }

        afterSyntax(volume, p);
        return volume;
    }

    @Override
    public Docker visitUser(Docker.User user, PrintOutputCapture<P> p) {
        beforeSyntax(user, p);
        instructionName(user, p);
        visitSpace(user.getUsername().getPrefix(), p);
        p.append(user.getUsername().getText());
        visitSpace(user.getUsername().getTrailing(), p);
        if (user.getGroup() != null && user.getGroup().getText() != null && !user.getGroup().getText().isEmpty()) {
            p.append(":");
            visitSpace(user.getGroup().getPrefix(), p);
            p.append(user.getGroup().getText());
            visitSpace(user.getGroup().getTrailing(), p);
        }
        afterSyntax(user, p);
        return user;
    }

    @Override
    public Docker visitWorkdir(Docker.Workdir workdir, PrintOutputCapture<P> p) {
        beforeSyntax(workdir, p);
        instructionName(workdir, p);
        visitLiteral(workdir.getPath(), p);
        afterSyntax(workdir, p);
        return workdir;
    }

    @Override
    public Docker visitArg(Docker.Arg arg, PrintOutputCapture<P> p) {
        beforeSyntax(arg, p);
        instructionName(arg, p);
        arg.getArgs().forEach(padded -> {
            Docker.KeyArgs args = padded.getElement();
            if (args.getValue().getText() != null) {
                visitKeyArgs(args, p);
            } else {
                // ARG without a value is a special case where we don't want to output KEY=
                visitSpace(args.getPrefix(), p);
                visitLiteral(args.getKey(), p);
            }
            visitSpace(padded.getAfter(), p);
        });
        afterSyntax(arg, p);
        return arg;
    }

    @Override
    public Docker visitOnBuild(Docker.OnBuild onBuild, PrintOutputCapture<P> p) {
        beforeSyntax(onBuild, p);
        instructionName(onBuild, p);
        p.append(" ");
        visit(onBuild.getInstruction(), p);
        afterSyntax(onBuild, p);
        return onBuild;
    }

    @Override
    public Docker visitStopSignal(Docker.StopSignal stopSignal, PrintOutputCapture<P> p) {
        beforeSyntax(stopSignal, p);
        instructionName(stopSignal, p);
        visitSpace(stopSignal.getPrefix(), p);
        visitLiteral(stopSignal.getSignal(), p);
        afterSyntax(stopSignal, p);
        return stopSignal;
    }

    @Override
    public Docker visitHealthcheck(Docker.Healthcheck healthcheck, PrintOutputCapture<P> p) {
        beforeSyntax(healthcheck, p);
        instructionName(healthcheck, p);

        if (healthcheck.getType() == Docker.Healthcheck.Type.NONE) {
            p.append(" NONE");
            afterSyntax(healthcheck, p);
            return healthcheck;
        }

        if (healthcheck.getOptions() != null) {
            healthcheck.getOptions().forEach(o -> {
                Docker.KeyArgs arg = o.getElement();
                if (!arg.key().startsWith("--")) {
                    arg = new Docker.KeyArgs(arg.getPrefix(), Docker.Literal.prepend("--", arg.getKey()), arg.getValue(), arg.isHasEquals(), arg.getQuoting());
                }
                visitKeyArgs(arg, p);
                visitSpace(o.getAfter(), p);
            });
        }

        if (healthcheck.getCommands() != null) {
            for (int i = 0; i < healthcheck.getCommands().size(); i++) {
                Docker.Literal command = healthcheck.getCommands().get(i);

                if (i == 0 && "CMD".equals(command.getText())) {
                    if (command.getPrefix().isEmpty()) {
                        p.append(" ");
                    }
                    p.append("CMD");
                    visitSpace(command.getPrefix(), p);
                    continue;
                } else if (i == 0) {
                    p.append(" CMD");
                }

                if (i > 0 && "".equals(command.getPrefix().getWhitespace())) {
                    p.append(" ");
                }

                visitLiteral(command, p);
            }
        }

        afterSyntax(healthcheck, p);
        return healthcheck;
    }

    @Override
    public Docker visitShell(Docker.Shell shell, PrintOutputCapture<P> p) {
        beforeSyntax(shell, p);
        instructionName(shell, p);
        if ("".equals(shell.getExecFormPrefix().getWhitespace())) {
            p.append(" ");
        } else {
            p.append(shell.getExecFormPrefix().getWhitespace());
        }
        p.append("[");

        for (int i = 0; i < shell.getCommands().size(); i++) {
            Docker.Literal literal = shell.getCommands().get(i);
            if (literal.getQuoting() != Quoting.DOUBLE_QUOTED) {
                literal = ((Docker.Literal) literal.copyPaste())
                        .withQuoting(Quoting.DOUBLE_QUOTED)
                        .withText(trimDoubleQuotes(literal.getText()));
            }

            visitLiteral(literal, p);

            if (i < shell.getCommands().size() - 1) {
                p.append(",");
            }
        }

        p.append("]");

        visitSpace(shell.getExecFormSuffix(), p);
        afterSyntax(shell, p);
        return shell;
    }

    private void appendRightPaddedLiteral(DockerRightPadded<Docker.Literal> padded, PrintOutputCapture<P> p) {
        if (padded == null) {
            return;
        }

        Docker.Literal literal = padded.getElement();
        if (literal == null || literal.getText() == null || literal.getText().isEmpty()) {
            return;
        }
        visitSpace(literal.getPrefix(), p);
        p.append(literal.getText());
        visitSpace(padded.getAfter(), p);
    }
}
