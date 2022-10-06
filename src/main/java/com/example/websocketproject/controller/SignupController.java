package com.example.websocketproject.controller;

import com.example.websocketproject.entity.User;
import com.example.websocketproject.mapper.UserMapper;
import com.example.websocketproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public int overlappedID(String id) throws Exception {
        int result = userService.overlappedID(id); // 중복확인한 값을 int로 받음
        return result;
    }

    //로그인 정보일치 확인
    @PostMapping("/login")
    public ModelAndView getSelectOne(User user, HttpSession session) throws Exception {
        List<User> result = userService.getUser(user);
        ModelAndView mav = new ModelAndView();
        if (result.size() != 0) {//차단된 유저들 로그인 정지
            if (result.get(0).getBlockcheck().equals("Y")) {
                mav.setViewName("/login");
                mav.addObject("msg", "block");
                return mav;
            } else {//로그인성공시 세션을 생성, null이 아니면 세션고유의값 리턴
                session.setAttribute("member", user);//세션에 로그인된 회원 인증성공
                mav.setViewName("redirect:/chattingPage");
                return mav;
            }
        } else {
            mav.setViewName("/login");
            mav.addObject("msg", "failure");

            return mav;
        }
    }

    //관리자페이지로 로그인하기
    @GetMapping("adminlogin")
    public void getSelectOne2() throws Exception {
    }

    @RequestMapping("adminlogin")
    public String login() {
        return "adminlogin";
    }

    @PostMapping("/adminlogin")
    public String getSelectOne2(User user, HttpSession session) throws Exception {
        List<User> result = userService.admin_login(user);
        System.out.println(result);
        if (result.size() != 0) {//로그인성공시 세션을 생성, null이 아니면 세션고유의값 리턴
            session.setAttribute("member", user);//세션에 로그인된 회원 인증성공
            return "redirect:/admin";
        } else {
            return "redirect:/adminlogin";
        }
    }

    //로그아웃, 세션 초기화
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/login";
    }

    //회원가입 페이지
    @RequestMapping("/signupPage")
    public String page() {
        return "signup";
    }

    @RequestMapping(value = "/signup1")
    public String postSignup(User user, Model model) {
        System.out.println(user);
        userService.createUser(user);
        model.addAttribute("msg", "회원가입 완료");
        return "login";
    }

    //관리자페이지 데이터조회
    @GetMapping("/admin")
    public String admin(Model model) {
        List<User> memberList = userService.admin();
//        System.out.println(memberList);
        model.addAttribute("list", memberList);
        return "admin";
    }

    //관리자페이지 블락유저 수정
    @RequestMapping("/user/change")
    public String userChange(@RequestParam("id") String id, User user) {
        userService.blockchange(id, user.getBlockcheck());
        return "redirect:/admin";
    }

    //블락유저들 데이터조회
    @GetMapping("blockview")
    public String blockview(Model model) {
        List<User> memberList2 = userService.blockView();
        model.addAttribute("list2", memberList2);
        return "blockview";
    }

    //블락유저들 계정정지
    @ResponseBody
    @GetMapping("/blockcheck")
    public int blockcheck(String blockcheck) throws Exception {
        int result = userService.blockcheck(blockcheck);
        return result;
    }
}
