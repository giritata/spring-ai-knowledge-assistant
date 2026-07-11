package com.demo.wpai.document.embedding;

import com.demo.wpai.document.model.EmbeddedKnowledgeChunk;
import com.demo.wpai.document.model.KnowledgeChunk;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KnowledgeEmbeddingPipeline {

    private final EmbeddingService embeddingService;

    public KnowledgeEmbeddingPipeline(
            EmbeddingService embeddingService) {

        this.embeddingService = embeddingService;

    }

    public List<EmbeddedKnowledgeChunk> embed(
            List<KnowledgeChunk> chunks) {
        System.out.println("Chunks Embedding Started");
        List<EmbeddedKnowledgeChunk> embeddedChunks =
                new ArrayList<>();

        for (KnowledgeChunk chunk : chunks) {

            embeddedChunks.add(

                    embeddingService.embed(chunk)

            );

        }
        System.out.println("Chunks Embedding Ended");

        return embeddedChunks;

    }

}