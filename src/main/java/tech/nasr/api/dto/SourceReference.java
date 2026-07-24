package tech.nasr.api.dto;

import tech.nasr.document.model.DocumentType;

import java.util.List;

public record SourceReference(

        String document,

        DocumentType documentType,

        List<String> symbols

) {
}