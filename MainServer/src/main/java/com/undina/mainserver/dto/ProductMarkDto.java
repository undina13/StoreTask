package com.undina.mainserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMarkDto {
    private Long id;

    private Integer mark;

    private ProductDto product;

    private UserDto user;
}
