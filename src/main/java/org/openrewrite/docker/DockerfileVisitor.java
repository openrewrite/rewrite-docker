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

import org.openrewrite.TreeVisitor;
import org.openrewrite.docker.tree.Dockerfile;

public class DockerfileVisitor<P> extends TreeVisitor<Dockerfile, P> {

    public Dockerfile visitDocument(Dockerfile.Document document, P p) {
        return document;
    }

    public Dockerfile visitFrom(Dockerfile.From from, P p) {
        return from;
    }

    public Dockerfile visitRun(Dockerfile.Run run, P p) {
        return run;
    }

    public Dockerfile visitAdd(Dockerfile.Add add, P p) {
        return add;
    }

    public Dockerfile visitCopy(Dockerfile.Copy copy, P p) {
        return copy;
    }

    public Dockerfile visitArg(Dockerfile.Arg arg, P p) {
        return arg;
    }

    public Dockerfile visitEnv(Dockerfile.Env env, P p) {
        return env;
    }

    public Dockerfile visitLabel(Dockerfile.Label label, P p) {
        return label;
    }

    public Dockerfile visitCmd(Dockerfile.Cmd cmd, P p) {
        return cmd;
    }

    public Dockerfile visitEntrypoint(Dockerfile.Entrypoint entrypoint, P p) {
        return entrypoint;
    }

    public Dockerfile visitExpose(Dockerfile.Expose expose, P p) {
        return expose;
    }

    public Dockerfile visitVolume(Dockerfile.Volume volume, P p) {
        return volume;
    }

    public Dockerfile visitShell(Dockerfile.Shell shell, P p) {
        return shell;
    }

    public Dockerfile visitWorkdir(Dockerfile.Workdir workdir, P p) {
        return workdir;
    }

    public Dockerfile visitUser(Dockerfile.User user, P p) {
        return user;
    }

    public Dockerfile visitStopsignal(Dockerfile.Stopsignal stopsignal, P p) {
        return stopsignal;
    }

    public Dockerfile visitOnbuild(Dockerfile.Onbuild onbuild, P p) {
        return onbuild;
    }

    public Dockerfile visitHealthcheck(Dockerfile.Healthcheck healthcheck, P p) {
        return healthcheck;
    }

    public Dockerfile visitMaintainer(Dockerfile.Maintainer maintainer, P p) {
        return maintainer;
    }

    public Dockerfile visitCommandLine(Dockerfile.CommandLine commandLine, P p) {
        return commandLine;
    }

    public Dockerfile visitShellForm(Dockerfile.ShellForm shellForm, P p) {
        return shellForm;
    }

    public Dockerfile visitExecForm(Dockerfile.ExecForm execForm, P p) {
        return execForm;
    }

    public Dockerfile visitFlag(Dockerfile.Flag flag, P p) {
        return flag;
    }

    public Dockerfile visitArgument(Dockerfile.Argument argument, P p) {
        return argument;
    }

    public Dockerfile visitPlainText(Dockerfile.PlainText plainText, P p) {
        return plainText;
    }

    public Dockerfile visitQuotedString(Dockerfile.QuotedString quotedString, P p) {
        return quotedString;
    }

    public Dockerfile visitEnvironmentVariable(Dockerfile.EnvironmentVariable environmentVariable, P p) {
        return environmentVariable;
    }
}
