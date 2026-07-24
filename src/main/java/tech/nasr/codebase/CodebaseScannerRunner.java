package tech.nasr.codebase;

import tech.nasr.document.pipeline.KnowledgeIndexingPipeline;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class CodebaseScannerRunner implements ApplicationRunner {

    private final KnowledgeIndexingPipeline pipeline;

    public CodebaseScannerRunner(KnowledgeIndexingPipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Path project =
                Path.of("C:\\DC\\spring-petclinic");

        pipeline.index(project);

    }

}