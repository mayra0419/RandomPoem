package com.mayra.poem.generator.service;

import com.mayra.poem.generator.configuration.RuleParser;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class BuildRandomPoemServiceTest {

    @Test
    void generatePoem() {
        Properties properties = new Properties();
        properties.put("POEM", "<LINE> <LINE> <LINE> <LINE> <LINE>");
        properties.put("LINE", "<NOUN>|<PREPOSITION>|<PRONOUN> $LINEBREAK");
        properties.put("ADJECTIVE", "black|white|dark|light|bright|murky|muddy|clear <NOUN>|<ADJECTIVE>|$END");
        properties.put("NOUN", "heart|sun|moon|thunder|fire|time|wind|sea|river|flavor|wave|willow|rain|tree|flower|field|meadow|pasture|harvest|water|father|mother|brother|sister <VERB>|<PREPOSITION>|$END");
        properties.put("PRONOUN", "my|your|his|her <NOUN>|<ADJECTIVE>");
        properties.put("VERB", "runs|walks|stands|climbs|crawls|flows|flies|transcends|ascends|descends|sinks <PREPOSITION>|<PRONOUN>|$END");
        properties.put("PREPOSITION", "above|across|against|along|among|around|before|behind|beneath|beside|between|beyond|during|inside|onto|outside|under|underneath|upon|with|without|through <NOUN>|<PRONOUN>|<ADJECTIVE>");
        String poem = new BuildRandomPoemService(new RuleParser().apply(properties)).execute();
        String[] poemLines = poem.split(System.lineSeparator());
        assertEquals(5, poemLines.length);
    }
}