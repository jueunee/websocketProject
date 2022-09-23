package com.example.websocketproject.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.sql.Timestamp;

//@Getter
//@Setter
@Data
@Entity
public class user {

   @GeneratedValue
    private String id;
    private String pw;
    private String username;
    private String mbti;

    @CreationTimestamp
    private Timestamp createDate;


    public user(String id, String pw, String username, String mbti) {
        this.id = id;
        this.pw = pw;
        this.username = username;
        this.mbti = mbti;
    }


}
