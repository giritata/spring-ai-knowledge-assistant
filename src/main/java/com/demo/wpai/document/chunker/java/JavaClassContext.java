package com.demo.wpai.document.chunker.java;

import java.util.List;

public record JavaClassContext(

        String packageName,

        String className,

        List<String> annotations,

        String superClass,

        List<String> interfaces

) {
}
