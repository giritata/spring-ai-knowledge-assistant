package com.demo.wpai.rag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class KnowledgeLoader implements ApplicationRunner {

    private final InMemoryKnowledgeStore store;
    private final EmbeddingModel embeddingModel;

    private static final Logger log =
            LoggerFactory.getLogger(KnowledgeLoader.class);

    public KnowledgeLoader(InMemoryKnowledgeStore store,
                           EmbeddingModel embeddingModel) {
        this.store = store;
        this.embeddingModel = embeddingModel;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("=================================");
        log.info("Loading Knowledge Base...");
        log.info("=================================");

        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();

        Resource[] resources =
                resolver.getResources("classpath:knowledge/*.txt");

        int counter = 1;

        for (Resource resource : resources) {

            loadResource(resource, counter++);

        }

        log.info("---------------------------------");
        log.info("Knowledge Loaded Successfully");
        log.info("Documents : " + store.getChunks().size());
        log.info("---------------------------------");

    }

    private void loadResource(Resource resource,
                              int counter) throws IOException {

        String fileName = resource.getFilename();

        String id = "DOC-" + counter;

        String content =
                new String(
                        resource.getInputStream().readAllBytes(),
                        StandardCharsets.UTF_8);

        float[] embedding =
                embeddingModel.embed(content);

        KnowledgeMetadata metadata =
                new KnowledgeMetadata(
                        fileName,
                        "knowledge",
                        LocalDateTime.now());

        KnowledgeChunk chunk =
                new KnowledgeChunk(
                        id,
                        content,
                        embedding,
                        metadata);

        store.add(chunk);

        log.info("Loaded -> " + fileName);

    }

}