package com.mayra.poem.generator.controller;

import com.mayra.poem.generator.domain.dto.Rule;
import com.mayra.poem.generator.service.BuildRandomPoemService;
import com.mayra.poem.generator.service.GetRulesService;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomPoemController {

    private final GetRulesService readRulesService;
    private final BuildRandomPoemService buildRandomPoemService;

    public RandomPoemController(GetRulesService readRulesService,
        BuildRandomPoemService buildRandomPoemService) {
        this.readRulesService = readRulesService;
        this.buildRandomPoemService = buildRandomPoemService;
    }

    @GetMapping("/rules")
    public ResponseEntity<Map<String, Rule>> getRules() {
        var rules = readRulesService.execute();
        return ResponseEntity.ok(rules);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRandomPoem() {
        var randomPoem = buildRandomPoemService.execute();
        return ResponseEntity.ok(randomPoem);
    }
}
