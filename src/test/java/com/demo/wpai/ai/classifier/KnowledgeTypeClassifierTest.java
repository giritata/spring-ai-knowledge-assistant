package com.demo.wpai.ai.classifier;

import com.demo.wpai.ai.prompt.KnowledgeType;
import com.demo.wpai.document.model.DocumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnowledgeTypeClassifierTest {

    private KnowledgeTypeClassifier classifier;

    @BeforeEach
    void setUp() {
        classifier = new KnowledgeTypeClassifier();
    }

    @Test
    void shouldClassifyJavaAsCode() {

        assertEquals(
                KnowledgeType.CODE,
                classifier.determineKnowledgeType(DocumentType.JAVA));
    }

    @Test
    void shouldClassifyMarkdownAsDocument() {

        assertEquals(
                KnowledgeType.DOCUMENT,
                classifier.determineKnowledgeType(DocumentType.MARKDOWN));
    }

    @Test
    void shouldClassifyYamlAsConfiguration() {

        assertEquals(
                KnowledgeType.CONFIGURATION,
                classifier.determineKnowledgeType(DocumentType.YAML));
    }

    @Test
    void shouldClassifyXmlAsConfiguration() {

        assertEquals(
                KnowledgeType.CONFIGURATION,
                classifier.determineKnowledgeType(DocumentType.XML));
    }

    @Test
    void shouldClassifyPropertiesAsConfiguration() {

        assertEquals(
                KnowledgeType.CONFIGURATION,
                classifier.determineKnowledgeType(DocumentType.PROPERTIES));
    }

    @Test
    void shouldClassifySqlAsProcess() {

        assertEquals(
                KnowledgeType.PROCESS,
                classifier.determineKnowledgeType(DocumentType.SQL));
    }

    @Test
    void shouldReturnUnknownWhenDocumentTypeUnknown() {

        assertEquals(
                KnowledgeType.UNKNOWN,
                classifier.determineKnowledgeType(DocumentType.UNKNOWN));
    }

    @Test
    void shouldReturnUnknownWhenDocumentTypeNull() {

        assertEquals(
                KnowledgeType.UNKNOWN,
                classifier.determineKnowledgeType((DocumentType) null));
    }

    @Test
    void shouldReturnCodeWhenMajorityAreJava() {

        List<DocumentType> documentTypes = List.of(
                DocumentType.JAVA,
                DocumentType.JAVA,
                DocumentType.MARKDOWN,
                DocumentType.JAVA);

        assertEquals(
                KnowledgeType.CODE,
                classifier.determineKnowledgeType(documentTypes));
    }

    @Test
    void shouldReturnDocumentWhenMajorityAreMarkdown() {

        List<DocumentType> documentTypes = List.of(
                DocumentType.MARKDOWN,
                DocumentType.MARKDOWN,
                DocumentType.JAVA);

        assertEquals(
                KnowledgeType.DOCUMENT,
                classifier.determineKnowledgeType(documentTypes));
    }

    @Test
    void shouldReturnConfigurationWhenMajorityAreConfigurationFiles() {

        List<DocumentType> documentTypes = List.of(
                DocumentType.XML,
                DocumentType.YAML,
                DocumentType.PROPERTIES);

        assertEquals(
                KnowledgeType.CONFIGURATION,
                classifier.determineKnowledgeType(documentTypes));
    }

    @Test
    void shouldReturnUnknownWhenDocumentTypeListIsEmpty() {

        assertEquals(
                KnowledgeType.UNKNOWN,
                classifier.determineKnowledgeType(List.of()));
    }

    @Test
    void shouldReturnUnknownWhenDocumentTypeListIsNull() {

        assertEquals(
                KnowledgeType.UNKNOWN,
                classifier.determineKnowledgeType((List<DocumentType>) null));
    }
}