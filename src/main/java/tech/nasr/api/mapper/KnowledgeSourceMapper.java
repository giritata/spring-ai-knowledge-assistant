package tech.nasr.api.mapper;

import org.springframework.stereotype.Component;
import tech.nasr.api.dto.KnowledgeSourceResponse;
import tech.nasr.domain.source.KnowledgeSource;

@Component
public class KnowledgeSourceMapper {

    public KnowledgeSourceResponse toResponse(
            KnowledgeSource source) {

        return new KnowledgeSourceResponse(

                source.id(),

                source.knowledgeBaseId(),

                source.name(),

                source.type().name()

        );

    }

}