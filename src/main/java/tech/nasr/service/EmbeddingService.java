package tech.nasr.service;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

//@Service
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public EmbeddingService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public float[] embed(String text) {

        return embeddingModel
                .embed(text);

    }

    public int dimensions(String text){

        return embeddingModel
                .embed(text)
                .length;

    }

    public void compare() {

        float[] a =
                embeddingModel.embed("Home Loan");

        float[] b =
                embeddingModel.embed("Mortgage");

    }

}
