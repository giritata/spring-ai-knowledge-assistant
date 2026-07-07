package com.demo.wpai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.wpai.rag.InMemoryKnowledgeStore;

@RestController
@RequestMapping("/knowledge")
@RequiredArgsConstructor
public class KnowledgeAdminController {

    private final InMemoryKnowledgeStore store;

    @GetMapping("/count")
    public int count(){

        return store.getChunks().size();

    }

}
