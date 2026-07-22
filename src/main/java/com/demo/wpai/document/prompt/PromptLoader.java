package com.demo.wpai.document.prompt;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class PromptLoader {

    public String loadSystemPrompt() {

        try {

            ClassPathResource resource =
                    new ClassPathResource("prompts/banking-system-prompt.txt");

            return new String(
                    resource.getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    public String load(String promptName) {

        try {

            ClassPathResource resource =
                    new ClassPathResource(
                            "prompts/" + promptName + ".md");

            return new String(
                    resource.getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8);

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to load prompt: " + promptName,
                    ex);

        }
    }

}