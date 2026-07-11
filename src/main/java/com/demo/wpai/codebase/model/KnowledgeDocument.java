package com.demo.wpai.codebase.model;

import java.nio.file.Path;

public record KnowledgeDocument(String fileName, Path path, DocumentType type, String content, long size) {
}
