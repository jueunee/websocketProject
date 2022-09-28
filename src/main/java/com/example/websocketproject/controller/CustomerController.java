package com.example.websocketproject.controller;

//import com.example.websocketproject.repository.CustomerRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequiredArgsConstructor
@Controller
public class CustomerController {

    //게시판- 데이터 입력 값 저장
    @GetMapping("/")
    public String main() {
        return "main";

    }


}