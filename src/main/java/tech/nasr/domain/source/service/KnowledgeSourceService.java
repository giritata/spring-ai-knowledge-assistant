package tech.nasr.domain.source.service;

import tech.nasr.domain.source.KnowledgeSource;

import java.nio.file.Path;
import java.util.List;

public interface KnowledgeSourceService {

    List<KnowledgeSource> findAll();


}
