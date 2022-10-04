package com.example.websocketproject.controller;

import com.example.websocketproject.entity.*;
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
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;

@Controller
public class ChatController {
    List<HashMap<String, Object>> rls = new ArrayList<>(); //웹소켓 세션을 담아둘 리스트 ---roomListSessions
    private static final String FILE_UPLOAD_PATH = "C:/test/websocket/";
    static int fileUploadIdx = 0;

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

    @MessageMapping("/chat.sendFile")
    @SendTo("/topic/sendFile")
    public void sendFile(@Payload BinaryMessage message) {
        System.out.println("하 개어려워진심 : " + message);
        //바이너리 메시지 발송
        ByteBuffer byteBuffer = message.getPayload();
        String fileName = "temp.jpg";
        File dir = new File(FILE_UPLOAD_PATH);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(FILE_UPLOAD_PATH, fileName);
        FileOutputStream out = null;
        FileChannel outChannel = null;
        try {
            byteBuffer.flip(); //byteBuffer를 읽기 위해 세팅
            out = new FileOutputStream(file, true); //생성을 위해 OutputStream을 연다.
            outChannel = out.getChannel(); //채널을 열고
            byteBuffer.compact(); //파일을 복사한다.
            outChannel.write(byteBuffer); //파일을 쓴다.
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null) {
                    out.close();
                }
                if(outChannel != null) {
                    outChannel.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        byteBuffer.position(0); //파일을 저장하면서 position값이 변경되었으므로 0으로 초기화한다.
        //파일쓰기가 끝나면 이미지를 발송한다.
        HashMap<String, Object> temp = rls.get(fileUploadIdx);
        for(String k : temp.keySet()) {
            if(k.equals("roomNumber")) {
                continue;
            }
            WebSocketSession wss = (WebSocketSession) temp.get(k);
            try {
                wss.sendMessage(new BinaryMessage(byteBuffer)); //초기화된 버퍼를 발송한다.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/firstMessage")
    public @ResponseBody int firstMessage(@RequestBody Map<String, Object> request) {
        String request_user = request.get("request_user").toString();
        String response_user = request.get("response_user").toString();
        int id = chatMapper.firstMessage(request_user, response_user);

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

    @RequestMapping("/roadNextChat")
    public @ResponseBody List<ChatMessageDTO> roadNextChat(@RequestBody Map<String, Object> request) {
        int id = Integer.parseInt(request.get("id").toString());
        int num = Integer.parseInt(request.get("endNum").toString());

        List<ChatMessageDTO> chatMessage = chatMapper.roadNextChat(id, num);

        return chatMessage;
    }

}
