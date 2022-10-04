package com.example.websocketproject.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AttachFileDTO {

    private String id; //chatRoom index
    private String sender;
    private String fileName;
    private String uploadPath;
    private Date uploadedDate;
}
