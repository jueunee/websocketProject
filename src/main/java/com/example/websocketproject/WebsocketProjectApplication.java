package com.example.websocketproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class WebsocketProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketProjectApplication.class, args);
    }

}
