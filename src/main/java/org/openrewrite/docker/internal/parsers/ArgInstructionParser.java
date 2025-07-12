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
package org.openrewrite.docker.internal.parsers;

import org.openrewrite.Tree;
import org.openrewrite.docker.internal.ParserState;
import org.openrewrite.docker.internal.ParserUtils;
import org.openrewrite.docker.tree.Docker;
import org.openrewrite.docker.tree.DockerRightPadded;
import org.openrewrite.docker.tree.Space;
import org.openrewrite.marker.Markers;

import java.util.List;

public class ArgInstructionParser implements InstructionParser {
    @Override
    public String instructionName() {
        return "ARG";
    }

    @Override
    public Docker.Instruction parse(String line, ParserState state) {
        List<DockerRightPadded<Docker.KeyArgs>> args = ParserUtils.parseArgs(line, state);
        return new Docker.Arg(Tree.randomId(), state.prefix(), args, Markers.EMPTY, Space.EMPTY);
    }
}
