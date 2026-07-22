package com.demo.wpai.service;

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
                "Explain OwnerController.";

        // When
        String answer =
                codeExplanationService.explain(question);

        // Then
        assertThat(answer)
                .isNotBlank();

        System.out.println(answer);
    }

}
