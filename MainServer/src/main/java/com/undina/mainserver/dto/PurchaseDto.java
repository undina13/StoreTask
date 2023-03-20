package com.undina.mainserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {

    private Long id;

    private String datePurchase;

    private UserDto user;

    private ProductDto product;

    private Integer price;

    private Integer count;

    private Integer sum;
}
