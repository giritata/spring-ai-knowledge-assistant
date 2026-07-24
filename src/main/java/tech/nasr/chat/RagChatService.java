package tech.nasr.chat;

import tech.nasr.document.prompt.PromptLoader;
import tech.nasr.document.retrieval.SearchResult;
import tech.nasr.document.retrieval.VectorSearchService;
import tech.nasr.dto.ChatRequest;
import tech.nasr.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RagChatService {

    private final ChatClient chatClient;

    private final VectorSearchService vectorSearchService;

    private final PromptLoader promptLoader;

    private final PromptContextBuilder contextBuilder;

    public RagChatService(

            ChatClient.Builder builder,

            VectorSearchService vectorSearchService,

            PromptLoader promptLoader,

            PromptContextBuilder contextBuilder) {

        this.chatClient = builder.build();

        this.vectorSearchService = vectorSearchService;

        this.promptLoader = promptLoader;

        this.contextBuilder = contextBuilder;

    }

    public ChatResponse chat(
            ChatRequest request) {

        List<SearchResult> results =

                vectorSearchService.search(
                        request.question());

        String context =

                contextBuilder.buildContext(
                        results);

        String systemPrompt =

                promptLoader.loadSystemPrompt("")

                        + "\n\n"

                        + context;

        return chatClient

                .prompt()

                .system(systemPrompt)

                .user(request.question())

                .call()

                .entity(ChatResponse.class);

    }

}
