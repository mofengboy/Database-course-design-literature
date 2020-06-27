package com.sunan.literature.service;

import com.sunan.literature.entity.User;
import com.sunan.literature.entity.UserAccess;
import com.sunan.literature.exception.WrongPasswordException;

import java.util.List;

public interface UserService {
    // 登录获取token
    String getTokenByEmailAndPassword(String email, String password) throws WrongPasswordException;

    // 验证token并且获取用户id
    Integer getUserIdByToken(String token);

    // 注册
    Boolean register(User user);

    // 获取用户信息列表
    List<User> getUserList();

    // 获取用户信息通过id
    User getUser(Integer id);

    // 获取用户信息通过email
    User getUser(String email);

    UserAccess getUserAccessByUserId(Integer userId);

    // 修改用户信息
    Boolean updateUser(User user);
}
