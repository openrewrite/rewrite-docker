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

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jspecify.annotations.Nullable;
import org.openrewrite.Cursor;
import org.openrewrite.trait.Reference;
import org.openrewrite.trait.SimpleTraitMatcher;
import org.openrewrite.yaml.trait.YamlReference;
import org.openrewrite.yaml.tree.Yaml;

import java.util.concurrent.atomic.AtomicBoolean;

@EqualsAndHashCode(callSuper = false)
@Value
public class YamlImageReference extends YamlReference {
    Cursor cursor;

    @Override
    public Reference.Kind getKind() {
        return Reference.Kind.IMAGE;
    }

    public static class Provider extends YamlProvider {
        private static final SimpleTraitMatcher<YamlReference> matcher = new SimpleTraitMatcher<YamlReference>() {
            private final AtomicBoolean found = new AtomicBoolean(false);

            @Override
            protected @Nullable YamlReference test(Cursor cursor) {
                Object value = cursor.getValue();
                if (value instanceof Yaml.Scalar) {
                    if (found.get()) {
                        found.set(false);
                        return new YamlImageReference(cursor);
                    }
                    if ("image".equals(((Yaml.Scalar) value).getValue())) {
                        found.set(true);
                    }
                }
                return null;
            }
        };

        @Override
        public SimpleTraitMatcher<YamlReference> getMatcher() {
            return matcher;
        }
    }
}
