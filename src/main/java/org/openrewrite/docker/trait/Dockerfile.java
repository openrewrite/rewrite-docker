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

import lombok.Value;
import org.openrewrite.Cursor;
import org.openrewrite.docker.DockerImageVersion;
import org.openrewrite.text.PlainText;
import org.openrewrite.trait.Reference;
import org.openrewrite.trait.Trait;

import java.util.ArrayList;
import java.util.List;

@Value
public class Dockerfile implements Trait<PlainText> {
    Cursor cursor;

    public List<DockerImageVersion> getFroms() {
        List<DockerImageVersion> froms = new ArrayList<>();
        for (String line : getTree().getText().split("\\R")) {
            if (line.startsWith("FROM")) {
                String[] imageVersionStr = line.substring("FROM".length()).trim().split(":");
                froms.add(new DockerImageVersion(
                        imageVersionStr[0],
                        imageVersionStr.length > 1 ? imageVersionStr[1].split(" ")[0] : null
                ));
            }
        }
        return froms;
    }

    public static class Matcher implements Reference.Matcher {

        @Override
        public boolean matchesReference(Reference reference) {
            return reference.getKind().equals(Reference.Kind.IMAGE);
        }

        @Override
        public Reference.Renamer createRenamer(String newName) {
            return reference -> newName;
        }
    }
}
