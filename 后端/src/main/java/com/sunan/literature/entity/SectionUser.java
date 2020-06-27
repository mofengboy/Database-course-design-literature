package com.sunan.literature.entity;

import lombok.Data;

@Data
public class SectionUser {
    private Integer id;
    private Integer sectionId;
    private Integer userId;
    private String registerTime;
}
