package com.demo.wpai.service;

import com.demo.wpai.ai.prompt.Audience;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CodeExplanationServiceTest {

    @Autowired
    private CodeExplanationService codeExplanationService;

    @Test
    void shouldExplainCode() {

        // Given
        String question =
                "Explain how Veterinarian data is stored.";

        // When
        String answer =
                codeExplanationService.explain(question, Audience.TEST_ENGINEER);

        // Then
        assertThat(answer)
                .isNotBlank();

        System.out.println(answer);
    }


}
