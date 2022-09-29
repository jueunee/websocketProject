package com.example.websocketproject.service;

import com.example.websocketproject.mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatMapper chatMapper;

    public void createdRoom(String responseUser, String requestUser) {

        chatMapper.createdRoom(responseUser, requestUser);
    }
}
