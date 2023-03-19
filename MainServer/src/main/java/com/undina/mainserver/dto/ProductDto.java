package com.undina.mainserver.dto;

import com.undina.mainserver.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private Organization organization;

    private Integer price;

    private Integer count;

    private List<Discount> discounts;

    private List<KeyWord> keyWords;

    private List<Characteristic> characteristics;

    private List<ProductReview> reviews;

    private List<ProductMark> marks;

    private boolean isAvailable;

    private Status status;
}
