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
package org.openrewrite.docker.internal;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * Represents a Heredoc structure in the context of defining content using
 * a unique indicator, name, and optional redirection target.
 * <p>
 * The Heredoc class provides a representation of a heredoc-style block with
 * key attributes common in configuration or scripting contexts.
 * <p>
 * This class is immutable and uses fluent accessors for its properties.
 */
@Value
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Accessors(fluent = true)
public class Heredoc {
    String indicator;
    String name;
    String redirectionTo;
}
