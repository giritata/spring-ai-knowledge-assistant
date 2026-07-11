package com.demo.wpai.document.chunker;
import com.demo.wpai.document.model.DocumentType;
import com.demo.wpai.document.model.KnowledgeDocument;
import org.springframework.stereotype.Component;

@Component

public class PropertiesDocumentChunker extends BaseDocumentChunker{
    @Override
    public boolean supports(
            KnowledgeDocument document) {

        return document.type()
                == DocumentType.PROPERTIES;

    }
}
