package com.demo.wpai.controller;


import com.demo.wpai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @GetMapping("/ask")
    public String ask(@RequestParam String question){

        return aiService.ask(question);

    }

}
