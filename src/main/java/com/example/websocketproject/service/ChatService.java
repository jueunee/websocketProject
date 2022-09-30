package com.example.websocketproject.service;

import com.example.websocketproject.entity.ChattingRoomDTO;
import com.example.websocketproject.entity.User;
import com.example.websocketproject.mapper.ChatMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    @Autowired
    private ChatMapper chatMapper;

    public String matching(Map<String, Object> request) {
        List<String> gender = (List<String>) request.get("gender");
        List<String> mbti1 = (List<String>) request.get("mbti1");
        List<String> mbti2 = (List<String>) request.get("mbti2");
        List<String> mbti3 = (List<String>) request.get("mbti3");
        List<String> mbti4 = (List<String>) request.get("mbti4");
        List<String> mbtiList = new ArrayList<>();
        String user = request.get("user").toString();

        Gson gson = new GsonBuilder().create();
        ArrayList<String> resultList  = new ArrayList<>();

        int cnt = 0;
        int checkInt = mbti1.size() * mbti2.size() * mbti3.size() * mbti4.size();

        // 조건에 맞는 mbti 조합 List
        while (cnt < checkInt) {
            Collections.shuffle(mbti1);
            Collections.shuffle(mbti2);
            Collections.shuffle(mbti3);
            Collections.shuffle(mbti4);

            String matching_mbti = mbti1.get(0) + mbti2.get(0) + mbti3.get(0) + mbti4.get(0);
            mbtiList.add(matching_mbti);

            cnt++;

            if (mbtiList.size() > 1) {
                for (int i = 0; i < cnt - 1; i++) {
                    if (mbtiList.get(i).equals(mbtiList.get(cnt - 1))) {
                        mbtiList.remove(cnt - 1);
                        cnt--;
                    }
                }
            }
        }

        // 조건에 맞게 매칭시작
        cnt = 0;
        User matching_member = new User();
        Loop1:
        while (cnt < mbtiList.size()) {
            for (int i = 0; i < gender.size(); i++) {
                String matching_gender = gender.get(i);

                matching_member = chatMapper.matching(matching_gender, mbtiList.get(cnt), user);

                if (matching_member != null) {
                    break;
                }
            }
            if (matching_member == null) {
                cnt++;
            } else {
                List<ChattingRoomDTO> chatList = chatMapper.chatList(user);
                String memberCheck = matching_member.getUser_id();
                for (int j = 0; j < chatList.size(); j++) {
                    if (chatList.get(j).getResponse_user().equals(memberCheck)
                            || chatList.get(j).getRequest_user().equals(memberCheck)) {
                        cnt++;
                        continue Loop1;
                    }
                }
                break;
            }
        }

        // 조건에 맞는 사람이 아무도 없을때 임의로 매칭
        if (cnt == mbtiList.size()) {
            Loop2:
            while(cnt < 100) {
                matching_member = chatMapper.matchingRandom(gender.get(0), user);
                List<ChattingRoomDTO> chatList = chatMapper.chatList(user);
                String memberCheck = matching_member.getUser_id();
                for (int j = 0; j < chatList.size(); j++) {
                    if (chatList.get(j).getResponse_user().equals(memberCheck)
                            || chatList.get(j).getRequest_user().equals(memberCheck)) {
                        cnt++;
                        continue Loop2;
                    }
                }
                break;
            }
            if (cnt == 100) {
                resultList.add("nothing");
            } else {
                chatMapper.createdRoom(matching_member.getUser_id(), user);
                resultList.add("random");
                resultList.add(matching_member.getUser_id());
            }
        } else {
            chatMapper.createdRoom(matching_member.getUser_id(), user);
            resultList.add("ok");
            resultList.add(matching_member.getUser_id());
        }

        String result = gson.toJson(resultList);

        return result;
    }
}
