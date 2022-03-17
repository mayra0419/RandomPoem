package com.mayra.poem.generator.configuration;

import com.mayra.poem.generator.domain.dto.Rule;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@NoArgsConstructor
@Configuration
public class InitializationConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializationConfig.class);

    @Bean("poemRules")
    public Map<String, Rule> getRules() {
        return new RuleParser().apply(getPropertiesFromFile());
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

}
