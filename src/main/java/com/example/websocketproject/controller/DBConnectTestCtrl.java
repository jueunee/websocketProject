package com.example.websocketproject.controller;

import com.example.websocketproject.entity.TestDTO;
import com.example.websocketproject.repository.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DBConnectTestCtrl {

    @Autowired
    public TestDAO testDAO;

    @GetMapping("/hello")
    public List<TestDTO> HelloWorld() {
        return testDAO.getTestData();
    }
}
