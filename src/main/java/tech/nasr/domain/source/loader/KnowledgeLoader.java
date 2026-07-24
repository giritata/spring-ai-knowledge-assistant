package tech.nasr.domain.source.loader;

import java.io.IOException;
import java.nio.file.Path;

import tech.nasr.domain.source.KnowledgeSource;

public interface KnowledgeLoader {

    boolean supports(KnowledgeSource source);

    Path load(KnowledgeSource source) throws IOException;

}