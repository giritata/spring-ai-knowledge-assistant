package tech.nasr.domain.source;

import java.nio.file.Path;
import java.util.List;

public interface KnowledgeSourceService {

    List<KnowledgeSource> findAll();

    KnowledgeSource loadSample();

    void upload(Path zipFile);

}
