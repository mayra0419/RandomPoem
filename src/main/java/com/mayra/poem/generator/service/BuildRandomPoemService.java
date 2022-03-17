package com.mayra.poem.generator.service;

import com.mayra.poem.generator.domain.dto.Action;
import com.mayra.poem.generator.domain.dto.Rule;
import com.mayra.poem.generator.util.RuleConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Stream;

@Service
public class BuildRandomPoemService {

    private static final String RULE_FOR_STARTING_A_POEM = "POEM";
    private final Map<String, Rule> poemRules;

    public BuildRandomPoemService(@Qualifier("poemRules") Map<String, Rule> poemRules) {
        this.poemRules = poemRules;
    }

    public String execute() {
        var firstRule = poemRules.get(RULE_FOR_STARTING_A_POEM);
        if (null == firstRule) {
            throw new ExceptionInInitializerError(
                "There is an error with the initial configuration");
        }
        var poemBuilder = new StringBuilder();
        executeRule(firstRule, poemBuilder);
        return poemBuilder.toString().trim();
    }

    private void executeRule(Rule rule, StringBuilder poemBuilder) {
        Stream.of(rule.getActions()).forEach(action -> executeAction(action, poemBuilder));
    }

    private void executeAction(Action action, StringBuilder poemBuilder) {
        if (action.isWordAction()) {
            poemBuilder.append(action.randomWord()).append(" ");
        } else if (action.isRefAction()) {
            var randomRef = action.randomRef();
            if (RuleConstants.KEYWORD_END.equals(randomRef)) {
                poemBuilder.append("");
            } else if (RuleConstants.KEYWORD_LINE_BREAK.equals(randomRef)) {
                poemBuilder.append(System.lineSeparator());
            } else {
                var selectedRule = poemRules.get(randomRef);
                executeRule(selectedRule, poemBuilder);
            }
        } else {
            throw new IllegalArgumentException(String.format("Action not supported: %s", action));
        }
    }

}
