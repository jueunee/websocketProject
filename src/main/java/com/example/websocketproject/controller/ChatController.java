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
    public String chattingPage(Model model, String id) {
        id = "userA";

        List<ChattingRoomDTO> chatList= chatMapper.chatList(id);
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

    @RequestMapping("/roadChat")
    public @ResponseBody List<ChatMessageDTO> roadChat(String id) {

        List<ChatMessageDTO> chatMessage = chatMapper.roadChat(id);

        return chatMessage;
    }

    @RequestMapping("/matching")
    public @ResponseBody Boolean matching(@RequestBody Map<String, Object> request) {
        System.out.println(request);
        List<String> gender = (List<String>) request.get("gender");
        String user = request.get("user").toString();
        List<String> mbti1 = (List<String>) request.get("mbti1");
        List<String> mbti2 = (List<String>) request.get("mbti2");
        List<String> mbti3 = (List<String>) request.get("mbti3");
        List<String> mbti4 = (List<String>) request.get("mbti4");
        System.out.println("111111111111111111");

        Collections.shuffle(gender);
        Collections.shuffle(mbti1);
        Collections.shuffle(mbti2);
        Collections.shuffle(mbti3);
        Collections.shuffle(mbti4);
        System.out.println("222222222");
        String matching_mbti = mbti1.get(0) + mbti2.get(0) + mbti3.get(0) + mbti4.get(0);
        String matching_gender = gender.get(0);

        User matching_member = chatMapper.matching(matching_gender, matching_mbti, user);
        System.out.println(matching_member);
        if (matching_member != null) {
            System.out.println("3333333333");
            chatService.createdRoom(matching_member.getUser_id(), user);

            return true;
        } else {
            System.out.println("444444444");
            User reMatching_member = chatMapper.matchingRandom(matching_gender, user);
            System.out.println("55555555");
            chatService.createdRoom(reMatching_member.getUser_id(), user);
            System.out.println("666666666");
            return false;
        }

    }

}
