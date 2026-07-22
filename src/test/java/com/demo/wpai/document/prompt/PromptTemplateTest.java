package com.demo.wpai.document.prompt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PromptTemplateTest {

    @Autowired
    private PromptLoader promptLoader;

    @Test
    void shouldRenderPromptVariables() {

        // Given
        String template =
                promptLoader.loadTaskPrompt("explain-code");

        PromptTemplate prompt =
                new PromptTemplate(template);

        // When
        String rendered = prompt.render(
                Map.of(
                        "context", "class Customer {}",
                        "question", "Explain this class"));

        // Then
        assertThat(rendered)
                .contains("class Customer {}")
                .contains("Explain this class")
                .doesNotContain("{{context}}")
                .doesNotContain("{{question}}");
    }
}
