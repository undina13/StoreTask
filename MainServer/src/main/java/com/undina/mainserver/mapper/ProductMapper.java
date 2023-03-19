package com.undina.mainserver.mapper;

import com.undina.mainserver.dto.NewProductDto;
import com.undina.mainserver.dto.ProductDto;
import com.undina.mainserver.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {
    public static Product toProductFromNewProductDto(NewProductDto newProductDto) {
        return Product.builder()
                .name(newProductDto.getName())
                .description(newProductDto.getDescription())
                .price(newProductDto.getPrice())
                .count(newProductDto.getCount())
                .build();
    }

    public static ProductDto toProductDtoFromProduct(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .organization(product.getOrganization())
                .price(product.getPrice())
                .count(product.getCount())
                .discounts(product.getDiscounts())
                .keyWords(product.getKeyWords())
                .characteristics(product.getCharacteristics())
                .reviews(product.getReviews())
                .marks(product.getMarks())
                .isAvailable(product.isAvailable())
                .status(product.getStatus())
                .build();
    }
}
