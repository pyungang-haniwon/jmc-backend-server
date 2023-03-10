package com.example.jmcbackend.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.jmcbackend.review.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Long countByStoreId(Long storeId);

    List<Review> findAllByUserId(String userId);


}
