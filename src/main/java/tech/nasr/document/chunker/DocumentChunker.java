package tech.nasr.document.chunker;

import tech.nasr.document.model.KnowledgeChunk;
import tech.nasr.document.model.KnowledgeDocument;

import java.util.List;

public interface DocumentChunker {

    boolean supports(KnowledgeDocument document);

    List<KnowledgeChunk> chunk(KnowledgeDocument document);

}