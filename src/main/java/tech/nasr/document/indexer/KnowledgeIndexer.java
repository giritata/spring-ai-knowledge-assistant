package tech.nasr.document.indexer;

import tech.nasr.codebase.DocumentScanner;
import tech.nasr.document.chunker.DocumentChunker;
import tech.nasr.document.model.KnowledgeChunk;
import tech.nasr.document.model.KnowledgeDocument;
import tech.nasr.codebase.reader.DocumentReader;
import tech.nasr.factory.DocumentReaderFactory;
import tech.nasr.document.store.KnowledgeStore;
import tech.nasr.factory.DocumentChunkerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class KnowledgeIndexer {

    private final DocumentScanner scanner;

    private final DocumentReaderFactory readerFactory;

    private final DocumentChunkerFactory chunkerFactory;

    private final KnowledgeStore knowledgeStore;

    public KnowledgeIndexer(
            DocumentScanner scanner,
            DocumentReaderFactory readerFactory,
            DocumentChunkerFactory chunkerFactory,
            KnowledgeStore knowledgeStore) {

        this.scanner = scanner;
        this.readerFactory = readerFactory;
        this.chunkerFactory = chunkerFactory;
        this.knowledgeStore = knowledgeStore;
    }

    public List<KnowledgeChunk> index(Path projectPath)
            throws IOException {

        List<Path> files =
                scanner.scan(projectPath);

        List<KnowledgeChunk> chunks =
                new ArrayList<>();

        for (Path file : files) {

            DocumentReader reader =
                    readerFactory.getReader(file);

            KnowledgeDocument document =
                    reader.read(file);

            DocumentChunker chunker = chunkerFactory.getChunker(document);
            chunks.addAll(chunker.chunk(document));

        }

        return chunks;

    }

}
