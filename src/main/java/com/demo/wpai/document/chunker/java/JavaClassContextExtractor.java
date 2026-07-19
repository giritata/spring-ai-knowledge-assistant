package com.demo.wpai.document.chunker.java;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JavaClassContextExtractor {

    public Optional<JavaClassContext> extract(
            CompilationUnit cu) {

        var optionalClass =

                cu.findFirst(
                        ClassOrInterfaceDeclaration.class);

        if (optionalClass.isEmpty()) {

            return Optional.empty();

        }

        ClassOrInterfaceDeclaration clazz =
                optionalClass.get();

        String packageName =
                cu.getPackageDeclaration()

                        .map(pd -> pd.getNameAsString())

                        .orElse("");

        List<String> annotations =
                clazz.getAnnotations()

                        .stream()

                        .map(a -> a.toString())

                        .toList();

        String superClass =

                clazz.getExtendedTypes()

                        .stream()

                        .findFirst()

                        .map(Object::toString)

                        .orElse("");

        List<String> interfaces =

                clazz.getImplementedTypes()

                        .stream()

                        .map(Object::toString)

                        .toList();

        return  Optional.of(new JavaClassContext(

                packageName,

                clazz.getNameAsString(),

                annotations,

                superClass,

                interfaces

        ));

    }

}
