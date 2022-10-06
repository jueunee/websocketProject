package com.example.websocketproject.service;

import com.example.websocketproject.entity.User;
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
    public int overlappedID(String id) throws Exception{
        int result = userMapper.overlappedID(id);
        return result;
    }

    public List<User> admin(){
        List<User> memberList = userMapper.admin();
        return memberList;
    }

    public List<User> admin_login(User user) {
        List<User> result1 = userMapper.admin_login(user);

        return result1;
    }
    public List<User> blockView(){
        List<User> memberList2 = userMapper.blockView();
        return memberList2;
    }
<<<<<<< HEAD
    public int blockchange(String id, String blockcheck) {
        int result2 = userMapper.blockchange(id, blockcheck);
        System.out.println(result2);
        return result2;
    }
    public int blockcheck(String blockcheck) throws Exception{
       int result3 = userMapper.blockcheck(blockcheck);
       return result3;
    }
=======
>>>>>>> dev
}

