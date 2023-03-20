package com.undina.mainserver.service;

import com.undina.mainserver.dto.PurchaseDto;
import com.undina.mainserver.exception.ObjectNotFoundException;
import com.undina.mainserver.exception.WrongRequestException;
import com.undina.mainserver.mapper.PurchaseMapper;
import com.undina.mainserver.model.*;
import com.undina.mainserver.repository.OrganizationRepository;
import com.undina.mainserver.repository.ProductRepository;
import com.undina.mainserver.repository.PurchaseRepository;
import com.undina.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository, ProductRepository productRepository, OrganizationRepository organizationRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public PurchaseDto createPurchase(Long userId, Long productId, Integer count) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ObjectNotFoundException("user with id = " + userId + " not found"));
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ObjectNotFoundException("user with id = " + productId + " not found"));
        if (product.getCount() < count) {
            throw new WrongRequestException("this product not have this count");
        }
        Integer disc = product.getDiscounts()
                .stream()
                .filter(discount -> discount.getDateOfEnd().
                        isAfter(LocalDate.now().minusDays(1)))
                .map(Discount::getPercent)
                .reduce(Integer::sum).orElse(0);
        if (disc > 99) {
            throw new WrongRequestException("this discount too big");
        }
        int price = product.getPrice() - product.getPrice() * disc / 100;
        int sum = price * count;
        if (user.getBalance() < sum) {
            throw new WrongRequestException("you do not have money at your balance");
        }
        product.setCount(product.getCount() - count);
        productRepository.save(product);
        user.setBalance(user.getBalance() - sum);
        userRepository.save(user);
        Organization organization = product.getOrganization();
        organization.setBalance((int) (organization.getBalance() + (0.9 * sum)));
        organizationRepository.save(organization);
        Purchase purchase = Purchase.builder()
                .datePurchase(LocalDateTime.now())
                .user(user)
                .product(product)
                .price(price)
                .count(count)
                .sum(sum)
                .build();
        return PurchaseMapper.toPurchaseDto(purchaseRepository.save(purchase));
    }

    @Transactional
    public PurchaseDto returnPurchase(Long userId, Long purchaseId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ObjectNotFoundException("user with id = " + userId + " not found"));
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() ->
                new ObjectNotFoundException("user with id = " + purchaseId + " not found"));
        if (purchase.getDatePurchase().isBefore(LocalDateTime.now().minusDays(1))) {
            throw new WrongRequestException("you can do return for 24 hours from purchase ");
        }
        Product product = purchase.getProduct();
        product.setCount(product.getCount() + purchase.getCount());
        productRepository.save(product);

        user.setBalance(user.getBalance() + purchase.getSum());
        userRepository.save(user);
        Organization organization = product.getOrganization();
        organization.setBalance((int) (organization.getBalance() - (0.9 * purchase.getSum())));
        organizationRepository.save(organization);
        Purchase purchase1 = Purchase.builder()
                .datePurchase(LocalDateTime.now())
                .user(purchase.getUser())
                .product(product)
                .price(purchase.getPrice())
                .count(purchase.getCount())
                .sum(-purchase.getSum())
                .build();
        return PurchaseMapper.toPurchaseDto(purchaseRepository.save(purchase1));
    }

    public List<PurchaseDto> getPurchaseByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ObjectNotFoundException("user with id = " + userId + " not found"));
        return purchaseRepository.findAllByUser(user)
                .stream()
                .map(PurchaseMapper::toPurchaseDto)
                .collect(Collectors.toList());
    }
}
