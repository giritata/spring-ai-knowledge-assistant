package tech.nasr.ai.classifier;

import tech.nasr.ai.prompt.KnowledgeType;
import tech.nasr.document.model.DocumentType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class KnowledgeTypeClassifier {

    public KnowledgeType determineKnowledgeType(DocumentType documentType) {

        if (documentType == null) {
            return KnowledgeType.UNKNOWN;
        }

        return switch (documentType) {

            case JAVA ->
                    KnowledgeType.CODE;

            case MARKDOWN ->
                    KnowledgeType.DOCUMENT;

            case XML,
                 YAML,
                 PROPERTIES ->
                    KnowledgeType.CONFIGURATION;

            case SQL ->
                    KnowledgeType.PROCESS;

            case UNKNOWN ->
                    KnowledgeType.UNKNOWN;
        };
    }

    public KnowledgeType determineKnowledgeType(List<DocumentType> documentTypes) {

        if (documentTypes == null || documentTypes.isEmpty()) {
            return KnowledgeType.UNKNOWN;
        }

        return documentTypes.stream()

                .map(this::determineKnowledgeType)

                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()))

                .entrySet()

                .stream()

                .max(Map.Entry.comparingByValue())

                .map(Map.Entry::getKey)

                .orElse(KnowledgeType.UNKNOWN);
    }
}