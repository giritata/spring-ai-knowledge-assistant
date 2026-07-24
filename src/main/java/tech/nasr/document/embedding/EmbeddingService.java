package tech.nasr.document.embedding;

import tech.nasr.document.model.EmbeddedKnowledgeChunk;
import tech.nasr.document.model.KnowledgeChunk;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingService(
            EmbeddingModel embeddingModel) {

        this.embeddingModel = embeddingModel;

    }

    public EmbeddedKnowledgeChunk embed(
            KnowledgeChunk chunk) {

        float[] embedding =
                embeddingModel.embed(
                        chunk.content());

        return new EmbeddedKnowledgeChunk(

                chunk,

                embedding

        );

    }

}