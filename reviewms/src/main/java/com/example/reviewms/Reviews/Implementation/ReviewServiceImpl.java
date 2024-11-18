package com.example.reviewms.Reviews.Implementation;

import com.example.reviewms.Reviews.Review;
import com.example.reviewms.Reviews.ReviewRepository;
import com.example.reviewms.Reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
//    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        /*use custome methods findByCompanyId so create a method in ReviewRepository*/
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
//        Company company = companyService.getCompanyById(companyId);
        if(companyId != null && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
//            updatedReview.setCompany(companyService.getCompanyById(companyId));
//            updatedReview.setId(reviewId);
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
//            Review review = reviewRepository.findById(reviewId).orElse(null);
//            Company company = review.getCompany();
//            company.getReviews().remove(review);
//            companyService.updateCompany(company, companyId);
//            review.setCompany(null); //this removes all the references from the company side as well from the review side

            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
