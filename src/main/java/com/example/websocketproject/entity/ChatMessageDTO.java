package com.example.websocketproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {

    private int num;
    private int id;
    private String sender;
    private String message;
    private Timestamp sendDate;

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "num=" + num +
                ", id=" + id +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}
