package com.undina.mainserver.controller;

import com.undina.mainserver.dto.NewProductDto;
import com.undina.mainserver.dto.ProductDto;
import com.undina.mainserver.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/{userId}/products")
@Slf4j
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDto createProduct(@PathVariable Long userId,
                                    @RequestBody @Valid NewProductDto newProductDto) {
        log.info("create product  userId{} {}", userId, newProductDto);
        return productService.createProduct(userId, newProductDto);
    }
}
