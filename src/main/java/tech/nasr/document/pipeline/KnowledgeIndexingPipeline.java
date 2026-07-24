package tech.nasr.document.pipeline;

import tech.nasr.config.EmbeddingProperties;
import tech.nasr.document.embedding.KnowledgeEmbeddingPipeline;
import tech.nasr.document.indexer.KnowledgeIndexer;
import tech.nasr.document.model.EmbeddedKnowledgeChunk;
import tech.nasr.document.model.KnowledgeChunk;
import tech.nasr.document.model.KnowledgeIndex;
import tech.nasr.document.store.InMemoryVectorStore;
import tech.nasr.document.store.KnowledgeStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;

@Service
public class KnowledgeIndexingPipeline {

    private final KnowledgeIndexer knowledgeIndexer;

    private final KnowledgeEmbeddingPipeline embeddingPipeline;

    private final InMemoryVectorStore vectorStore;

    private final KnowledgeStore knowledgeStore;

    private final EmbeddingProperties embeddingProperties;

    private static final Logger log =
            LoggerFactory.getLogger(KnowledgeIndexingPipeline.class);

    public KnowledgeIndexingPipeline(
            KnowledgeIndexer knowledgeIndexer,
            KnowledgeEmbeddingPipeline embeddingPipeline,
            InMemoryVectorStore vectorStore,
            KnowledgeStore knowledgeStore,
            EmbeddingProperties embeddingProperties) {

        this.knowledgeIndexer = knowledgeIndexer;
        this.embeddingPipeline = embeddingPipeline;
        this.vectorStore = vectorStore;
        this.knowledgeStore = knowledgeStore;
        this.embeddingProperties = embeddingProperties;
    }

    public void index(Path projectPath)
            throws IOException {

        if (knowledgeStore.exists()) {

            log.info("Knowledge index found. Loading...");

            KnowledgeIndex knowledgeIndex =
                    knowledgeStore.load();

            vectorStore.replaceAll(
                    knowledgeIndex.chunks());

            log.info("Loaded {} chunks from knowledge index.",
                    knowledgeIndex.chunks().size());

            return;
        }
        buildKnowledgeIndex(projectPath);

    }

    private void buildKnowledgeIndex(Path projectPath) throws IOException {
        log.info("");
        log.info("==================================");
        log.info("Knowledge indexing started");
        log.info("==================================");

        List<KnowledgeChunk> chunks =
                knowledgeIndexer.index(projectPath);

        log.info("Chunks Created : " + chunks.size());

        List<EmbeddedKnowledgeChunk> embeddedChunks =
                embeddingPipeline.embed(chunks);

        log.info("Embeddings Created : "
                + embeddedChunks.size());

        KnowledgeIndex knowledgeIndex =
                new KnowledgeIndex(

                        projectPath.getFileName().toString(),

                        embeddingProperties.getModel(),

                        Instant.now(),

                        embeddedChunks

                );

        knowledgeStore.save(knowledgeIndex);

        vectorStore.replaceAll(embeddedChunks);

        log.info("Vectors Stored : "
                + vectorStore.getChunks().size());

        log.info("==================================");
        log.info("Knowledge Indexing Completed");
        log.info("==================================");
    }

}