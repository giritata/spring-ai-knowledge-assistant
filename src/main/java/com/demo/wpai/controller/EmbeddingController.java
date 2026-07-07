package com.demo.wpai.controller;

import com.demo.wpai.service.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/embedding")
@RequiredArgsConstructor
public class EmbeddingController {

    private final EmbeddingService embeddingService;

    @GetMapping
    public float[] embedding(
            @RequestParam String text){

        return embeddingService.embed(text);

    }

    @GetMapping("/dimensions")
    public int dimensions(
            @RequestParam String text){

        return embeddingService.dimensions(text);

    }

}
