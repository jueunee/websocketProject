package com.example.websocketproject.service;

import com.example.websocketproject.entity.User;
import com.example.websocketproject.entity.UserDTO;
import com.example.websocketproject.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired(required = false)
    UserMapper userMapper;
    public void createUser(User user) {
        System.out.println("시스템이다ㅏㅏㅏㅏㅏ" + user);


        userMapper.createUser(user);
    }

    public List<User> getList(){
        return userMapper.getList();
    }

//    public User loginCheck(UserDTO.LoginInfo userdto){
//        User user = UserMapper.getUser(userdto.getUser_id());
//    }
}

