package com.example.websocketproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
//    private Date joinDated;
    private int reportcheck;
    private String blockcheck;
// LocalDateTime format 변경
    @Column(name = "created_date")
    @CreatedDate
    private String joinDated;
    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;
    @PrePersist
    public void onPrePersist(){
        this.joinDated = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.modifiedDate = this.joinDated;
    }
    @PreUpdate
    public void onPreUpdate(){
        this.modifiedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

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
