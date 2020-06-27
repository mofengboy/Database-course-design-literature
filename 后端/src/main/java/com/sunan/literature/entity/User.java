package com.sunan.literature.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer sex;
    private String registerTime;
}
