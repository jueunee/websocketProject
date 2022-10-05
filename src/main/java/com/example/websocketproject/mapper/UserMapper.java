package com.example.websocketproject.mapper;

import com.example.websocketproject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {
    //사용자등록
    void createUser(User user);
    List<User> getList();

    List<User> getUser(User user);

    public int overlappedID(String id) throws Exception;

    public List<User> admin();
    List<User> admin_login(User user);
    public List<User> blockView();
    public int blockchange(@Param("user_id") String id, @Param("blockcheck") String blockcheck);
    public int blockcheck(String blockcheck) throws Exception;
}
