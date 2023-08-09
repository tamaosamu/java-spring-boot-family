package com.family.finances.mapper;

import com.family.finances.model.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IUserMapper {
    @Select("SELECT * FROM user")
    List<User> getAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getOne(int id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User getByUsername(String username);

    @Insert("insert info user(username, password) VALUES (#{username}, #{password})")
    int addUser(User user);

}