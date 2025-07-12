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
import java.util.stream.Collectors;

public class VolumeInstructionParser extends CommandLikeInstructionParser {
    @Override
    public String instructionName() {
        return "VOLUME";
    }

    @Override
    public Docker.Instruction parse(String line, ParserState state) {
        Elements elements = parseElements(line, state);

        List<DockerRightPadded<Docker.Literal>> literals = ParserUtils.parseLiterals(elements.getForm(), elements.getContent(), state);

        return new Docker.Volume(Tree.randomId(), elements.getForm(), state.prefix(), elements.getExecFormPrefix(),
                literals.stream()
                        .map(d -> d.getElement().withTrailing(Space.append(d.getElement().getTrailing(), d.getAfter())))
                        .collect(Collectors.toList()),
                elements.getExecFormSuffix(), Markers.EMPTY, Space.EMPTY);
    }
}
