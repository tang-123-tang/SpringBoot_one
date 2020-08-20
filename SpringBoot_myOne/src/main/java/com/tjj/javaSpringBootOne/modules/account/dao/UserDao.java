package com.tjj.javaSpringBootOne.modules.account.dao;

import com.tjj.javaSpringBootOne.modules.account.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    @Insert("insert into user(user_name,password,user_img,create_date) values" +
            " (#{userName},#{password},#{userImg},#{createDate})")
    @Options(useGeneratedKeys = true,keyProperty = "userId",keyColumn = "user_id")
    void inserUser(User user);
    @Select("select * from user where user_name=#{userName}")
    User getUserByUserName(String userName);
}
