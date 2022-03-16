package com.mayra.poem.generator.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RuleConstants {

    public static final String OPTIONS_SEPARATOR = "\\|";
    public static final String ACTIONS_SEPARATOR = "\\ ";
    public static final String KEYWORD_LINE_BREAK = "$LINEBREAK";
    public static final String KEYWORD_END = "$END";
}
