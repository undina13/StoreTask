package com.undina.mainserver.mapper;

import com.undina.mainserver.dto.DiscountDto;
import com.undina.mainserver.dto.NewDiscountDto;
import com.undina.mainserver.dto.ProductDto;
import com.undina.mainserver.model.Discount;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DiscountMapper {
    public static Discount toDiscount(NewDiscountDto newDiscountDto) {
        return Discount.builder()
                .dateOfEnd(LocalDate.parse(newDiscountDto.getDateOfEnd(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .percent(newDiscountDto.getPercent())
                .build();
    }

    public static DiscountDto toDiscountDto(Discount discount) {
        List<ProductDto> productDtoList = discount.getProducts()
                .stream()
                .map(ProductMapper::toProductDtoFromProduct)
                .collect(Collectors.toList());
        return DiscountDto.builder()
                .id(discount.getId())
                .products(productDtoList)
                .percent(discount.getPercent())
                .dateOfEnd(discount.getDateOfEnd()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
