package com.sunan.literature.controller.impl;

import com.sunan.literature.common.GenericResponse;
import com.sunan.literature.common.ServiceError;
import com.sunan.literature.controller.ArticleController;
import com.sunan.literature.entity.Article;
import com.sunan.literature.entity.Review;
import com.sunan.literature.service.ArticleService;
import com.sunan.literature.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "/article")
public class ArticleControllerImpl implements ArticleController {
    @Autowired
    private ArticleServiceImpl articleService;


    @Override
    @RequestMapping(value = "/getArticleList", method = RequestMethod.POST)
    public GenericResponse getArticleList(Integer startId, Integer counts) {
        List<Article> articleList = articleService.getArticleList(startId, counts);
        return GenericResponse.response(ServiceError.NORMAL, articleList);
    }

    @Override
    @RequestMapping(value = "/getArticleListBySectionId", method = RequestMethod.POST)
    public GenericResponse getArticleListBySectionId(Integer sectionId, Integer startId, Integer counts) {
        List<Article> articleList = articleService.getArticleList(sectionId, startId, counts);
        return GenericResponse.response(ServiceError.NORMAL, articleList);
    }

    @Override
    @RequestMapping(value = "/getArticle", method = RequestMethod.POST)
    public GenericResponse getArticle(Integer id) {
        Article article = articleService.getArticleById(id);
        return GenericResponse.response(ServiceError.NORMAL, article);
    }

    @Override
    @RequestMapping(value = "/publishArticle", method = RequestMethod.POST)
    public GenericResponse publishArticle(String token, Integer sectionId, String articleTitle, String articleContent) {
        Boolean publishArticle = articleService.publishArticle(token, sectionId, articleTitle, articleContent);
        return GenericResponse.response(ServiceError.NORMAL, publishArticle);
    }

    @Override
    @RequestMapping(value = "/getReviewList", method = RequestMethod.POST)
    public GenericResponse getReviewList(Integer id) {
        List<Review> reviewList = articleService.getReviewList(id);
        return GenericResponse.response(ServiceError.NORMAL, reviewList);
    }

    @Override
    @RequestMapping(value = "/publishReview", method = RequestMethod.POST)
    public GenericResponse publishReview(String token, Integer articleId, String content) {
        articleService.publishReview(token, articleId, content);
        return GenericResponse.response(ServiceError.NORMAL);
    }

    @Override
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    public GenericResponse deleteArticle(String token, Integer articleId) {
        Boolean flag = articleService.deleteArticle(token, articleId);
        return GenericResponse.response(ServiceError.NORMAL, flag);
    }
}
