package com.example.websocketproject.controller;

import com.example.websocketproject.entity.ChatListDTO;
import com.example.websocketproject.entity.ChatMessageDTO;
import com.example.websocketproject.entity.ChattingRoomDTO;
import com.example.websocketproject.entity.User;
import com.example.websocketproject.mapper.ChatMapper;
import com.example.websocketproject.service.ChatService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {

    @Autowired
    private ChatMapper chatMapper;
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chatMessageDTO) {
        chatMapper.chatMessage(chatMessageDTO);

        return chatMessageDTO;
    }

    @MessageMapping("/chat.createdRoom")
    @SendTo("/topic/createdRoom")
    public ChattingRoomDTO setCreatedRoom(@Payload Map<String, Object> request) {
        String response_user = request.get("other").toString();
        String request_user = request.get("user").toString();

        ChattingRoomDTO result = chatMapper.setCreatedRoom(response_user, request_user);

        return result;
    }

    @RequestMapping("/firstMessage")
    public @ResponseBody int firstMessage(String user_id) {
        int id = chatMapper.firstMessage(user_id);

        return id;
    }

    @RequestMapping("/chattingPage")
    public String chattingPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("member");
        String user_id = user.getUser_id();

        List<ChattingRoomDTO> chatList = chatMapper.chatList(user_id);
        List<ChatListDTO> chatListDTOS = new ArrayList<>();

        if (chatList.size() != 0) {
            for (int i = 0; i < chatList.size(); i++) {
                if (chatList.get(i).getRequest_user().equals(user_id)) {
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
        }

        model.addAttribute("list", chatListDTOS);

        return "chattingPage";
    }

    @RequestMapping("/roadChat")
    public @ResponseBody List<ChatMessageDTO> roadChat(String id) {

        List<ChatMessageDTO> chatMessage = chatMapper.roadChat(id);
        List<ChatMessageDTO> chatMessageDESC = new ArrayList<>();
        for (int i = chatMessage.size()-1; i >= 0; i--) {
            chatMessageDESC.add(chatMessage.get(i));
        }

        return chatMessageDESC;
    }

    @RequestMapping("/matching")
    public @ResponseBody String matching(@RequestBody Map<String, Object> request) {
        String result = chatService.matching(request);

        return result;

    }

}
