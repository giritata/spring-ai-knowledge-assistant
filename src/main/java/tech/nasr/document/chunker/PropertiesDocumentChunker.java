package tech.nasr.document.chunker;
import tech.nasr.document.model.DocumentType;
import tech.nasr.document.model.KnowledgeDocument;
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
