package com.sunan.literature.dao;

import com.sunan.literature.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    // 获取所有用户的信息
    List<User> selectAll();

    // 通过电子邮件和密码获取用户信息
    User selectUserByEmailAndPassword(String email, String password);

    // 通过id获取用户信息
    User selectUserById(Integer id);

    // 通过电子邮箱获取用户信息
    User selectUserByEmail(String email);

    // 插入用户
    Integer insertUser(User user);

    // 通过id删除用户
    Integer deleteUserById(Integer id);

    // 通过id更新用户信息
    Integer updateUserById(User user);
}
