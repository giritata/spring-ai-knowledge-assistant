package tech.nasr.factory;

import tech.nasr.codebase.reader.DocumentReader;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class DocumentReaderFactory {

    private final List<DocumentReader> readers;

    public DocumentReaderFactory(List<DocumentReader> readers) {

        this.readers = readers;

    }

    public DocumentReader getReader(Path path) {

        return readers.stream()

                .filter(reader -> reader.supports(path))

                .findFirst()

                .orElseThrow(() ->

                        new IllegalArgumentException(

                                "No reader found for "

                                        + path));

    }

}