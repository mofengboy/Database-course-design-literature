package com.sunan.literature.entity;

import lombok.Data;

@Data
public class UserAccess {
    private Integer id;
    private Integer userId;
    private Integer accessId;
    private String registerTime;
}
