package com.demo.wpai.service;

import com.demo.wpai.rag.InMemoryKnowledgeStore;
import com.demo.wpai.rag.KnowledgeChunk;
import com.demo.wpai.rag.SearchResult;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SimilaritySearchService {

    private final EmbeddingModel embeddingModel;
    private final InMemoryKnowledgeStore store;

    public SimilaritySearchService(
            EmbeddingModel embeddingModel,
            InMemoryKnowledgeStore store) {

        this.embeddingModel = embeddingModel;
        this.store = store;
    }

    public List<SearchResult> search(String question) {

        float[] questionEmbedding =
                embeddingModel.embed(question);

        return store.getChunks()
                .stream()

                .map(chunk -> {

                    double similarity =
                            cosineSimilarity(
                                    questionEmbedding,
                                    chunk.embedding());

                    return new SearchResult(
                            chunk,
                            similarity);

                })

                .sorted(
                        Comparator.comparingDouble(SearchResult::similarity)
                                .reversed())

                .limit(3)

                .toList();

    }

    private double cosineSimilarity(
            float[] a,
            float[] b) {

        double dot = 0.0;

        double normA = 0.0;

        double normB = 0.0;

        for (int i = 0; i < a.length; i++) {

            dot += a[i] * b[i];

            normA += a[i] * a[i];

            normB += b[i] * b[i];
        }

        if (normA == 0 || normB == 0) {
            return 0.0;
        }

        return dot /
                (Math.sqrt(normA) * Math.sqrt(normB));
    }

}