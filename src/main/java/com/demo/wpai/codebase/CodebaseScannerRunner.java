package com.demo.wpai.codebase;

import com.demo.wpai.codebase.model.KnowledgeDocument;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.demo.wpai.document.indexer.KnowledgeIndexer;

import java.nio.file.Path;
import java.util.List;

@Component
public class CodebaseScannerRunner implements ApplicationRunner {

    private final KnowledgeIndexer loader;
    private final KnowledgeIndexer knowledgeIndexer;

    public CodebaseScannerRunner(KnowledgeIndexer loader, KnowledgeIndexer knowledgeIndexer) {
        this.loader = loader;
        this.knowledgeIndexer = knowledgeIndexer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Path project =
                Path.of("C:\\DC\\spring-petclinic");

        List<KnowledgeDocument> documents =
                knowledgeIndexer.index(project);

        System.out.println("================================");
        System.out.println("Documents Indexed : "
                + documents.size());
        System.out.println("================================");

        documents.forEach(document -> {

            System.out.println(document.fileName());

            System.out.println(document.type());

            System.out.println(document.size());

            System.out.println("--------------------------------");

        });

    }

}