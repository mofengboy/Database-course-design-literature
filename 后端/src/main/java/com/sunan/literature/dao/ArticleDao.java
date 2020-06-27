package com.sunan.literature.dao;

import com.sunan.literature.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDao {

    /**
     * 通过板块id按需获取文章摘要列表
     *
     * @param sectionId      板块id
     * @param startId 文章列表开始id
     * @param counts  文章数量
     * @return
     */
    List<Article> selectArticleListBySectionId(Integer sectionId, Integer startId, Integer counts);
    List<Article> selectAllArticleListBySectionId(Integer startId, Integer counts);

    Article selectArticleById(Integer id);

    Integer insertArticle(Article article);

    Integer updateArticleById(Article article);

    Integer deleteArticleById(Integer id);


}
