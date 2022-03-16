package com.mayra.poem.generator.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestApplication.class})
@WebAppConfiguration
class RandomPoemControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testShouldCreateBeansWhenContextAreOk() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assertions.assertNotNull(servletContext, "context is null");
        Assertions.assertTrue(servletContext instanceof MockServletContext, "wrong context type");
        Assertions.assertNotNull(webApplicationContext.getBean("randomPoemController"),
            "controller bean wasn't created");
        Assertions.assertNotNull(webApplicationContext.getBean("poemRules"),
            "poem rules bean wasn't created");
    }

    @Test
    void testShouldGetRandomPoem() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andReturn();

        Assertions.assertNotNull(mvcResult.getResponse(), "response is null");
        Assertions.assertEquals("text/plain;charset=UTF-8",
            mvcResult.getResponse().getContentType(), "wrong content type");
        var randomPoem = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals(5, randomPoem.split(System.lineSeparator()).length,
            "wrong poem structure");
    }
}