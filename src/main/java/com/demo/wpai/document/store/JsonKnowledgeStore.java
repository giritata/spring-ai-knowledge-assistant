package com.demo.wpai.document.store;

import com.demo.wpai.document.model.KnowledgeIndex;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class JsonKnowledgeStore
        implements KnowledgeStore {

    private static final Path INDEX_FILE =
            Path.of("knowledge-index.json");

    private final ObjectMapper objectMapper;

    public JsonKnowledgeStore(
            ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    @Override
    public boolean exists() {

        return Files.exists(INDEX_FILE);

    }

    @Override
    public void save(
            KnowledgeIndex index) {

        try {

            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(
                            INDEX_FILE.toFile(),
                            index);

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to save knowledge index",
                    ex);

        }

    }

    @Override
    public KnowledgeIndex load() {

        try {

            return objectMapper.readValue(
                    INDEX_FILE.toFile(),
                    KnowledgeIndex.class);

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to load knowledge index",
                    ex);

        }

    }

}
