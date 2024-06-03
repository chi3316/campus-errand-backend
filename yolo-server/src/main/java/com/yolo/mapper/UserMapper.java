package com.yolo.mapper;

import com.yolo.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    void insert(User newUser);

    void update(User user);

    @Select("select * from user where id = #{userId}")
    User getById(Long userId);
}
