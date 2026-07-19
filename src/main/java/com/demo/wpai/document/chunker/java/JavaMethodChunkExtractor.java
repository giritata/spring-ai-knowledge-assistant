package com.demo.wpai.document.chunker.java;

import com.demo.wpai.document.model.DocumentType;
import com.demo.wpai.document.model.KnowledgeChunk;
import com.demo.wpai.document.model.SymbolType;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JavaMethodChunkExtractor {

    private final JavaMethodPromptBuilder promptBuilder;

    private final JavaClassContextExtractor contextExtractor;

    public JavaMethodChunkExtractor(

            JavaMethodPromptBuilder promptBuilder,

            JavaClassContextExtractor contextExtractor) {

        this.promptBuilder = promptBuilder;

        this.contextExtractor = contextExtractor;

    }

    public List<KnowledgeChunk> extract(
            CompilationUnit cu,
            String source) {

        Optional<JavaClassContext> context =

                contextExtractor.extract(cu);

        if (context == null) {

            return List.of();

        }

        List<KnowledgeChunk> chunks =
                new ArrayList<>();

        int counter = 1;

        for (MethodDeclaration method :

                cu.findAll(MethodDeclaration.class)) {

            String content =

                    promptBuilder.build(

                            context.get(),

                            method);

            KnowledgeChunk chunk =
                    new KnowledgeChunk(

                            source + "-" + counter++,

                            source,

                            DocumentType.JAVA,

                            SymbolType.METHOD,

                            context.get().className() + "." + method.getNameAsString()+"()",

                            content,
                            counter

                    );

            chunks.add(chunk);

        }

        return chunks;

    }

}
