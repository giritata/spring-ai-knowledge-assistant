package com.demo.wpai.chat;

import com.demo.wpai.document.search.SearchResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromptContextBuilder {

    public String buildContext(
            List<SearchResult> results) {

        StringBuilder builder =
                new StringBuilder();

        builder.append("""
                You are answering questions
                using the following codebase.

                """);

        for (SearchResult result : results) {

            builder.append("""

                    ============================
                    Source:
                    """);

            builder.append(

                    result.chunk()
                            .chunk()
                            .source()

            );

            builder.append("""

                    Content:

                    """);

            builder.append(

                    result.chunk()
                            .chunk()
                            .content()

            );

            builder.append("\n");

        }

        return builder.toString();

    }

}