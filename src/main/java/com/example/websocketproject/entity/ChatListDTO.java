package com.example.websocketproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ChatListDTO {

    private String _id;
    private String userA;
    private String userB;

    public ChatListDTO toEntity() {
        return ChatListDTO.builder()
                .userA(userA)
                .userB(userB)
                .build();
    }
}
