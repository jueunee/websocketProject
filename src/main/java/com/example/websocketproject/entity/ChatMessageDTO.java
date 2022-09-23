package com.example.websocketproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
public class ChatMessageDTO {

    private String _id;

    private ChatListDTO listId;
    private String sendUser;
    private String message;

    public ChatMessageDTO toEntity() {
        return ChatMessageDTO.builder()
                .listId(listId)
                .sendUser(sendUser)
                .message(message)
                .build();
    }
}
