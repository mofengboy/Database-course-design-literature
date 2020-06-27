package com.sunan.literature.controller.impl;

import com.sunan.literature.common.GenericResponse;
import com.sunan.literature.common.ServiceError;
import com.sunan.literature.controller.UserController;
import com.sunan.literature.entity.User;
import com.sunan.literature.entity.UserAccess;
import com.sunan.literature.exception.WrongPasswordException;
import com.sunan.literature.service.UserService;
import com.sunan.literature.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:8081")
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public GenericResponse index() {
        return GenericResponse.response(ServiceError.NORMAL);
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public GenericResponse login(String email, String password) {
        try {
            if ("".equals(email) || "".equals(password)) {
                throw new WrongPasswordException();
            }
            String token = userService.getTokenByEmailAndPassword(email, password);
            return GenericResponse.response(ServiceError.NORMAL, token);
        } catch (WrongPasswordException e) {
            return GenericResponse.response(ServiceError.GLOBAL_ERR_WRONG_PASSWORD);
        }
    }

    @Override
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public GenericResponse register(String username, String password, String email, Integer sex) {
        if ("".equals(username) || "".equals(password) || "".equals(email)) {

            return GenericResponse.response(ServiceError.INVALID_PARAMETER);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setEmail(email);
        Boolean flag = userService.register(user);
        if (flag) {
            return GenericResponse.response(ServiceError.NORMAL);
        } else {
            return GenericResponse.response(ServiceError.UN_KNOW_ERROR);
        }
    }

    @Override
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public GenericResponse getUserInfo(String token) {
        Integer userId = userService.getUserIdByToken(token);
        User user = userService.getUser(userId);
        UserAccess userAccess = userService.getUserAccessByUserId(userId);
        Object[] arr = new Object[2];
        arr[0] = user;
        if (userAccess != null) {
            arr[1] = userAccess;
        }
        return GenericResponse.response(ServiceError.NORMAL, arr);
    }

    @Override
    @RequestMapping(value = "/getALLUserInfo", method = RequestMethod.POST)
    public GenericResponse getALLUserInfo(String token) {
        return null;
    }

    @Override
    @RequestMapping(value = "/getUserInfoById", method = RequestMethod.POST)
    public GenericResponse getUserInfo(Integer id) {
        User user = userService.getUser(id);
        UserAccess userAccess = userService.getUserAccessByUserId(id);
        Object[] arr = new Object[2];
        arr[0] = user;
        if (userAccess != null) {
            arr[1] = userAccess;
        }
        return GenericResponse.response(ServiceError.NORMAL, arr);
    }
}
