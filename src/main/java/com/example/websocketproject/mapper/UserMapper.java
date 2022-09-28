package com.example.websocketproject.mapper;

import com.example.websocketproject.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    //사용자등록
    void createUser(User user);
    List<User> getList();

    void getUser(User user);
}
