package com.undina.mainserver.service;

import com.undina.mainserver.dto.NewProductMarkDto;
import com.undina.mainserver.dto.ProductMarkDto;
import com.undina.mainserver.exception.ObjectNotFoundException;
import com.undina.mainserver.exception.ProductNotFoundException;
import com.undina.mainserver.exception.WrongRequestException;
import com.undina.mainserver.mapper.ProductMapper;
import com.undina.mainserver.mapper.ProductMarkMapper;
import com.undina.mainserver.model.Product;
import com.undina.mainserver.model.ProductMark;
import com.undina.mainserver.model.User;
import com.undina.mainserver.repository.ProductMarkRepository;
import com.undina.mainserver.repository.ProductRepository;
import com.undina.mainserver.repository.PurchaseRepository;
import com.undina.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductMarkService {
    private final ProductMarkRepository productMarkRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private  final PurchaseRepository purchaseRepository;

    @Autowired
    public ProductMarkService(ProductMarkRepository productMarkRepository, UserRepository userRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository) {
        this.productMarkRepository = productMarkRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public ProductMarkDto saveProductMark(NewProductMarkDto newProductMarkDto) {
        User user = userRepository.findById(newProductMarkDto.getUserId()).orElseThrow(() ->
                new ObjectNotFoundException("user  not found"));
        Product product = productRepository.findById(newProductMarkDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if(!purchaseRepository.existsPurchaseByUserAndProduct(user, product)){
            throw new WrongRequestException("you can write mark only after purchase ");
        }
        ProductMark productMark = ProductMark.builder()
                .user(user)
                .product(product)
                .mark(newProductMarkDto.getMark())
                .build();
        return ProductMarkMapper.toProductMarkDto(productMarkRepository.save(productMark));
    }


}
