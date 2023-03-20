package com.undina.mainserver.dto;

import com.undina.mainserver.model.Product;
import com.undina.mainserver.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private Long id;

    private String name;

    private String description;

    private  String logo;

    private List<ProductDto> products;

    private UserDto owner;

    private boolean isDeleted;

    private boolean isFrozen;

    private Integer balance;

    private String  status;
}
