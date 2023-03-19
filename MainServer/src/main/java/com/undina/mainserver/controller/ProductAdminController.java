package com.undina.mainserver.controller;

import com.undina.mainserver.dto.AdminUpdateProductRequest;
import com.undina.mainserver.dto.ProductDto;
import com.undina.mainserver.model.Status;
import com.undina.mainserver.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/products")
@Slf4j
public class ProductAdminController {
    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{statusString}")
    public List<ProductDto> getAdminProductsByStatus(@PathVariable String statusString) {
        log.info("get admin product by status");
        Status status = Status.valueOf(statusString);
        return productService.getAdminProductsByStatus(status);
    }

    @PutMapping("/{productId}")
    public ProductDto updateProductByAdmin(@PathVariable Long productId,
                                           @RequestBody AdminUpdateProductRequest adminUpdateEventRequest) {
        log.info("update product {} by admin", productId);
        return productService.updateProductByAdmin(productId, adminUpdateEventRequest);
    }

    @PatchMapping("/{productId}/publish")
    public ProductDto publishEventByAdmin(@PathVariable Long productId) {
        log.info("publish product{} by admin", productId);
        return productService.publishProductByAdmin(productId);
    }

    @PatchMapping("/{productId}/reject")
    public ProductDto rejectEventByAdmin(@PathVariable Long productId) {
        log.info("reject product {} by admin", productId);
        return productService.rejectProductByAdmin(productId);
    }
}
