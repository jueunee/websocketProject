package com.example.websocketproject.repository;

import com.example.websocketproject.entity.ChatListDTO;
import com.example.websocketproject.entity.ChatMessageDTO;
import com.example.websocketproject.entity.ChattingRoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatDAO {
    void chatMessage(ChatMessageDTO chatMessageDTO);

    List<ChattingRoomDTO> chatList(String id);
}
