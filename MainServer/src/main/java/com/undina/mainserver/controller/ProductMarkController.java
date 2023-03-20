package com.undina.mainserver.controller;

import com.undina.mainserver.dto.NewProductMarkDto;
import com.undina.mainserver.dto.ProductMarkDto;
import com.undina.mainserver.service.ProductMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/productmarks")
@Slf4j
public class ProductMarkController {
    private final ProductMarkService productMarkService;

    @Autowired
    public ProductMarkController(ProductMarkService productMarkService) {
        this.productMarkService = productMarkService;
    }

    @PostMapping
    public ProductMarkDto saveProductMark(@RequestBody NewProductMarkDto newProductMarkDto) {
        log.info("save productMark");
        return productMarkService.saveProductMark(newProductMarkDto);
    }
}
