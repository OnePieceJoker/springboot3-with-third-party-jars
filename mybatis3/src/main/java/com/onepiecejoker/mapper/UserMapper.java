package com.onepiecejoker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.onepiecejoker.entity.User;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> selectAll();

    User selectById(Long id);

    @Insert("insert into user(id) values(#{id})")
    void insert(User user);

    @Delete("delete from user where id = #{id}")
    void delete(Integer id);
    
}
