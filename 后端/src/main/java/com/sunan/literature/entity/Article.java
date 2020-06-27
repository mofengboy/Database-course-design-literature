package com.sunan.literature.entity;

import lombok.Data;

@Data
public class Article {
    private Integer id;
    private Integer userId;
    // 所属板块
    private Integer sectionId;
    private String articleTitle;
    private String articleContent;
    private String registerTime;
}
