package com.demo.wpai.document.store;

import com.demo.wpai.document.model.KnowledgeIndex;

import java.io.IOException;

public interface KnowledgeStore {

    boolean exists();

    void save(KnowledgeIndex index)
            throws IOException;

    KnowledgeIndex load()
            throws IOException;

}