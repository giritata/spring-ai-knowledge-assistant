package tech.nasr.codebase.reader;

import tech.nasr.document.model.KnowledgeDocument;

import java.io.IOException;
import java.nio.file.Path;

public interface DocumentReader {
    boolean supports(Path path);
    KnowledgeDocument read(Path path) throws IOException;
}
