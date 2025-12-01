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

import lombok.Getter;
import lombok.Value;
import org.openrewrite.docker.internal.ParserState;
import org.openrewrite.docker.internal.StringWithPadding;
import org.openrewrite.docker.tree.Form;
import org.openrewrite.docker.tree.Space;

abstract class CommandLikeInstructionParser implements InstructionParser {
    @Value
    @Getter
    static class Elements {
        String content;
        Form form;
        Space execFormPrefix;
        Space execFormSuffix;
    }

    protected Elements parseElements(String content, ParserState state) {
        Form form = Form.SHELL;
        Space execFormPrefix = Space.EMPTY;
        Space execFormSuffix = Space.EMPTY;
        if (content.trim().startsWith("[")) {
            StringWithPadding stringWithPadding = StringWithPadding.of(content);
            content = stringWithPadding.content();
            execFormPrefix = stringWithPadding.prefix();
            content = content.substring(1, content.length() - 1);
            form = Form.EXEC;

            execFormSuffix = state.rightPadding();
        }
        return new Elements(content, form, execFormPrefix, execFormSuffix);
    }
}
