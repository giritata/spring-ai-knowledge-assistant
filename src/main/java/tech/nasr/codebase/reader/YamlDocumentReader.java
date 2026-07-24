package tech.nasr.codebase.reader;

import tech.nasr.document.model.DocumentType;
import tech.nasr.document.model.KnowledgeDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class YamlDocumentReader implements DocumentReader {

    @Override
    public boolean supports(Path path) {

        String file =
                path.toString().toLowerCase();

        return file.endsWith(".yml")
                || file.endsWith(".yaml");

    }

    @Override
    public KnowledgeDocument read(Path path) throws IOException {

        return new KnowledgeDocument(

                path.getFileName().toString(),

                path,

                DocumentType.YAML,

                Files.readString(path),

                Files.size(path)

        );

    }
}
