package com.example.websocketproject.mapper;

import com.example.websocketproject.entity.ChatMessageDTO;
import com.example.websocketproject.entity.ChattingRoomDTO;
import com.example.websocketproject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {
    void chatMessage(ChatMessageDTO chatMessageDTO);

    List<ChattingRoomDTO> chatList(String id);

    List<ChatMessageDTO> roadChat(String id);

    User matching(@Param("gender")String matching_gender, @Param("mbti")String matching_mbti, @Param("user_id")String user);

    User matchingRandom(@Param("gender")String matching_gender, @Param("user_id")String user);

    void createdRoom(@Param("response_user") String responseUser, @Param("request_user") String requestUser);
}
