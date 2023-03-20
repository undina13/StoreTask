package com.undina.mainserver.mapper;

import com.undina.mainserver.dto.PurchaseDto;
import com.undina.mainserver.model.Purchase;
import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class PurchaseMapper {
    public static PurchaseDto toPurchaseDto(Purchase purchase) {
        return PurchaseDto.builder()
                .id(purchase.getId())
                .datePurchase(purchase.getDatePurchase()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .user(UserMapper.toUserDto(purchase.getUser()))
                .product(ProductMapper.toProductDtoFromProduct(purchase.getProduct()))
                .price(purchase.getPrice())
                .count(purchase.getCount())
                .sum(purchase.getSum())
                .build();
    }
}
