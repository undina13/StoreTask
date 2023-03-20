package com.undina.mainserver.controller;

import com.undina.mainserver.dto.ProductDto;
import com.undina.mainserver.dto.PurchaseDto;
import com.undina.mainserver.model.Status;
import com.undina.mainserver.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/purchases")
@Slf4j
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @PostMapping("/{userId}/{productId}/{count}")
    public PurchaseDto createPurchase(@PathVariable Long userId,
                                      @PathVariable Long productId,
                                      @PathVariable Integer count) {
        log.info("create purchase  user{} product {} ", userId, productId );
        return purchaseService.createPurchase(userId, productId, count);
    }

    @PostMapping("/{userId}/{purchaseId}")
    public PurchaseDto returnPurchase(@PathVariable Long userId,
                                      @PathVariable Long purchaseId) {
        log.info("return purchase  user{} product {} ", userId, purchaseId );
        return purchaseService.returnPurchase(userId, purchaseId);
    }

    @GetMapping("/{userId}")
    public List<PurchaseDto> getPurchaseByUserId(@PathVariable Long userId) {
        log.info("get  purchase  user{} ", userId);
        return purchaseService.getPurchaseByUserId(userId);
    }
}
