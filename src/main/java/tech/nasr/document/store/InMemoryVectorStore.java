package tech.nasr.document.store;

import tech.nasr.document.model.EmbeddedKnowledgeChunk;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryVectorStore {

    private final List<EmbeddedKnowledgeChunk> chunks =
            new ArrayList<>();

    public void add(EmbeddedKnowledgeChunk chunk) {

        chunks.add(chunk);

    }

    public void addAll(List<EmbeddedKnowledgeChunk> chunks) {

        this.chunks.addAll(chunks);

    }

    public List<EmbeddedKnowledgeChunk> getChunks() {

        return List.copyOf(chunks);

    }

    public void clear() {

        chunks.clear();

    }

    public void replaceAll(
            List<EmbeddedKnowledgeChunk> chunks) {

        clear();

        addAll(chunks);

    }
}