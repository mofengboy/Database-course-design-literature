package com.sunan.literature.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sunan.literature.dao.UserAccessDao;
import com.sunan.literature.dao.UserDao;
import com.sunan.literature.entity.User;
import com.sunan.literature.entity.UserAccess;
import com.sunan.literature.exception.WrongPasswordException;
import com.sunan.literature.service.UserService;
import com.sunan.literature.utils.JwtUtil;
import com.sunan.literature.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAccessDao userAccessDao;

    public List<User> getAllUserInfo() {
        return userDao.selectAll();
    }


    @Override
    public String getTokenByEmailAndPassword(String email, String password) throws WrongPasswordException {
        // 加密明文密码
        String encodePassword = Md5Util.getMD5(password);
        User user = userDao.selectUserByEmailAndPassword(email, encodePassword);
        if (user == null) {
            throw new WrongPasswordException();
        }
        String token = JwtUtil.sign(user.getUsername(), String.valueOf(user.getId()));
        return token;
    }


    @Override
    public Integer getUserIdByToken(String token) {
        Integer userId = JwtUtil.getUserId(token);
        return userId;
    }

    @Override
    public Boolean register(User user) {
        // 加密明文密码
        String encodePassword = Md5Util.getMD5(user.getPassword());
        user.setPassword(encodePassword);
        Integer flag = userDao.insertUser(user);
        return flag == 1;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.selectAll();
        return userList;
    }


    @Override
    public User getUser(Integer id) {
        User user = userDao.selectUserById(id);
        return user;
    }

    // 获取用户权限
    public UserAccess getUserAccessByUserId(Integer userId){
        UserAccess userAccess  = userAccessDao.selectUserAccessByUserId(userId);
        return userAccess;
    }

    @Override
    public User getUser(String email) {
        User user = userDao.selectUserByEmail(email);
        return user;
    }

    @Override
    public Boolean updateUser(User user) {
        Integer flag = userDao.updateUserById(user);
        return flag == 1;
    }
}
