package com.sunan.literature.service;

import com.sunan.literature.entity.Article;
import com.sunan.literature.entity.Review;
import com.sunan.literature.entity.Section;

import java.util.List;

public interface ArticleService {
    // 获取板块id
    List<Section> getSectionList();

    /**
     * 获取文章列表
     *
     * @param sectionId 板块id
     * @param startId   偏移量
     * @param counts    数量
     * @return
     */
    List<Article> getArticleList(Integer sectionId, Integer startId, Integer counts);

    // 获取文章详情
    Article getArticleById(Integer id);

    // 获取评论
    List<Review> getReviewList(Integer articleId);

    // 发文章
    Boolean publishArticle(String token, Integer sectionId,String articleTitle, String articleContent);

    // 删除文章
    Boolean deleteArticle(String token, Integer articleId);

    // 修改文章
    Boolean editArticle(String token, String articleContent);

    // 发评论
    Boolean publishReview(String token, Integer articleId, String reviewContent);

    Boolean publishReview(String nickname, String email, Integer articleId, String reviewContent);

    // 删除评论
    Boolean deleteReview(String token, Integer reviewId);
}
