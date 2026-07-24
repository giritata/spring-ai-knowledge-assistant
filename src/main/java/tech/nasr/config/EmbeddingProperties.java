package tech.nasr.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wpai.embedding")
public class EmbeddingProperties {
    private String model;
}
