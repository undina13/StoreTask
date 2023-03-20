package com.undina.mainserver.mapper;

import com.undina.mainserver.dto.ProductMarkDto;
import com.undina.mainserver.model.ProductMark;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMarkMapper {
    public static ProductMarkDto toProductMarkDto (ProductMark productMark){
        return ProductMarkDto.builder()
                .id(productMark.getId())
                .mark(productMark.getMark())
                .user(UserMapper.toUserDto(productMark.getUser()))
                .product(ProductMapper.toProductDtoFromProduct(productMark.getProduct()))
                .build();
    }
}
