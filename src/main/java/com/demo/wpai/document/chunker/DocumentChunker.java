package com.demo.wpai.document.chunker;

import com.demo.wpai.document.model.KnowledgeChunk;
import com.demo.wpai.document.model.KnowledgeDocument;

import java.util.List;

public interface DocumentChunker {

    boolean supports(KnowledgeDocument document);

    List<KnowledgeChunk> chunk(KnowledgeDocument document);

}