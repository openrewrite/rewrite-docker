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

@Value
@EqualsAndHashCode(callSuper = false)
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
                    } else if ("image".equals(((Yaml.Scalar) value).getValue())) {
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
