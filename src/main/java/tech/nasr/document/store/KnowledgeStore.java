package tech.nasr.document.store;

import tech.nasr.document.model.KnowledgeIndex;

import java.io.IOException;

public interface KnowledgeStore {

    boolean exists();

    void save(KnowledgeIndex index)
            throws IOException;

    KnowledgeIndex load()
            throws IOException;

}