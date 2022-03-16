package com.mayra.poem.generator.configuration;

import com.mayra.poem.generator.domain.dto.Action;
import com.mayra.poem.generator.domain.dto.Rule;
import com.mayra.poem.generator.util.RuleConstants;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@NoArgsConstructor
@Configuration
public class InitializationConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializationConfig.class);
    private static final List<String> KEYWORDS = List.of(RuleConstants.KEYWORD_LINE_BREAK,
        RuleConstants.KEYWORD_END);

    @Bean("poemRules")
    public Map<String, Rule> getRules() {
        var properties = getPropertiesFromFile();
        var rules = new HashMap<String, Rule>();
        if (!properties.isEmpty()) {
            properties.forEach(
                (key, value) -> rules.put((String) key, buildRule((String) key, (String) value)));
        }
        return rules;
    }

    private static Properties getPropertiesFromFile() {
        var properties = new Properties();
        try {
            var file = ResourceUtils.getFile("classpath:rules.txt");
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            LOGGER.error("Fail read the rules file", e);
        }
        return properties;
    }

    private static Rule buildRule(String ruleId, String data) {
        var actions = data.split(RuleConstants.ACTIONS_SEPARATOR);
        var ruleActions = new ArrayList<Action>();
        for (String actionData : actions) {
            var action = buildAction(actionData);
            ruleActions.add(action);
        }
        return new Rule(ruleId, ruleActions.toArray(new Action[0]));
    }

    private static Action buildAction(String actionData) {
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
