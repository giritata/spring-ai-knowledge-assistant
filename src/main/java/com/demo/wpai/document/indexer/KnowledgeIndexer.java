package com.demo.wpai.document.indexer;

import com.demo.wpai.codebase.DocumentScanner;
import com.demo.wpai.codebase.model.KnowledgeDocument;
import com.demo.wpai.codebase.reader.DocumentReader;
import com.demo.wpai.document.factory.DocumentReaderFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class KnowledgeIndexer {

    private final DocumentScanner scanner;

    private final DocumentReaderFactory readerFactory;

    public KnowledgeIndexer(
            DocumentScanner scanner,
            DocumentReaderFactory readerFactory) {

        this.scanner = scanner;
        this.readerFactory = readerFactory;

    }

    public List<KnowledgeDocument> index(Path projectPath)
            throws IOException {

        List<Path> files =
                scanner.scan(projectPath);

        List<KnowledgeDocument> documents =
                new ArrayList<>();

        for (Path file : files) {

            DocumentReader reader =
                    readerFactory.getReader(file);

            KnowledgeDocument document =
                    reader.read(file);

            documents.add(document);

        }

        return documents;

    }

}
