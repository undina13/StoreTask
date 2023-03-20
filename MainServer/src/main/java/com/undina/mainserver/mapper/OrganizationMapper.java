package com.undina.mainserver.mapper;

import com.undina.mainserver.dto.NewOrganizationDto;
import com.undina.mainserver.dto.OrganizationDto;
import com.undina.mainserver.dto.ProductDto;
import com.undina.mainserver.model.Organization;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class OrganizationMapper {
    public static Organization toOrganization(NewOrganizationDto newOrganizationDto) {
        return Organization.builder()
                .name(newOrganizationDto.getName())
                .description(newOrganizationDto.getDescription())
                .logo(newOrganizationDto.getLogo())
                .build();
    }

    public static OrganizationDto toOrganizationDto(Organization organization) {
        List<ProductDto> products = organization.getProducts()
                .stream()
                .map(ProductMapper::toProductDtoFromProduct)
                .collect(Collectors.toList());
        return OrganizationDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .description(organization.getDescription())
                .logo(organization.getLogo())
                .products(products)
                .owner(UserMapper.toUserDto(organization.getOwner()))
                .isDeleted(organization.isDeleted())
                .isFrozen(organization.isFrozen())
                .balance(organization.getBalance())
                .status(organization.getStatus().toString())
                .build();    }
}
