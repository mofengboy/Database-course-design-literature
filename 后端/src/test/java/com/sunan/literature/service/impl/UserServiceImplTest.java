package com.sunan.literature.service.impl;

import com.sunan.literature.entity.User;
import com.sunan.literature.service.UserService;
import net.bytebuddy.asm.Advice;
import org.assertj.core.error.ShouldBeAfterYear;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void getAllUserInfo() {
        List<User> userList = userService.getAllUserInfo();
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).getUsername());
        }

    }

    @Test
    void testGetAllUserInfo() {
    }

    @Test
    void getTokenByEmailAndPassword() {
    }

    @Test
    void isToken() {
        Integer id = userService.getUserIdByToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dpbk5hbWUiOiJ0ZXN0IiwiZXhwIjoxNTkzMTQyMzk3LCJ1c2VySWQiOiIxIn0.dZz4YdB2-4jUg24etv4BstiP0SWnl_cPYbFN9X0vnv0");
        System.out.println(id);
    }

    @Test
    void register() {
    }

    @Test
    void getUser() {
    }

    @Test
    void updateUser() {
    }
}