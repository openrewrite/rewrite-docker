/*
 * Copyright 2024 the original author or authors.
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
package org.openrewrite.docker.trait;

import org.openrewrite.trait.Reference;

public class ImageMatcher implements Reference.Matcher {

    @Override
    public boolean matchesReference(Reference reference) {
        return reference.getKind() == Reference.Kind.IMAGE;
    }

    @Override
    public Reference.Renamer createRenamer(String newName) {
        return reference -> newName;
    }
}