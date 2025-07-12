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
import org.openrewrite.docker.internal.StringWithPadding;
import org.openrewrite.docker.tree.Docker;
import org.openrewrite.docker.tree.Quoting;
import org.openrewrite.docker.tree.Space;
import org.openrewrite.marker.Markers;

public class MaintainerInstructionParser implements InstructionParser {
    @Override
    public String instructionName() {
        return "MAINTAINER";
    }

    @Override
    public Docker.Instruction parse(String line, ParserState state) {
        // TODO: quoting
        Quoting quoting = Quoting.UNQUOTED;
        StringWithPadding stringWithPadding = StringWithPadding.of(line);
        return new Docker.Maintainer(Tree.randomId(), quoting, state.prefix(),
                Docker.Literal.build(stringWithPadding.content())
                        .withPrefix(stringWithPadding.prefix())
                        .withTrailing(Space.append(stringWithPadding.suffix(), state.rightPadding())),
                Markers.EMPTY, Space.EMPTY);
    }
}
