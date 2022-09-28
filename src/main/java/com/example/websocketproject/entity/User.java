package com.example.websocketproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String user_id;
    private String pw;
    private String name;
    private String email;
    private String gender;
    private String mbti;
    private Date joinDated;

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", mbti='" + mbti + '\'' +
                ", joinDated=" + joinDated +
                '}';
    }
}
