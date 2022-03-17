package com.mayra.poem.generator.configuration;

import com.mayra.poem.generator.domain.dto.Action;
import com.mayra.poem.generator.domain.dto.Rule;
import com.mayra.poem.generator.util.RuleConstants;

import java.util.*;
import java.util.function.Function;

/**
 * @author Jorge Gomez (jorge.gomez@kane-group.com)
 * Created on 17/03/22
 */
public class RuleParser implements Function<Properties, Map<String, Rule>> {

    private static final List<String> KEYWORDS = List.of(RuleConstants.KEYWORD_LINE_BREAK,
            RuleConstants.KEYWORD_END);

    public Map<String, Rule> apply(Properties properties) {
        var rules = new HashMap<String, Rule>();
        properties.forEach(
                (key, value) -> rules.put((String) key, buildRule((String) key, (String) value)));
        return rules;
    }

    private Rule buildRule(String ruleId, String data) {
        var actions = data.split(RuleConstants.ACTIONS_SEPARATOR);
        var ruleActions = new ArrayList<Action>();
        for (String actionData : actions) {
            var action = buildAction(actionData);
            ruleActions.add(action);
        }
        return new Rule(ruleId, ruleActions.toArray(new Action[0]));
    }

    private Action buildAction(String actionData) {
        var associatedRules = new ArrayList<String>();
        var words = new ArrayList<String>();
        var options = actionData.split(RuleConstants.OPTIONS_SEPARATOR);
        for (String option : options) {
            if ((option.startsWith("<") && option.endsWith(">")) || KEYWORDS.contains(option)) {
                associatedRules.add(option.replace("<", "").replace(">", ""));
            } else {
                words.add(option);
            }
        }
        return new Action(words.toArray(new String[0]), associatedRules.toArray(new String[0]));
    }

}
