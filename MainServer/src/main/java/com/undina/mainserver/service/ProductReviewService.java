package com.undina.mainserver.service;

import com.undina.mainserver.dto.NewProductMarkDto;
import com.undina.mainserver.dto.NewReviewDto;
import com.undina.mainserver.dto.ProductMarkDto;
import com.undina.mainserver.dto.ProductReviewDto;
import com.undina.mainserver.exception.ObjectNotFoundException;
import com.undina.mainserver.exception.ProductNotFoundException;
import com.undina.mainserver.exception.WrongRequestException;
import com.undina.mainserver.mapper.ProductMarkMapper;
import com.undina.mainserver.mapper.ProductReviewMapper;
import com.undina.mainserver.model.Product;
import com.undina.mainserver.model.ProductMark;
import com.undina.mainserver.model.ProductReview;
import com.undina.mainserver.model.User;
import com.undina.mainserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductReviewService {
    private final ProductReviewRepository productReviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private  final PurchaseRepository purchaseRepository;

    @Autowired
    public ProductReviewService(ProductReviewRepository productReviewRepository, UserRepository userRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.productReviewRepository = productReviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public ProductReviewDto saveProductReview(NewReviewDto newReviewDto) {
        User user = userRepository.findById(newReviewDto.getUserId()).orElseThrow(() ->
                new ObjectNotFoundException("user  not found"));
        Product product = productRepository.findById(newReviewDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if(!purchaseRepository.existsPurchaseByUserAndProduct(user, product)){
            throw new WrongRequestException("you can write review only after purchase ");
        }
        ProductReview productReview = ProductReview.builder()
                .user(user)
                .product(product)
                .review(newReviewDto.getReview())
                .build();
        return ProductReviewMapper.toProductReviewDto(productReviewRepository.save(productReview));
    }


}
