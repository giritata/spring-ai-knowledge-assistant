package tech.nasr.document.prompt;

import tech.nasr.document.model.KnowledgeChunk;
import tech.nasr.document.retrieval.SearchResult;
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
