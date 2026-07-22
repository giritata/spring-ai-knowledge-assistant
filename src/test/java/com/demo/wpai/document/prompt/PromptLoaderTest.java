package com.demo.wpai.document.prompt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PromptLoaderTest {

    @Autowired
    private PromptLoader promptLoader;

    @Test
    void shouldLoadExplainCodePrompt() {

        String prompt =
                promptLoader.loadTaskPrompt("explain-code");

        assertThat(prompt)
                .isNotBlank()
                .contains("{{context}}");

    }
}
