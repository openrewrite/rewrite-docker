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

// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

parser grammar DockerfileParser;

options {
    tokenVocab = DockerfileLexer;
}

// Root rule
dockerfile
    : ( parserDirective | NEWLINE | COMMENT )* ( instruction ( NEWLINE+ | LINE_CONTINUATION | COMMENT )* )* EOF
    ;

parserDirective
    : PARSER_DIRECTIVE
    ;

instruction
    : fromInstruction
    | runInstruction
    | cmdInstruction
    | labelInstruction
    | exposeInstruction
    | envInstruction
    | addInstruction
    | copyInstruction
    | entrypointInstruction
    | volumeInstruction
    | userInstruction
    | workdirInstruction
    | argInstruction
    | onbuildInstruction
    | stopsignalInstruction
    | healthcheckInstruction
    | shellInstruction
    | maintainerInstruction
    ;

fromInstruction
    : FROM WS+ ( flags WS+ )? imageName ( WS+ AS WS+ stageName )? trailingComment?
    ;

runInstruction
    : RUN WS+ ( flags WS+ )? ( execForm | shellForm | heredoc ) trailingComment?
    ;

cmdInstruction
    : CMD WS+ ( execForm | shellForm ) trailingComment?
    ;

labelInstruction
    : LABEL WS+ labelPairs trailingComment?
    ;

exposeInstruction
    : EXPOSE WS+ portList trailingComment?
    ;

envInstruction
    : ENV WS+ envPairs trailingComment?
    ;

addInstruction
    : ADD WS+ ( flags WS+ )? ( heredoc | sourceList WS+ destination ) trailingComment?
    ;

copyInstruction
    : COPY WS+ ( flags WS+ )? ( heredoc | sourceList WS+ destination ) trailingComment?
    ;

entrypointInstruction
    : ENTRYPOINT WS+ ( execForm | shellForm ) trailingComment?
    ;

volumeInstruction
    : VOLUME WS+ ( jsonArray | pathList ) trailingComment?
    ;

userInstruction
    : USER WS+ userSpec trailingComment?
    ;

workdirInstruction
    : WORKDIR WS+ path trailingComment?
    ;

argInstruction
    : ARG WS+ argName ( EQUALS argValue )? trailingComment?
    ;

onbuildInstruction
    : ONBUILD WS+ instruction trailingComment?
    ;

stopsignalInstruction
    : STOPSIGNAL WS+ signal trailingComment?
    ;

healthcheckInstruction
    : HEALTHCHECK WS+ ( UNQUOTED_TEXT | ( flags WS+ )? cmdInstruction ) trailingComment?
    ;

shellInstruction
    : SHELL WS+ jsonArray trailingComment?
    ;

maintainerInstruction
    : MAINTAINER WS+ text trailingComment?
    ;

// Common elements
flags
    : flag ( WS+ flag )*
    ;

flag
    : DASH_DASH flagName ( EQUALS flagValue )?
    ;

flagName
    : UNQUOTED_TEXT
    | FROM  // Allow 'from' as flag name (e.g., --from=builder in COPY)
    | AS    // Allow 'as' as flag name
    ;

flagValue
    : flagValueElement+
    ;

flagValueElement
    : UNQUOTED_TEXT | EQUALS | DOUBLE_QUOTED_STRING | SINGLE_QUOTED_STRING
    ;

execForm
    : jsonArray
    ;

shellForm
    : text
    ;

heredoc
    : HEREDOC_START ( WS+ path )? NEWLINE ( heredocLine )* heredocEnd
    ;

heredocLine
    : text? NEWLINE
    ;

heredocEnd
    : UNQUOTED_TEXT
    ;

jsonArray
    : LBRACKET JSON_WS? jsonArrayElements? JSON_WS? JSON_RBRACKET
    ;

jsonArrayElements
    : jsonString ( JSON_WS? JSON_COMMA JSON_WS? jsonString )*
    ;

jsonString
    : JSON_STRING
    ;

imageName
    : text
    ;

stageName
    : UNQUOTED_TEXT
    ;

labelPairs
    : labelPair ( WS+ labelPair )*
    ;

labelPair
    : labelKey EQUALS labelValue
    ;

labelKey
    : UNQUOTED_TEXT | DOUBLE_QUOTED_STRING | SINGLE_QUOTED_STRING
    ;

labelValue
    : UNQUOTED_TEXT | DOUBLE_QUOTED_STRING | SINGLE_QUOTED_STRING
    ;

portList
    : port ( WS+ port )*
    ;

port
    : UNQUOTED_TEXT
    ;

envPairs
    : envPair ( WS+ envPair )*
    ;

envPair
    : envKey ( EQUALS envValue | WS+ envValue )
    ;

envKey
    : UNQUOTED_TEXT
    ;

envValue
    : text
    ;

sourceList
    : source ( WS+ source )*
    ;

source
    : path
    ;

destination
    : path
    ;

path
    : text
    ;

pathList
    : path ( WS+ path )*
    ;

userSpec
    : text
    ;

argName
    : UNQUOTED_TEXT
    ;

argValue
    : text
    ;

signal
    : UNQUOTED_TEXT
    ;

text
    : textElement+
    ;

textElement
    : UNQUOTED_TEXT
    | DOUBLE_QUOTED_STRING
    | SINGLE_QUOTED_STRING
    | ENV_VAR
    | EQUALS  // Allow = in shell form text (e.g., ENV_VAR=value in RUN commands)
    | DASH_DASH  // Allow -- in shell form text (e.g., --option in shell commands)
    | LINE_CONTINUATION  // Allow line continuations in shell commands
    | WS
    | COMMENT  // Allow comments in heredoc content
    ;

trailingComment
    : WS* COMMENT
    ;
