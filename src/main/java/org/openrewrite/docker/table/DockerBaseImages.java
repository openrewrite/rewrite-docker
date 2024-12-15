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
package org.openrewrite.docker.table;

import lombok.Value;
import org.openrewrite.Column;
import org.openrewrite.DataTable;
import org.openrewrite.Recipe;

public class DockerBaseImages extends DataTable<DockerBaseImages.Row> {

    public DockerBaseImages(Recipe recipe) {
        super(recipe,
                "Uses of docker images as bases",
                "Records the `FROM` block of Dockerfiles.");
    }

    @Value
    public static class Row {
        @Column(displayName = "Image name",
                description = "The full name of the image.")
        String imageName;

        @Column(displayName = "Tag",
                description = "The tag, if any. If no tag is specified, this will be empty.")
        String tag;
    }
}
