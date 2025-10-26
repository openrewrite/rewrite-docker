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

import org.openrewrite.docker.tree.Dockerfile;
import org.openrewrite.docker.tree.Space;
import org.openrewrite.internal.ListUtils;

public class DockerfileIsoVisitor<P> extends DockerfileVisitor<P> {

    public Space visitSpace(Space space, P p) {
        return space;
    }

    @Override
    public Dockerfile visitDocument(Dockerfile.Document document, P p) {
        Dockerfile.Document d = document;
        d = d.withPrefix(visitSpace(d.getPrefix(), p));
        d = d.withMarkers(visitMarkers(d.getMarkers(), p));
        return d.withInstructions(ListUtils.map(d.getInstructions(), inst -> (Dockerfile.Instruction) visit(inst, p)));
    }

    @Override
    public Dockerfile visitFrom(Dockerfile.From from, P p) {
        Dockerfile.From f = from;
        f = f.withPrefix(visitSpace(f.getPrefix(), p));
        f = f.withMarkers(visitMarkers(f.getMarkers(), p));
        if (f.getFlags() != null) {
            f = f.withFlags(ListUtils.map(f.getFlags(), flag -> (Dockerfile.Flag) visit(flag, p)));
        }
        f = f.withImage((Dockerfile.Argument) visit(f.getImage(), p));
        if (f.getAs() != null) {
            f = f.withAs(visitFromAs(f.getAs(), p));
        }
        return f;
    }

    public Dockerfile.From.As visitFromAs(Dockerfile.From.As as, P p) {
        Dockerfile.From.As a = as;
        a = a.withPrefix(visitSpace(a.getPrefix(), p));
        a = a.withMarkers(visitMarkers(a.getMarkers(), p));
        return a.withName((Dockerfile.Argument) visit(a.getName(), p));
    }

    @Override
    public Dockerfile visitRun(Dockerfile.Run run, P p) {
        Dockerfile.Run r = run;
        r = r.withPrefix(visitSpace(r.getPrefix(), p));
        r = r.withMarkers(visitMarkers(r.getMarkers(), p));
        if (r.getFlags() != null) {
            r = r.withFlags(ListUtils.map(r.getFlags(), flag -> (Dockerfile.Flag) visit(flag, p)));
        }
        return r.withCommandLine((Dockerfile.CommandLine) visit(r.getCommandLine(), p));
    }

    @Override
    public Dockerfile visitCopy(Dockerfile.Copy copy, P p) {
        Dockerfile.Copy c = copy;
        c = c.withPrefix(visitSpace(c.getPrefix(), p));
        c = c.withMarkers(visitMarkers(c.getMarkers(), p));
        if (c.getFlags() != null) {
            c = c.withFlags(ListUtils.map(c.getFlags(), flag -> (Dockerfile.Flag) visit(flag, p)));
        }
        c = c.withSources(ListUtils.map(c.getSources(), source -> (Dockerfile.Argument) visit(source, p)));
        return c.withDestination((Dockerfile.Argument) visit(c.getDestination(), p));
    }

    @Override
    public Dockerfile visitArg(Dockerfile.Arg arg, P p) {
        Dockerfile.Arg a = arg;
        a = a.withPrefix(visitSpace(a.getPrefix(), p));
        a = a.withMarkers(visitMarkers(a.getMarkers(), p));
        a = a.withName((Dockerfile.Argument) visit(a.getName(), p));
        if (a.getValue() != null) {
            a = a.withValue((Dockerfile.Argument) visit(a.getValue(), p));
        }
        return a;
    }

    @Override
    public Dockerfile visitCommandLine(Dockerfile.CommandLine commandLine, P p) {
        Dockerfile.CommandLine cl = commandLine;
        cl = cl.withPrefix(visitSpace(cl.getPrefix(), p));
        cl = cl.withMarkers(visitMarkers(cl.getMarkers(), p));
        return cl.withForm((Dockerfile.CommandForm) visit(cl.getForm(), p));
    }

    @Override
    public Dockerfile visitShellForm(Dockerfile.ShellForm shellForm, P p) {
        Dockerfile.ShellForm sf = shellForm;
        sf = sf.withPrefix(visitSpace(sf.getPrefix(), p));
        sf = sf.withMarkers(visitMarkers(sf.getMarkers(), p));
        return sf.withArguments(ListUtils.map(sf.getArguments(), arg -> (Dockerfile.Argument) visit(arg, p)));
    }

    @Override
    public Dockerfile visitExecForm(Dockerfile.ExecForm execForm, P p) {
        Dockerfile.ExecForm ef = execForm;
        ef = ef.withPrefix(visitSpace(ef.getPrefix(), p));
        ef = ef.withMarkers(visitMarkers(ef.getMarkers(), p));
        return ef.withArguments(ListUtils.map(ef.getArguments(), arg -> (Dockerfile.Argument) visit(arg, p)));
    }

    @Override
    public Dockerfile visitFlag(Dockerfile.Flag flag, P p) {
        Dockerfile.Flag f = flag;
        f = f.withPrefix(visitSpace(f.getPrefix(), p));
        f = f.withMarkers(visitMarkers(f.getMarkers(), p));
        if (f.getValue() != null) {
            f = f.withValue((Dockerfile.Argument) visit(f.getValue(), p));
        }
        return f;
    }

    @Override
    public Dockerfile visitArgument(Dockerfile.Argument argument, P p) {
        Dockerfile.Argument a = argument;
        a = a.withPrefix(visitSpace(a.getPrefix(), p));
        a = a.withMarkers(visitMarkers(a.getMarkers(), p));
        return a.withContents(ListUtils.map(a.getContents(), content -> (Dockerfile.ArgumentContent) visit(content, p)));
    }

    @Override
    public Dockerfile visitPlainText(Dockerfile.PlainText plainText, P p) {
        Dockerfile.PlainText pt = plainText;
        pt = pt.withPrefix(visitSpace(pt.getPrefix(), p));
        return pt.withMarkers(visitMarkers(pt.getMarkers(), p));
    }

    @Override
    public Dockerfile visitQuotedString(Dockerfile.QuotedString quotedString, P p) {
        Dockerfile.QuotedString qs = quotedString;
        qs = qs.withPrefix(visitSpace(qs.getPrefix(), p));
        return qs.withMarkers(visitMarkers(qs.getMarkers(), p));
    }

    @Override
    public Dockerfile visitEnvironmentVariable(Dockerfile.EnvironmentVariable environmentVariable, P p) {
        Dockerfile.EnvironmentVariable ev = environmentVariable;
        ev = ev.withPrefix(visitSpace(ev.getPrefix(), p));
        return ev.withMarkers(visitMarkers(ev.getMarkers(), p));
    }
}
