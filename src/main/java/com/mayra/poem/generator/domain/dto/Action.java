package com.mayra.poem.generator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Action {
    private final String[] words;
    private final String[] associatedRules;
}
