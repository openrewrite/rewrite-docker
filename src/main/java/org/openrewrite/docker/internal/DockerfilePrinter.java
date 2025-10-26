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

import org.openrewrite.PrintOutputCapture;
import org.openrewrite.docker.DockerfileVisitor;
import org.openrewrite.docker.tree.Comment;
import org.openrewrite.docker.tree.Dockerfile;
import org.openrewrite.docker.tree.Space;
import org.openrewrite.marker.Marker;

public class DockerfilePrinter<P> extends DockerfileVisitor<PrintOutputCapture<P>> {

    public Space visitSpace(Space space, PrintOutputCapture<P> p) {
        for (Comment comment : space.getComments()) {
            p.append(comment.getSuffix());
            p.append(comment.getText());
        }
        p.append(space.getWhitespace());
        return space;
    }

    @Override
    public Dockerfile visitDocument(Dockerfile.Document document, PrintOutputCapture<P> p) {
        beforeSyntax(document, p);
        for (Dockerfile.Instruction instruction : document.getInstructions()) {
            visit(instruction, p);
        }
        visitSpace(document.getEof(), p);
        afterSyntax(document, p);
        return document;
    }

    @Override
    public Dockerfile visitFrom(Dockerfile.From from, PrintOutputCapture<P> p) {
        beforeSyntax(from, p);
        p.append(from.getKeyword());
        if (from.getFlags() != null) {
            for (Dockerfile.Flag flag : from.getFlags()) {
                visit(flag, p);
            }
        }
        visit(from.getImage(), p);
        if (from.getAs() != null) {
            visitSpace(from.getAs().getPrefix(), p);
            p.append(from.getAs().getKeyword());
            visit(from.getAs().getName(), p);
        }
        afterSyntax(from, p);
        return from;
    }

    @Override
    public Dockerfile visitRun(Dockerfile.Run run, PrintOutputCapture<P> p) {
        beforeSyntax(run, p);
        p.append(run.getKeyword());
        if (run.getFlags() != null) {
            for (Dockerfile.Flag flag : run.getFlags()) {
                visit(flag, p);
            }
        }
        visit(run.getCommandLine(), p);
        afterSyntax(run, p);
        return run;
    }

    @Override
    public Dockerfile visitAdd(Dockerfile.Add add, PrintOutputCapture<P> p) {
        beforeSyntax(add, p);
        p.append(add.getKeyword());
        if (add.getFlags() != null) {
            for (Dockerfile.Flag flag : add.getFlags()) {
                visit(flag, p);
            }
        }
        for (Dockerfile.Argument source : add.getSources()) {
            visit(source, p);
        }
        visit(add.getDestination(), p);
        afterSyntax(add, p);
        return add;
    }

    @Override
    public Dockerfile visitCopy(Dockerfile.Copy copy, PrintOutputCapture<P> p) {
        beforeSyntax(copy, p);
        p.append(copy.getKeyword());
        if (copy.getFlags() != null) {
            for (Dockerfile.Flag flag : copy.getFlags()) {
                visit(flag, p);
            }
        }
        for (Dockerfile.Argument source : copy.getSources()) {
            visit(source, p);
        }
        visit(copy.getDestination(), p);
        afterSyntax(copy, p);
        return copy;
    }

    @Override
    public Dockerfile visitArg(Dockerfile.Arg arg, PrintOutputCapture<P> p) {
        beforeSyntax(arg, p);
        p.append(arg.getKeyword());
        visit(arg.getName(), p);
        if (arg.getValue() != null) {
            p.append("=");
            visit(arg.getValue(), p);
        }
        afterSyntax(arg, p);
        return arg;
    }

    @Override
    public Dockerfile visitEnv(Dockerfile.Env env, PrintOutputCapture<P> p) {
        beforeSyntax(env, p);
        p.append(env.getKeyword());
        for (Dockerfile.Env.EnvPair pair : env.getPairs()) {
            visitSpace(pair.getPrefix(), p);
            visit(pair.getKey(), p);
            if (pair.isHasEquals()) {
                p.append("=");
            }
            visit(pair.getValue(), p);
        }
        afterSyntax(env, p);
        return env;
    }

    @Override
    public Dockerfile visitLabel(Dockerfile.Label label, PrintOutputCapture<P> p) {
        beforeSyntax(label, p);
        p.append(label.getKeyword());
        for (Dockerfile.Label.LabelPair pair : label.getPairs()) {
            visitSpace(pair.getPrefix(), p);
            visit(pair.getKey(), p);
            p.append("=");
            visit(pair.getValue(), p);
        }
        afterSyntax(label, p);
        return label;
    }

    @Override
    public Dockerfile visitCmd(Dockerfile.Cmd cmd, PrintOutputCapture<P> p) {
        beforeSyntax(cmd, p);
        p.append(cmd.getKeyword());
        visit(cmd.getCommandLine(), p);
        afterSyntax(cmd, p);
        return cmd;
    }

    @Override
    public Dockerfile visitEntrypoint(Dockerfile.Entrypoint entrypoint, PrintOutputCapture<P> p) {
        beforeSyntax(entrypoint, p);
        p.append(entrypoint.getKeyword());
        visit(entrypoint.getCommandLine(), p);
        afterSyntax(entrypoint, p);
        return entrypoint;
    }

    @Override
    public Dockerfile visitExpose(Dockerfile.Expose expose, PrintOutputCapture<P> p) {
        beforeSyntax(expose, p);
        p.append(expose.getKeyword());
        for (Dockerfile.Argument port : expose.getPorts()) {
            visit(port, p);
        }
        afterSyntax(expose, p);
        return expose;
    }

    @Override
    public Dockerfile visitVolume(Dockerfile.Volume volume, PrintOutputCapture<P> p) {
        beforeSyntax(volume, p);
        p.append(volume.getKeyword());
        if (volume.isJsonForm()) {
            // Print space and opening bracket
            if (!volume.getValues().isEmpty()) {
                visitSpace(volume.getValues().get(0).getPrefix(), p);
            }
            p.append("[");
            for (int i = 0; i < volume.getValues().size(); i++) {
                Dockerfile.Argument arg = volume.getValues().get(i);
                // For first element, we already printed its prefix above
                // For subsequent elements, print comma then prefix
                if (i > 0) {
                    p.append(",");
                    visitSpace(arg.getPrefix(), p);
                }
                // Visit the argument content without its prefix
                for (Dockerfile.ArgumentContent content : arg.getContents()) {
                    visit(content, p);
                }
            }
            p.append("]");
        } else {
            for (Dockerfile.Argument value : volume.getValues()) {
                visit(value, p);
            }
        }
        afterSyntax(volume, p);
        return volume;
    }

    @Override
    public Dockerfile visitShell(Dockerfile.Shell shell, PrintOutputCapture<P> p) {
        beforeSyntax(shell, p);
        p.append(shell.getKeyword());
        // Print space and opening bracket
        if (!shell.getArguments().isEmpty()) {
            visitSpace(shell.getArguments().get(0).getPrefix(), p);
        }
        p.append("[");
        for (int i = 0; i < shell.getArguments().size(); i++) {
            Dockerfile.Argument arg = shell.getArguments().get(i);
            // For first element, we already printed its prefix above
            // For subsequent elements, print comma then prefix
            if (i > 0) {
                p.append(",");
                visitSpace(arg.getPrefix(), p);
            }
            // Visit the argument content without its prefix
            for (Dockerfile.ArgumentContent content : arg.getContents()) {
                visit(content, p);
            }
        }
        p.append("]");
        afterSyntax(shell, p);
        return shell;
    }

    @Override
    public Dockerfile visitWorkdir(Dockerfile.Workdir workdir, PrintOutputCapture<P> p) {
        beforeSyntax(workdir, p);
        p.append(workdir.getKeyword());
        visit(workdir.getPath(), p);
        afterSyntax(workdir, p);
        return workdir;
    }

    @Override
    public Dockerfile visitUser(Dockerfile.User user, PrintOutputCapture<P> p) {
        beforeSyntax(user, p);
        p.append(user.getKeyword());
        visit(user.getUserSpec(), p);
        afterSyntax(user, p);
        return user;
    }

    @Override
    public Dockerfile visitStopsignal(Dockerfile.Stopsignal stopsignal, PrintOutputCapture<P> p) {
        beforeSyntax(stopsignal, p);
        p.append(stopsignal.getKeyword());
        visit(stopsignal.getSignal(), p);
        afterSyntax(stopsignal, p);
        return stopsignal;
    }

    @Override
    public Dockerfile visitMaintainer(Dockerfile.Maintainer maintainer, PrintOutputCapture<P> p) {
        beforeSyntax(maintainer, p);
        p.append(maintainer.getKeyword());
        visit(maintainer.getText(), p);
        afterSyntax(maintainer, p);
        return maintainer;
    }

    @Override
    public Dockerfile visitCommandLine(Dockerfile.CommandLine commandLine, PrintOutputCapture<P> p) {
        beforeSyntax(commandLine, p);
        visit(commandLine.getForm(), p);
        afterSyntax(commandLine, p);
        return commandLine;
    }

    @Override
    public Dockerfile visitShellForm(Dockerfile.ShellForm shellForm, PrintOutputCapture<P> p) {
        beforeSyntax(shellForm, p);
        for (Dockerfile.Argument arg : shellForm.getArguments()) {
            visit(arg, p);
        }
        afterSyntax(shellForm, p);
        return shellForm;
    }

    @Override
    public Dockerfile visitExecForm(Dockerfile.ExecForm execForm, PrintOutputCapture<P> p) {
        beforeSyntax(execForm, p);
        p.append("[");
        for (Dockerfile.Argument arg : execForm.getArguments()) {
            visit(arg, p);
        }
        p.append("]");
        afterSyntax(execForm, p);
        return execForm;
    }

    @Override
    public Dockerfile visitFlag(Dockerfile.Flag flag, PrintOutputCapture<P> p) {
        beforeSyntax(flag, p);
        p.append("--").append(flag.getName());
        if (flag.getValue() != null) {
            p.append("=");
            visit(flag.getValue(), p);
        }
        afterSyntax(flag, p);
        return flag;
    }

    @Override
    public Dockerfile visitArgument(Dockerfile.Argument argument, PrintOutputCapture<P> p) {
        beforeSyntax(argument, p);
        for (Dockerfile.ArgumentContent content : argument.getContents()) {
            visit(content, p);
        }
        afterSyntax(argument, p);
        return argument;
    }

    @Override
    public Dockerfile visitPlainText(Dockerfile.PlainText plainText, PrintOutputCapture<P> p) {
        beforeSyntax(plainText, p);
        p.append(plainText.getText());
        afterSyntax(plainText, p);
        return plainText;
    }

    @Override
    public Dockerfile visitQuotedString(Dockerfile.QuotedString quotedString, PrintOutputCapture<P> p) {
        beforeSyntax(quotedString, p);
        char quote = quotedString.getQuoteStyle() == Dockerfile.QuotedString.QuoteStyle.DOUBLE ? '"' : '\'';
        p.append(quote).append(quotedString.getValue()).append(quote);
        afterSyntax(quotedString, p);
        return quotedString;
    }

    @Override
    public Dockerfile visitEnvironmentVariable(Dockerfile.EnvironmentVariable environmentVariable, PrintOutputCapture<P> p) {
        beforeSyntax(environmentVariable, p);
        if (environmentVariable.isBraced()) {
            p.append("${").append(environmentVariable.getName()).append("}");
        } else {
            p.append("$").append(environmentVariable.getName());
        }
        afterSyntax(environmentVariable, p);
        return environmentVariable;
    }

    private static final java.util.function.UnaryOperator<String> DOCKERFILE_MARKER_WRAPPER =
            out -> "~~" + out + (out.isEmpty() ? "" : "~~") + ">";

    private void beforeSyntax(Dockerfile d, PrintOutputCapture<P> p) {
        beforeSyntax(d.getPrefix(), d.getMarkers(), p);
    }

    private void beforeSyntax(org.openrewrite.docker.tree.Space prefix, org.openrewrite.marker.Markers markers, PrintOutputCapture<P> p) {
        for (Marker marker : markers.getMarkers()) {
            p.append(p.getMarkerPrinter().beforePrefix(marker, new org.openrewrite.Cursor(getCursor(), marker), DOCKERFILE_MARKER_WRAPPER));
        }
        visitSpace(prefix, p);
        visitMarkers(markers, p);
        for (Marker marker : markers.getMarkers()) {
            p.append(p.getMarkerPrinter().beforeSyntax(marker, new org.openrewrite.Cursor(getCursor(), marker), DOCKERFILE_MARKER_WRAPPER));
        }
    }

    private void afterSyntax(Dockerfile d, PrintOutputCapture<P> p) {
        afterSyntax(d.getMarkers(), p);
    }

    private void afterSyntax(org.openrewrite.marker.Markers markers, PrintOutputCapture<P> p) {
        for (Marker marker : markers.getMarkers()) {
            p.append(p.getMarkerPrinter().afterSyntax(marker, new org.openrewrite.Cursor(getCursor(), marker), DOCKERFILE_MARKER_WRAPPER));
        }
    }
}
