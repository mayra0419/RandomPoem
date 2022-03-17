package com.mayra.poem.generator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Rule {
    private final String id;
    private final Action[] actions;
}
