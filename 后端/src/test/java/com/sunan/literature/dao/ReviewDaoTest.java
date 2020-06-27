package com.sunan.literature.dao;

import com.sunan.literature.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewDaoTest {
    @Autowired
    private ReviewDao reviewDao;

    @Test
    void selectReviewListByArticleId() {
    }

    @Test
    void selectReviewListByUserId() {
    }

    @Test
    void insertReview() {
        Review review = new Review();
        review.setArticleId(1);
        review.setToReviewId(0);
        review.setUserId(1);
        review.setReviewContent("哈哈哈哈哈");
        reviewDao.insertReview(review);
    }

    @Test
    void deleteReviewById() {
    }
}