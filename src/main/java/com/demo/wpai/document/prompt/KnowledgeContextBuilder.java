package com.demo.wpai.document.prompt;

import com.demo.wpai.document.model.KnowledgeChunk;
import com.demo.wpai.document.search.SearchResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KnowledgeContextBuilder {

    public String build(List<SearchResult> results) {

        StringBuilder builder = new StringBuilder();

        for (SearchResult result : results) {

            KnowledgeChunk chunk =
                    result.chunk().chunk();

            builder

                    .append("Source: ")
                    .append(chunk.source())
                    .append("\n")

                    .append("Title: ")
                    .append(chunk.title())
                    .append("\n")

                    .append("Type: ")
                    .append(chunk.symbolType())
                    .append("\n\n")

                    .append(chunk.content())
                    .append("\n")

                    .append("----------------------------------------")
                    .append("\n\n");
        }

        return builder.toString();
    }

}
