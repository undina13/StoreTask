package com.undina.mainserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewProductDto {

    private String name;

    private String description;

    private long organizationId;

    private Integer price;

    private Integer count;

    private List<Long> keyWords;

    private List<Long> characteristics;
}
