package com.example.websocketproject.controller;

import com.example.websocketproject.entity.ChatListDTO;
import com.example.websocketproject.entity.ChatMessageDTO;
import com.example.websocketproject.entity.ChattingRoomDTO;
import com.example.websocketproject.entity.User;
import com.example.websocketproject.mapper.ChatMapper;
import com.example.websocketproject.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
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

        return chatMessage;
    }

    @RequestMapping("/matching")
    public @ResponseBody String matching(@RequestBody Map<String, Object> request) {
        String result = chatService.matching(request);

        return result;

    }

}
