package org.openrewrite.docker.trait;

import org.openrewrite.trait.Reference;

public class ImageMatcher implements Reference.Matcher {

    @Override
    public boolean matchesReference(Reference reference) {
        return reference.getKind().equals(Reference.Kind.IMAGE);
    }

    @Override
    public Reference.Renamer createRenamer(String newName) {
        return reference -> newName;
    }
}
