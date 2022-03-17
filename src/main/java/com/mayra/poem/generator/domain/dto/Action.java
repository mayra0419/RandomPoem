package com.mayra.poem.generator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public class Action {
    private static final Random RANDOM = new Random();

    private final String[] words;
    private final String[] associatedRules;

    public boolean isWordAction() {
        return words != null && words.length > 0;
    }

    public String randomWord() {
        return words[RANDOM.nextInt(words.length)];
    }

    public boolean isRefAction() {
        return associatedRules != null && associatedRules.length > 0;
    }

    public String randomRef() {
        return associatedRules[RANDOM.nextInt(associatedRules.length)];
    }
}
