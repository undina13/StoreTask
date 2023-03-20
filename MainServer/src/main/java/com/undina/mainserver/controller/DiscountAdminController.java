package com.undina.mainserver.controller;

import com.undina.mainserver.dto.DiscountDto;
import com.undina.mainserver.dto.NewDiscountDto;
import com.undina.mainserver.service.DiscountService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/discounts")
@Slf4j
public class DiscountAdminController {
    private final DiscountService discountService;

    @Autowired
    public DiscountAdminController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping
    public DiscountDto createDiscount(@RequestBody @Valid NewDiscountDto newDiscountDto) {
        log.info("create discount {} ", newDiscountDto);
        return discountService.createDiscount(newDiscountDto);
    }

    @GetMapping
    public List<DiscountDto> getPastDiscount() {
        log.info("get past discount");
        return discountService.getPastDiscount();
    }

    @DeleteMapping("/discountId")
    public void deleteDiscount(@PathVariable Long discountId) {
        log.info("delete discount {} ", discountId);
        discountService.deleteDiscount(discountId);
    }
}
