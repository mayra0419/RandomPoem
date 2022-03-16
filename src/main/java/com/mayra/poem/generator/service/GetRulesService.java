package com.mayra.poem.generator.service;

import com.mayra.poem.generator.domain.dto.Rule;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GetRulesService {

    private final Map<String, Rule> poemRules;

    public GetRulesService(@Qualifier("poemRules") Map<String, Rule> poemRules) {
        this.poemRules = poemRules;
    }

    public Map<String, Rule> execute() {
        return new HashMap<>(poemRules);
    }
}
