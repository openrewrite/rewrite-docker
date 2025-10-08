/*
 * Copyright 2022 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.docker.internal.parsers;

import org.openrewrite.Tree;
import org.openrewrite.docker.internal.ParserState;
import org.openrewrite.docker.tree.Docker;
import org.openrewrite.docker.tree.Space;
import org.openrewrite.marker.Markers;

public class CopyInstructionParser extends AddLikeInstructionParser {
    @Override
    public String instructionName() {
        return "COPY";
    }

    @Override
    public Docker.Instruction parse(String line, ParserState state) {
        Elements elements = parseElements(line, state);
        return new Docker.Copy(
                Tree.randomId(),
                state.prefix(),
                elements.getOptions(),
                elements.getSources(),
                elements.getDestination(),
                Markers.EMPTY,
                Space.EMPTY);
    }
}
