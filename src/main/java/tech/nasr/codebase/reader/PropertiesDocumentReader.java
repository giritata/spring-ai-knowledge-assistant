package tech.nasr.codebase.reader;

import tech.nasr.document.model.DocumentType;
import tech.nasr.document.model.KnowledgeDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class PropertiesDocumentReader implements DocumentReader {

    @Override
    public boolean supports(Path path) {

        String file =
                path.toString().toLowerCase();

        return file.endsWith(".properties");

    }

    @Override
    public KnowledgeDocument read(Path path) throws IOException {

        return new KnowledgeDocument(

                path.getFileName().toString(),

                path,

                DocumentType.PROPERTIES,

                Files.readString(path),

                Files.size(path)

        );

    }
}
