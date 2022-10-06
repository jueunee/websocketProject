package com.example.websocketproject.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChattingRoomDTO {

    private int id;
    private String request_user;
    private String response_user;
    private Timestamp createdDate;

    private String deleteUser;

    @Override
    public String toString() {
        return "ChattingRoomDTO{" +
                "id=" + id +
                ", request_user='" + request_user + '\'' +
                ", response_user='" + response_user + '\'' +
                ", createdDate=" + createdDate +
                ", deleteUser='" + deleteUser + '\'' +
                '}';
    }
}
