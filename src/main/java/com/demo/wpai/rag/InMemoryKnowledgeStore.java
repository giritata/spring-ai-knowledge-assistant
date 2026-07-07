package com.demo.wpai.rag;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryKnowledgeStore {

    private final List<KnowledgeChunk> chunks =
            new ArrayList<>();

    public void add(KnowledgeChunk chunk){

        chunks.add(chunk);

    }

    public List<KnowledgeChunk> getChunks(){

        return chunks;

    }

}