package com.undina.mainserver.service;

import com.undina.mainserver.dto.DiscountDto;
import com.undina.mainserver.dto.NewDiscountDto;
import com.undina.mainserver.mapper.DiscountMapper;
import com.undina.mainserver.model.Discount;
import com.undina.mainserver.model.Product;
import com.undina.mainserver.repository.DiscountRepository;
import com.undina.mainserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository, ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public DiscountDto createDiscount(NewDiscountDto newDiscountDto) {
        Discount discount = DiscountMapper.toDiscount(newDiscountDto);
        List<Product> products = productRepository.findAllById(newDiscountDto.getProducts());
        discount.setProducts(products);
        return DiscountMapper.toDiscountDto(discountRepository.save(discount));
    }

    public List<DiscountDto> getPastDiscount() {
        LocalDate dateNow = LocalDate.now();
        return discountRepository.findAllByDateOfEndBefore(dateNow)
                .stream()
                .map(DiscountMapper::toDiscountDto)
                .collect(Collectors.toList());
    }

    public void deleteDiscount(Long discountId) {
        discountRepository.deleteById(discountId);
    }
}
