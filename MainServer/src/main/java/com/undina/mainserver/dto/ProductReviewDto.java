package com.undina.mainserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewDto {
    private Long id;

    private String review;

    private ProductDto product;

    private UserDto user;
}
