package com.example.reviewms.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId);
    /* with this declaration spring data JPA automatically generate an implementation for findByCompanyId() method
     at run-time
     what it will do is...

     findBy: looking to find something
     CompanyId: it's one field that I have present in database
     findByCompanyId(): give me all the records by companyId
     */
}
