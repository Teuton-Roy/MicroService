package com.example.reviewms.Reviews;

import java.util.*;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
//    void addReview(Long companyId, Review review);
    boolean addReview(Long companyId, Review review); //boolean because, if company doesn't exit then how handel the review...
    Review getReview(Long reviewId);
    boolean updateReview(Long reviewId, Review review);

    boolean deleteReview(Long reviewId);
}
