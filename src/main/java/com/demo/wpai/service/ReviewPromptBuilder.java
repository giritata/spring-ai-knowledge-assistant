package com.demo.wpai.service;

import com.demo.wpai.document.search.SearchResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewPromptBuilder {

    public String build(
            String question,
            List<SearchResult> results) {

        StringBuilder builder =
                new StringBuilder();

        builder.append("""
You are a Principal Java Software Architect.

Review the supplied source code.

Focus on:

- Bugs
- Code Smells
- SOLID Principles
- Spring Boot Best Practices
- Readability
- Maintainability
- Performance
- Refactoring Opportunities

If the implementation is already good, explain why.

Question:

""");

        builder.append(question);

        builder.append("\n\n");

        builder.append("Relevant Code\n\n");

        for (SearchResult result : results) {

            builder.append("-----------------------------------\n");

            builder.append("Source : ");

            builder.append(result.chunk()
                    .chunk()
                    .source());

            builder.append("\n");

            builder.append("Title : ");

            builder.append(result.chunk()
                    .chunk()
                    .title());

            builder.append("\n\n");

            builder.append(result.chunk()
                    .chunk()
                    .content());

            builder.append("\n\n");
        }

        return builder.toString();

    }

}