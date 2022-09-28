package com.example.websocketproject.mapper;

import com.example.websocketproject.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    void createUser(User user);
    List<User> getList();


}
