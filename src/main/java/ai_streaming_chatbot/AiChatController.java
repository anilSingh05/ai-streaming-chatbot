package ai_streaming_chatbot;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Chat Controller
 */
@RestController
public class AiChatController {

    private final ChatClient chatClient;

    /**
     *
     * @param chatClient
     */
    public AiChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    /**
     *
     * @param message
     * @return
     */
    @PostMapping("/chat")
    public String  chat(@RequestParam String message){
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }

    /**
     *
     * @param message
     * @return
     */
    @GetMapping("/stream")
    public Flux<String> chatAsStream(@RequestParam String message){
        return chatClient
                .prompt()
                .user(message)
                .stream()
                .content();
    }
}
