package com.undina.mainserver.repository;

import com.undina.mainserver.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    List<ProductReview> findAllByIdIn(List<Long> ids);
}
