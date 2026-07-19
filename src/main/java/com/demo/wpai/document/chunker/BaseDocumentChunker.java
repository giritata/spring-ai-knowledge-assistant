package com.demo.wpai.document.chunker;

import com.demo.wpai.document.model.KnowledgeChunk;
import com.demo.wpai.document.model.KnowledgeDocument;
import com.demo.wpai.document.model.SymbolType;

import java.util.List;

public abstract class BaseDocumentChunker
        implements DocumentChunker {

    @Override
    public List<KnowledgeChunk> chunk(
            KnowledgeDocument document) {

        String id = document.fileName() + "-1";

        int chunkNumber = 1;

        String content = document.content();

        KnowledgeChunk chunk =

                new KnowledgeChunk(

                        id,

                        document.fileName(),

                        document.type(),

                        SymbolType.FILE,

                        document.fileName(),

                        content,

                        chunkNumber

                );;

        return List.of(chunk);

    }

}