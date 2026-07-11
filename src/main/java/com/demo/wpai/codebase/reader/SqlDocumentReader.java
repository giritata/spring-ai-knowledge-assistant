package com.demo.wpai.codebase.reader;

import com.demo.wpai.document.model.DocumentType;
import com.demo.wpai.document.model.KnowledgeDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class SqlDocumentReader implements DocumentReader {
    @Override
    public boolean supports(Path path) {

        return path.toString()
                .toLowerCase()
                .endsWith(".sql");

    }

    @Override
    public KnowledgeDocument read(Path path)
            throws IOException {

        String content =
                Files.readString(path);

        return new KnowledgeDocument(

                path.getFileName().toString(),

                path,

                DocumentType.SQL,

                content,

                Files.size(path)

        );
    }
}
