package com.demo.wpai.codebase;

import com.demo.wpai.document.embedding.KnowledgeEmbeddingPipeline;
import com.demo.wpai.document.model.EmbeddedKnowledgeChunk;
import com.demo.wpai.document.model.KnowledgeChunk;
import com.demo.wpai.document.model.KnowledgeDocument;
import com.demo.wpai.document.pipeline.KnowledgeIndexingPipeline;
import com.demo.wpai.document.store.InMemoryVectorStore;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.demo.wpai.document.indexer.KnowledgeIndexer;

import java.nio.file.Path;
import java.util.List;

@Component
public class CodebaseScannerRunner implements ApplicationRunner {

    private final KnowledgeIndexingPipeline pipeline;

    public CodebaseScannerRunner(KnowledgeIndexingPipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Path project =
                Path.of("C:\\DC\\spring-petclinic");

        pipeline.index(project);

    }

}