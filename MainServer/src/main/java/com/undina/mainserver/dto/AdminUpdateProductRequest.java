package com.undina.mainserver.dto;

import com.undina.mainserver.model.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdateProductRequest {
    private Long id;

    private String name;

    private String description;

    private Long organization;

    private Integer price;

    private Integer count;

    private List<Long> discounts;

    private List<Long> keyWords;

    private List<Long> characteristics;

    private List<Long> reviews;

    private List<Long> marks;

    private Boolean isAvailable;

    private String status;
}
