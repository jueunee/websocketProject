package com.example.websocketproject.controller;

import com.example.websocketproject.entity.User;
import com.example.websocketproject.mapper.UserMapper;
import com.example.websocketproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SignupController {
    @Autowired
    UserService userService;
    UserMapper userMapper;

    @GetMapping("login")
    public void getSelectOne() throws Exception {
    }
    //아이디 중복 확인
    @ResponseBody
    @GetMapping("/idCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
    public int overlappedID(String id) throws Exception{
        int result = userService.overlappedID(id); // 중복확인한 값을 int로 받음
        return result;
    }

    //로그인 정보일치 확인
    @PostMapping("/login")
    public String getSelectOne(User user, HttpSession session) throws Exception {
        List<User> result = userService.getUser(user);
        if (result.size() != 0) {//로그인성공시 세션을 생성, null이 아니면 세션고유의값 리턴
            session.setAttribute("member", user);//세션에 로그인된 회원 인증성공
            return "redirect:/chattingPage";
        } else {
            return "redirect:/login";
        }
    }
//    @RequestMapping("/index")
//    public String main() {
//        return "index";
//    }
    //로그아웃, 세션 초기화
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) throws Exception{
       session.invalidate();
       return "redirect:/login";
    }
    //회원가입 페이지
    @RequestMapping("/signupPage")
    public String page() {
        return "signup";
    }

    @RequestMapping(value = "/signup1")
    public String postSignup(User user) {
        System.out.println("postttttt sign up");
        System.out.println("user sign up");
        System.out.println(user);
        userService.createUser(user);
        return "signup";
    }

//    @GetMapping("/memberList")
//        public String admin(Model model) {
//        List<User> memberList = UserService.admin();
//        model.addAttribute("title","회원리스트");
//        model.addAttribute("memberList",memberList);
//        return "redirect:/admin";
//    }

}
