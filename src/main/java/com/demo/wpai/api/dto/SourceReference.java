package com.demo.wpai.api.dto;

import com.demo.wpai.document.model.DocumentType;

import java.util.List;

public record SourceReference(

        String document,

        DocumentType documentType,

        List<String> symbols

) {
}