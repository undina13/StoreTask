package com.undina.mainserver.mapper;

import com.undina.mainserver.dto.ProductMarkDto;
import com.undina.mainserver.dto.ProductReviewDto;
import com.undina.mainserver.model.ProductMark;
import com.undina.mainserver.model.ProductReview;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductReviewMapper {
    public static ProductReviewDto toProductReviewDto (ProductReview productReview){
        return ProductReviewDto.builder()
                .id(productReview.getId())
                .review(productReview.getReview())
                .user(UserMapper.toUserDto(productReview.getUser()))
                .product(ProductMapper.toProductDtoFromProduct(productReview.getProduct()))
                .build();
    }
}
