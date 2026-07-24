package tech.nasr.domain.source.loader;

import tech.nasr.domain.knowledgebase.KnowledgeBase;
import tech.nasr.domain.source.KnowledgeSource;

public interface KnowledgeLoader {

    KnowledgeBase load(KnowledgeSource source);

}
