package tech.nasr.document.chunker;

import tech.nasr.document.model.KnowledgeChunk;
import tech.nasr.document.model.KnowledgeDocument;
import tech.nasr.document.model.SymbolType;

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