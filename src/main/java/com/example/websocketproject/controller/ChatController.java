package com.example.websocketproject.controller;

import com.example.websocketproject.entity.ChatListDTO;
import com.example.websocketproject.entity.ChatMessageDTO;
import com.example.websocketproject.entity.ChattingRoomDTO;
import com.example.websocketproject.repository.ChatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatDAO chatDAO;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chatMessageDTO) {
        chatDAO.chatMessage(chatMessageDTO);

        return chatMessageDTO;
    }

    @RequestMapping("/chattingPage")
    public String chattingPage(Model model, String id) {
        id = "userA";

        List<ChattingRoomDTO> chatList= chatDAO.chatList(id);
        List<ChatListDTO> chatListDTOS = new ArrayList<>();
        for (int i = 0; i < chatList.size(); i++) {
            if (chatList.get(i).getRequest_user().equals(id)) {
                ChatListDTO dto = new ChatListDTO();
                dto.set_id(chatList.get(i).getId());
                dto.setUser_id(chatList.get(i).getResponse_user());
                chatListDTOS.add(dto);
            } else {
                ChatListDTO dto = new ChatListDTO();
                dto.set_id(chatList.get(i).getId());
                dto.setUser_id(chatList.get(i).getRequest_user());
                chatListDTOS.add(dto);
            }
        }

        model.addAttribute("list", chatListDTOS);

        return "chattingPage";
    }

}
