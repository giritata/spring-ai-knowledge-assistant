package com.demo.wpai.document.chunker;

import com.demo.wpai.document.model.KnowledgeChunk;
import com.demo.wpai.document.model.KnowledgeDocument;

import java.util.List;

public abstract class BaseDocumentChunker
        implements DocumentChunker {

    @Override
    public List<KnowledgeChunk> chunk(
            KnowledgeDocument document) {

        KnowledgeChunk chunk =

                new KnowledgeChunk(

                        document.fileName(),

                        document.fileName(),

                        document.content()

                );

        return List.of(chunk);

    }

}