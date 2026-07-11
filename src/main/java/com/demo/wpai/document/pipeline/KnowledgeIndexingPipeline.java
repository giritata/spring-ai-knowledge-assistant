package com.demo.wpai.document.pipeline;

import com.demo.wpai.document.embedding.KnowledgeEmbeddingPipeline;
import com.demo.wpai.document.indexer.KnowledgeIndexer;
import com.demo.wpai.document.model.EmbeddedKnowledgeChunk;
import com.demo.wpai.document.model.KnowledgeChunk;
import com.demo.wpai.document.store.InMemoryVectorStore;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class KnowledgeIndexingPipeline {

    private final KnowledgeIndexer knowledgeIndexer;

    private final KnowledgeEmbeddingPipeline embeddingPipeline;

    private final InMemoryVectorStore vectorStore;

    public KnowledgeIndexingPipeline(
            KnowledgeIndexer knowledgeIndexer,
            KnowledgeEmbeddingPipeline embeddingPipeline,
            InMemoryVectorStore vectorStore) {

        this.knowledgeIndexer = knowledgeIndexer;
        this.embeddingPipeline = embeddingPipeline;
        this.vectorStore = vectorStore;

    }

    public void index(Path projectPath)
            throws IOException {

        System.out.println();
        System.out.println("==================================");
        System.out.println("Knowledge Indexing Started");
        System.out.println("==================================");

        List<KnowledgeChunk> chunks =
                knowledgeIndexer.index(projectPath);

        System.out.println("Chunks Created : " + chunks.size());

        List<EmbeddedKnowledgeChunk> embeddedChunks =
                embeddingPipeline.embed(chunks);

        System.out.println("Embeddings Created : "
                + embeddedChunks.size());

        vectorStore.clear();

        vectorStore.addAll(embeddedChunks);

        System.out.println("Vectors Stored : "
                + vectorStore.getChunks().size());

        System.out.println("==================================");
        System.out.println("Knowledge Indexing Completed");
        System.out.println("==================================");

    }

}