package com.sunan.literature.service.impl;

import com.sunan.literature.dao.*;
import com.sunan.literature.entity.*;
import com.sunan.literature.service.ArticleService;
import com.sunan.literature.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private SectionDao sectionDao;
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private SectionUserDao sectionUserDao;
    @Autowired
    private AccessDao accessDao;
    @Autowired
    private UserAccessDao userAccessDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Section> getSectionList() {
        List<Section> sectionList = sectionDao.selectSectionList();
        return sectionList;
    }

    @Override
    public List<Article> getArticleList(Integer sectionId, Integer startId, Integer counts) {
        List<Article> articleList = articleDao.selectArticleListBySectionId(sectionId, startId, counts);
        return articleList;
    }

    public List<Article> getArticleList(Integer startId, Integer counts) {
        List<Article> articleList = articleDao.selectAllArticleListBySectionId(startId, counts);
        return articleList;
    }

    @Override
    public Article getArticleById(Integer id) {
        Article article = articleDao.selectArticleById(id);
        return article;
    }

    @Override
    public List<Review> getReviewList(Integer articleId) {
        List<Review> reviewList = reviewDao.selectReviewListByArticleId(articleId);
        for (int i = 0; i < reviewList.size(); i++) {
            String username = userDao.selectUserById(reviewList.get(i).getUserId()).getUsername();
            reviewList.get(i).setUsername(username);
        }
        return reviewList;
    }

    @Override
    public Boolean publishArticle(String token, Integer sectionId, String articleTitle, String articleContent) {
        Integer userId = JwtUtil.getUserId(token);
        Article article = new Article();
        article.setUserId(userId);
        article.setSectionId(sectionId);
        article.setArticleTitle(articleTitle);
        article.setArticleContent(articleContent);
        Integer flag = articleDao.insertArticle(article);
        return flag == 1;
    }

    @Override
    public Boolean deleteArticle(String token, Integer articleId) {
        Integer userId = JwtUtil.getUserId(token);
        Article article = articleDao.selectArticleById(articleId);
        // 管理员删除
        UserAccess userAccess = userAccessDao.selectUserAccessByUserId(userId);
        if (3 == userAccess.getAccessId()) {
            articleDao.deleteArticleById(articleId);
            return true;
        }

        // 本人删除
        if (userId.equals(article.getUserId())) {
            articleDao.deleteArticleById(articleId);
            return true;
        }
        // 版主删除
        SectionUser sectionUser = sectionUserDao.selectSectionUserByUserId(userId);
        if (sectionUser.getSectionId().equals(article.getSectionId())) {
            articleDao.deleteArticleById(articleId);
            return true;
        }
        return false;
    }

    @Override
    public Boolean editArticle(String token, String articleContent) {
        return null;
    }

    @Override
    public Boolean publishReview(String token, Integer articleId, String reviewContent) {
        Integer userId = JwtUtil.getUserId(token);
        Review review = new Review();
        review.setUserId(userId);
        review.setArticleId(articleId);
        review.setReviewContent(reviewContent);
        reviewDao.insertReview(review);
        return true;
    }

    @Override
    public Boolean publishReview(String nickname, String email, Integer articleId, String reviewContent) {
        return null;
    }

    @Override
    public Boolean deleteReview(String token, Integer reviewId) {
        return null;
    }
}
