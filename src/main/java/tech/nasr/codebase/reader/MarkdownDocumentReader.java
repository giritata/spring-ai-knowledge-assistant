package tech.nasr.codebase.reader;

import tech.nasr.document.model.DocumentType;
import tech.nasr.document.model.KnowledgeDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Component
public class MarkdownDocumentReader implements DocumentReader {
    @Override
    public boolean supports(Path path) {

        return path.toString()
                .toLowerCase()
                .endsWith(".md");

    }

    @Override
    public KnowledgeDocument read(Path path)
            throws IOException {

        String content =
                Files.readString(path);

        return new KnowledgeDocument(

                path.getFileName().toString(),

                path,

                DocumentType.MARKDOWN,

                content,

                Files.size(path)

        );
    }
}
