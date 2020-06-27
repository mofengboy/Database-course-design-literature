package com.sunan.literature.entity;

import lombok.Data;

@Data
public class Review {
    private Integer id;
    private String username;
    private Integer articleId;
    private Integer toReviewId;
    private Integer userId;
    private String reviewContent;
    private String registerTime;
}
