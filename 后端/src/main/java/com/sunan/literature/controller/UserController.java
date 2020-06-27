package com.sunan.literature.controller;

import com.sunan.literature.common.GenericResponse;
import com.sunan.literature.entity.User;

import java.lang.ref.SoftReference;
import java.util.List;

public interface UserController {
    // 登录
    GenericResponse login(String email, String password);

    // 注册
    GenericResponse register(String username,String password ,String email,Integer sex);

    // 获取用户信息
    GenericResponse getUserInfo(String token);
    // 获取所有用户信息
    GenericResponse getALLUserInfo(String token);

    // 获取用户信息
    GenericResponse getUserInfo(Integer id);
}
