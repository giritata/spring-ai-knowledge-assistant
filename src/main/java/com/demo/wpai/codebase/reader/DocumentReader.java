package com.demo.wpai.codebase.reader;

import com.demo.wpai.codebase.model.KnowledgeDocument;

import java.io.IOException;
import java.nio.file.Path;

public interface DocumentReader {
    boolean supports(Path path);
    KnowledgeDocument read(Path path) throws IOException;
}
