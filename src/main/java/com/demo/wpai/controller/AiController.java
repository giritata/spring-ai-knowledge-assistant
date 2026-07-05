package com.demo.wpai.controller;


import com.demo.wpai.dto.ChatRequest;
import com.demo.wpai.dto.ChatResponse;
import com.demo.wpai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/chat")
    public ChatResponse ask(@RequestBody ChatRequest request){

        return aiService.chat(request);

    }

}
