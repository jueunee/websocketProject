package com.example.websocketproject.service;

import com.example.websocketproject.entity.User;
import com.example.websocketproject.entity.UserDTO;
import com.example.websocketproject.mapper.UserMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createUser(User user) {
        System.out.println("시스템이다ㅏㅏㅏㅏㅏ" + user);


        userMapper.createUser(user);
    }
    public List<User> getList(){
        return userMapper.getList();
    }

    public List<User> getUser(User user) {
        List<User> result = userMapper.getUser(user);

        return result;
    }
    public int overlappedID(String user_id) throws Exception{
        int result = userMapper.overlappedID(user_id);
        return result;
    }
}

