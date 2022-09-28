package com.example.websocketproject.controller;

import com.example.websocketproject.entity.User;
import com.example.websocketproject.mapper.UserMapper;
import com.example.websocketproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SignupController {
    @Autowired
    UserService userService;
    UserMapper userMapper;
    @GetMapping("login")
    public void getSelectOne() throws Exception{}

    @PostMapping("login")
    public String getSelectOne(User user, HttpSession session) throws Exception{
    userService.getUser(user);
    if (user != null){
        session.setAttribute("member",user);
    }
        return "/";
    }
    @RequestMapping("/signupPage")
    public String page(){
        return "signup";
    }

    @RequestMapping(value = "/signup1")
    public String postSignup(User user){
        System.out.println("postttttt sign up");
        System.out.println("user sign up");
        System.out.println(user);
        userService.createUser(user);
        return "signup";
    }
}
