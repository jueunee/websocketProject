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
    private String gender;
    private String mbti;
    private char grade;
    private Date joinDated;
    private int reportcheck;
    private char blockcheck;
    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", pw='" + pw + '\'' +
                ", gender='" + gender + '\'' +
                ", mbti='" + mbti + '\'' +
                ", grade=" + grade +
                ", joinDated=" + joinDated +
                ", reportcheck=" + reportcheck +
                ", blockcheck=" + blockcheck +
                '}';
    }


}
