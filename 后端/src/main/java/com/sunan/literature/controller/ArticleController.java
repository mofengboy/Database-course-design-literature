package com.sunan.literature.controller;

import com.sunan.literature.common.GenericResponse;

public interface ArticleController {
    GenericResponse getArticleList(Integer startId, Integer counts);

    GenericResponse getArticleListBySectionId(Integer sectionId, Integer startId, Integer counts);

    GenericResponse getArticle(Integer id);

    GenericResponse publishArticle(String token, Integer sectionId, String articleTitle, String articleContent);

    GenericResponse getReviewList(Integer id);

    GenericResponse publishReview(String token, Integer articleId, String content);

    GenericResponse deleteArticle(String token, Integer articleId);
}
