/*
 * Copyright 2025 the original author or authors.
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

// $antlr-format alignTrailingComments true, columnLimit 150, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine true, allowShortBlocksOnASingleLine true, minEmptyLines 0, alignSemicolons ownLine
// $antlr-format alignColons trailing, singleLineOverrulesHangingColon true, alignLexerCommands true, alignLabels true, alignTrailers true

lexer grammar DockerfileLexer;

// Parser directives (must be at the beginning of file)
PARSER_DIRECTIVE : '#' WS_CHAR* [a-zA-Z_]+ WS_CHAR* '=' WS_CHAR* ~[\r\n]* NEWLINE_CHAR;

// Comments (after parser directives)
COMMENT : '#' ~[\r\n]*;

// Instructions (case-insensitive)
FROM       : [Ff][Rr][Oo][Mm];
RUN        : [Rr][Uu][Nn];
CMD        : [Cc][Mm][Dd];
LABEL      : [Ll][Aa][Bb][Ee][Ll];
EXPOSE     : [Ee][Xx][Pp][Oo][Ss][Ee];
ENV        : [Ee][Nn][Vv];
ADD        : [Aa][Dd][Dd];
COPY       : [Cc][Oo][Pp][Yy];
ENTRYPOINT : [Ee][Nn][Tt][Rr][Yy][Pp][Oo][Ii][Nn][Tt];
VOLUME     : [Vv][Oo][Ll][Uu][Mm][Ee];
USER       : [Uu][Ss][Ee][Rr];
WORKDIR    : [Ww][Oo][Rr][Kk][Dd][Ii][Rr];
ARG        : [Aa][Rr][Gg];
ONBUILD    : [Oo][Nn][Bb][Uu][Ii][Ll][Dd];
STOPSIGNAL : [Ss][Tt][Oo][Pp][Ss][Ii][Gg][Nn][Aa][Ll];
HEALTHCHECK: [Hh][Ee][Aa][Ll][Tt][Hh][Cc][Hh][Ee][Cc][Kk];
SHELL      : [Ss][Hh][Ee][Ll][Ll];
MAINTAINER : [Mm][Aa][Ii][Nn][Tt][Aa][Ii][Nn][Ee][Rr];

// Special keywords
AS         : [Aa][Ss];

// Heredoc start - captures <<EOF or <<-EOF and switches to HEREDOC mode
HEREDOC_START : '<<' '-'? [A-Za-z_][A-Za-z0-9_]* -> pushMode(HEREDOC_MODE);

// Line continuation
LINE_CONTINUATION : '\\' [ \t]* NEWLINE_CHAR;

// JSON array delimiters (for exec form)
LBRACKET : '[';
RBRACKET : ']';
COMMA    : ',';

// Assignment and flags
EQUALS     : '=';
DASH_DASH  : '--';

// String literals
DOUBLE_QUOTED_STRING : '"' ( ESCAPE_SEQUENCE | ~["\\\r\n] )* '"';
SINGLE_QUOTED_STRING : '\'' ( ESCAPE_SEQUENCE | ~['\\\r\n] )* '\'';

fragment ESCAPE_SEQUENCE
    : '\\' [nrt"'\\$]
    | '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    | '\\' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;

fragment HEX_DIGIT : [0-9a-fA-F];

// Environment variable reference
ENV_VAR : '$' '{' [a-zA-Z_][a-zA-Z0-9_]* ( ':-' | ':+' | ':' )? ~[}]* '}' | '$' [a-zA-Z_][a-zA-Z0-9_]*;

// Unquoted text (arguments, file paths, etc.)
// This should be after more specific tokens
UNQUOTED_TEXT : ( ~[ \t\r\n\\"'$[\],=] | '\\' . )+;

// Whitespace (preserve for LST)
WS : WS_CHAR+ ;

fragment WS_CHAR : [ \t];

// Newlines (preserve for LST)
NEWLINE : NEWLINE_CHAR+;

fragment NEWLINE_CHAR : [\r\n];

// HEREDOC mode - captures content until closing marker
mode HEREDOC_MODE;

// Initial newline after opening marker
HEREDOC_NEWLINE : NEWLINE_CHAR+ ;

// Closing marker on its own line - must match identifier alone on a line
HEREDOC_END : [A-Za-z_][A-Za-z0-9_]* -> popMode;

// Content line in heredoc (anything except newline)
HEREDOC_CONTENT : ~[\r\n]+ ;
