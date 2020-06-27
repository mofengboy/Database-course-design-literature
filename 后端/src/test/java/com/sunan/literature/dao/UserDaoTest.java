package com.sunan.literature.dao;

import com.sunan.literature.entity.User;
import org.assertj.core.error.ShouldBeAfterYear;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    void selectAll() {
        List<User> userList = userDao.selectAll();
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).getUsername());
        }
    }

    @Test
    void selectUserByEmailAndPassword() {
        User user = userDao.selectUserByEmailAndPassword("test","12345");
        System.out.println(user.getId());
    }

    @Test
    void selectUserById() {
        User user = userDao.selectUserById(1);
        System.out.println(user.getUsername());
    }

    @Test
    void selectUserByEmail() {
        User user = userDao.selectUserByEmail("test");
        System.out.println(user.getUsername());
    }

    @Test
    void insertUser() {
        User user = new User();
        user.setUsername("test002");
        user.setEmail("test002");
        user.setPassword("easdf");
        user.setSex(-1);
        int flag = userDao.insertUser(user);
        System.out.println(flag);
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void updateUserById() {
    }
}