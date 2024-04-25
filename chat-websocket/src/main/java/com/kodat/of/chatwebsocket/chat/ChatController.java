package com.kodat.of.chatwebsocket.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage savedChatMessage = chatMessageService.save(chatMessage);
        simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/messages",
                        new ChatNotification(
                        savedChatMessage.getId(),
                        savedChatMessage.getSenderId(),
                        savedChatMessage.getRecipientId(),
                        savedChatMessage.getContent()
                        )
        );


    }


    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages
            (@PathVariable String senderId,
             @PathVariable String recipientId) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }
}
