package com.sunan.literature.dao;

import com.sunan.literature.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleDaoTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    void selectArticleListBySectionId() {
        List<Article> articleList = articleDao.selectArticleListBySectionId(1,0,10);
        for (int i = 0; i < articleList.size(); i++) {
            System.out.println(articleList.get(i).getArticleTitle());
        }

    }

    @Test
    void selectArticleById() {
    }

    @Test
    void insertArticle() {
        Article article = new Article();
        article.setUserId(1);
        article.setSectionId(1);
        article.setArticleTitle("第2篇文章");
        article.setArticleContent("我是第22222篇文章哦");
        articleDao.insertArticle(article);
    }

    @Test
    void updateArticleById() {
    }

    @Test
    void deleteArticleById() {
    }
}