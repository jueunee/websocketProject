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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/roadChat")
    public @ResponseBody List<ChatMessageDTO> roadChat(String id) {

        List<ChatMessageDTO> chatMessage = chatDAO.roadChat(id);

        return chatMessage;
    }

    @RequestMapping("/matching")
    public @ResponseBody String matching(@RequestBody Map<String, Object> request) {
        List<String> gender = (List<String>) request.get("gender");
        List<String> mbti1 = (List<String>) request.get("mbti1");
        List<String> mbti2 = (List<String>) request.get("mbti2");
        List<String> mbti3 = (List<String>) request.get("mbti3");
        List<String> mbti4 = (List<String>) request.get("mbti4");

        Collections.shuffle(gender);
        Collections.shuffle(mbti1);
        Collections.shuffle(mbti2);
        Collections.shuffle(mbti3);
        Collections.shuffle(mbti4);

        String matching_mbti = mbti1.get(0) + mbti2.get(0) + mbti3.get(0) + mbti4.get(0);
        String matching_gender = gender.get(0);

        //chatDAO.matching(matching_gender, matching_mbti);

        return "redirect:/";
    }


}
