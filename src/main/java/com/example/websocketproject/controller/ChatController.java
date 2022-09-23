package com.example.websocketproject.controller;

import com.example.websocketproject.entity.ChatMessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chatMessageDTO) {

        ChatMessageDTO chatmessage = chatMessageDTO.builder()
                .listId(chatMessageDTO.getListId())
                .sendUser(chatMessageDTO.getSendUser())
                .message(chatMessageDTO.getMessage())
                .build();

        return chatMessageDTO;
    }
}
