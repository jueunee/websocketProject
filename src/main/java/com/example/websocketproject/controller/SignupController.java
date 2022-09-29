package com.example.websocketproject.controller;

import com.example.websocketproject.entity.User;
import com.example.websocketproject.mapper.UserMapper;
import com.example.websocketproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @PostMapping(value = "/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("user_id") String user_id) {
        int cnt = userService.idCheck(user_id);
        return cnt;
    }
//로그인 정보일치 확인
    @PostMapping("login")
    public String getSelectOne(User user, HttpSession session) throws Exception {
        List<User> result = userService.getUser(user);
        if (result.size() != 0) {//로그인성공시 세션을 생성, null이 아니면 세션고유의값 리턴
            System.out.println("정보잇음");
            session.setAttribute("member", user);//세션에 로그인된 회원 인증성공
            return "redirect:/index";
        } else {
            System.out.println("정보없음");
            return "redirect:/login";
        }

    }
    @RequestMapping("/index")
    public String main() {
        return "index";
    }

    //로그아웃, 세션 초기화
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) throws Exception{
       session.invalidate();
       return "redirect:/login";
    }
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
}
