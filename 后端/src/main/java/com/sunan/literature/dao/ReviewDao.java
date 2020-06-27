package com.sunan.literature.dao;

import com.sunan.literature.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

import java.util.List;

@Mapper
public interface ReviewDao {
    List<Review> selectReviewListByArticleId(Integer ArticleId);

    List<Review> selectReviewListByUserId(Integer userId);

    Integer insertReview(Review review);

    Integer deleteReviewById(Integer id);
}
