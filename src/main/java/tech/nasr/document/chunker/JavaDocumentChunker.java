package tech.nasr.document.chunker;

import tech.nasr.document.chunker.java.JavaMethodChunkExtractor;
import tech.nasr.document.model.DocumentType;
import tech.nasr.document.model.KnowledgeDocument;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.springframework.stereotype.Component;
import tech.nasr.document.model.KnowledgeChunk;

import java.util.List;

@Component

public class JavaDocumentChunker extends BaseDocumentChunker {

    private final JavaMethodChunkExtractor extractor;

    public JavaDocumentChunker(

            JavaMethodChunkExtractor extractor) {

        this.extractor = extractor;

    }

    @Override
    public boolean supports(
            KnowledgeDocument document) {

        return document.type()
                == DocumentType.JAVA;

    }

    @Override
    public List<KnowledgeChunk> chunk(
            KnowledgeDocument document) {
        CompilationUnit cu =
                StaticJavaParser.parse(
                        document.content());

        return extractor.extract(

                cu,

                document.fileName()

        );
    }
}
