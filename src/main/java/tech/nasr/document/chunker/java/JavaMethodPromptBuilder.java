package tech.nasr.document.chunker.java;

import com.github.javaparser.ast.body.MethodDeclaration;
import org.springframework.stereotype.Component;

@Component
public class JavaMethodPromptBuilder {

    public String build(

            JavaClassContext context,

            MethodDeclaration method) {

        StringBuilder builder =
                new StringBuilder();


        builder.append("Package:\n")
                .append(context.packageName())
                .append("\n\n");

        builder.append("Class:\n")
                .append(context.className())
                .append("\n\n");

        builder.append("Class Annotations:\n");

        context.annotations()

                .forEach(a ->

                        builder.append(a)

                                .append("\n"));

        builder.append("\n");

        builder.append("Method:\n")

                .append(method.getNameAsString())

                .append("\n\n");

        builder.append("Method Annotations:\n");

        method.getAnnotations()

                .forEach(a ->

                        builder.append(a)

                                .append("\n"));

        builder.append("\n");

        builder.append("Return Type:\n")

                .append(method.getType())

                .append("\n\n");

        builder.append("Parameters:\n");

        method.getParameters()

                .forEach(parameter ->

                        builder.append(parameter)

                                .append("\n"));

        builder.append("\n");

        builder.append("Source:\n\n");

        builder.append(method);

        return builder.toString();

    }
}
