package com.demo.wpai.codebase.filter;

import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class SourceFileFilter {

    public boolean shouldIndex(Path path) {

        String file = path.toString().toLowerCase();

        if (file.contains("\\.git\\")
                || file.contains("\\target\\")
                || file.contains("\\.idea\\")
                || file.contains("\\build\\")
                || file.contains("\\node_modules\\")) {

            return false;
        }

        return file.endsWith(".java")
                || file.endsWith(".xml")
                || file.endsWith(".yml")
                || file.endsWith(".yaml")
                || file.endsWith(".properties")
                || file.endsWith(".sql")
                || file.endsWith(".md");
    }
}