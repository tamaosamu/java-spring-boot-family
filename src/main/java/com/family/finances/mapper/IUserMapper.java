package com.family.finances.mapper;

import com.family.finances.model.domain.User;
import org.apache.ibatis.annotations.Delete;
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

    @Select("SELECT * FROM user WHERE username = #{username} LIMIT 1")
    User getByUsername(String username);

    @Select("SELECT count(id) FROM user WHERE username = #{username}")
    int getCountByUsername(String username);

    @Insert("INSERT INTO user(username, password, salt) " +
            "VALUES (" +
            "   #{username}, " +
            "   #{password}, " +
            "   #{salt}" +
            ")")
    int add(User user);

    @Delete("DELETE FROM user WHERE id = #{id} ")
    int remove(int id);


}