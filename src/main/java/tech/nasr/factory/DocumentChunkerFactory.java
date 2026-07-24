package tech.nasr.factory;

import tech.nasr.document.chunker.DocumentChunker;
import tech.nasr.document.model.KnowledgeDocument;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentChunkerFactory {

    private final List<DocumentChunker> chunkers;

    public DocumentChunkerFactory(
            List<DocumentChunker> chunkers) {

        this.chunkers = chunkers;

    }

    public DocumentChunker getChunker(
            KnowledgeDocument document) {

        return chunkers.stream()

                .filter(chunker ->
                        chunker.supports(document))

                .findFirst()

                .orElseThrow(() ->
                        new IllegalArgumentException(

                                "No chunker for "

                                        + document.fileName()

                        ));

    }

}
