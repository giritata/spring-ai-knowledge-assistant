package tech.nasr.application.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tech.nasr.domain.source.KnowledgeSource;

@Component
public class StartupSummaryPrinter {

    private static final Logger log =
            LoggerFactory.getLogger(StartupSummaryPrinter.class);

    public void print(KnowledgeSource source) {

        log.info("");
        log.info("===================================================");
        log.info("      WP-AI Enterprise Knowledge Platform");
        log.info("===================================================");

        log.info("");
        log.info("Knowledge Base");
        log.info("--------------");
        log.info("Name : {}", source.knowledgeBaseId());

        log.info("");
        log.info("Knowledge Source");
        log.info("----------------");
        log.info("Name     : {}", source.name());
        log.info("Type     : {}", source.type());
        log.info("Location : {}", source.location());

        log.info("");
        log.info("Starting knowledge indexing...");
        log.info("");

    }

}