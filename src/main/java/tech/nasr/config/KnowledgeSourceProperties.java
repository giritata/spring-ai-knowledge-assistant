package tech.nasr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import tech.nasr.domain.source.KnowledgeSourceType;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "wpai")
public class KnowledgeSourceProperties {

    private List<Source> sources = new ArrayList<>();

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public static class Source {

        private String id;

        private String knowledgeBaseId;

        private String name;

        private KnowledgeSourceType type;

        private String location;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKnowledgeBaseId() {
            return knowledgeBaseId;
        }

        public void setKnowledgeBaseId(String knowledgeBaseId) {
            this.knowledgeBaseId = knowledgeBaseId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public KnowledgeSourceType getType() {
            return type;
        }

        public void setType(KnowledgeSourceType type) {
            this.type = type;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}