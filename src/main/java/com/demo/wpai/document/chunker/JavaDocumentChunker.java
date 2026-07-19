package com.demo.wpai.document.chunker;

import com.demo.wpai.document.chunker.java.JavaMethodChunkExtractor;
import com.demo.wpai.document.model.DocumentType;
import com.demo.wpai.document.model.KnowledgeDocument;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.springframework.stereotype.Component;
import com.demo.wpai.document.model.KnowledgeChunk;

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
