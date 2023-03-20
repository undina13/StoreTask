package com.undina.mainserver.controller;

import com.undina.mainserver.dto.NewReviewDto;
import com.undina.mainserver.dto.ProductReviewDto;
import com.undina.mainserver.service.ProductReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/productreviews")
@Slf4j
public class ProductReviewController {
    private final ProductReviewService productReviewService;

    @Autowired
    public ProductReviewController(ProductReviewService productReviewService) {
        this.productReviewService = productReviewService;
    }

    @PostMapping
    public ProductReviewDto saveProductReview(@RequestBody NewReviewDto newReviewDto) {
        log.info("save productReview");
        return productReviewService.saveProductReview(newReviewDto);
    }
}
