package com.demo.wpai.codebase.reader;

import com.demo.wpai.document.model.DocumentType;
import com.demo.wpai.document.model.KnowledgeDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class XmlDocumentReader implements DocumentReader {

    @Override
    public boolean supports(Path path) {

        return path.toString()
                .toLowerCase()
                .endsWith(".xml");

    }

    @Override
    public KnowledgeDocument read(Path path) throws IOException {

        return new KnowledgeDocument(

                path.getFileName().toString(),

                path,

                DocumentType.XML,

                Files.readString(path),

                Files.size(path)

        );

    }
}
