package tech.nasr.codebase;

import tech.nasr.document.pipeline.KnowledgeIndexingPipeline;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tech.nasr.domain.source.KnowledgeSource;
import tech.nasr.domain.source.service.KnowledgeSourceService;

import java.nio.file.Path;
import java.util.List;

@Component
public class CodebaseScannerRunner implements ApplicationRunner {

    private final KnowledgeIndexingPipeline pipeline;

    private final KnowledgeSourceService knowledgeSourceService;

    public CodebaseScannerRunner(
            KnowledgeIndexingPipeline pipeline,
            KnowledgeSourceService knowledgeSourceService) {

        this.pipeline = pipeline;
        this.knowledgeSourceService = knowledgeSourceService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<KnowledgeSource> sources = knowledgeSourceService.findAll();

        if (sources.isEmpty()) {
            throw new IllegalStateException("No KnowledgeSource found.");
        }

        KnowledgeSource source = sources.get(0);

        pipeline.index(source);

    }

}