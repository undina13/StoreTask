package com.undina.mainserver.service;

import com.undina.mainserver.dto.AdminUpdateProductRequest;
import com.undina.mainserver.dto.NewProductDto;
import com.undina.mainserver.dto.ProductDto;
import com.undina.mainserver.exception.OrganizationNotFoundException;
import com.undina.mainserver.exception.ProductNotFoundException;
import com.undina.mainserver.exception.UserNotOwnerException;
import com.undina.mainserver.mapper.ProductMapper;
import com.undina.mainserver.model.*;
import com.undina.mainserver.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final KeyWordRepository keyWordRepository;
    private final CharacteristicRepository characteristicRepository;
    private final OrganizationRepository organizationRepository;
    private final DiscountRepository discountRepository;
    private final ProductMarkRepository productMarkRepository;
    private final ProductReviewRepository productReviewRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, KeyWordRepository keyWordRepository, CharacteristicRepository characteristicRepository, OrganizationRepository organizationRepository, DiscountRepository discountRepository, ProductMarkRepository productMarkRepository, ProductReviewRepository productReviewRepository) {
        this.productRepository = productRepository;
        this.keyWordRepository = keyWordRepository;
        this.characteristicRepository = characteristicRepository;
        this.organizationRepository = organizationRepository;
        this.discountRepository = discountRepository;
        this.productMarkRepository = productMarkRepository;
        this.productReviewRepository = productReviewRepository;
    }

    @Transactional
    public ProductDto createProduct(Long userId, NewProductDto newProductDto) {
        Product product = ProductMapper.toProductFromNewProductDto(newProductDto);
        List<KeyWord> keyWords = keyWordRepository.findAllByIdIn(newProductDto.getKeyWords());
        product.setKeyWords(keyWords);
        List<Characteristic> characteristics = characteristicRepository
                .findAllByIdIn(newProductDto.getCharacteristics());
        product.setCharacteristics(characteristics);
        Organization organization = organizationRepository.findById(newProductDto.getOrganizationId())
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
        if (!organization.getOwner().getId().equals(userId)) {
            throw new UserNotOwnerException("Only owner og organization can create this product");
        }
        product.setOrganization(organization);
        product.setAvailable(false);
        product.setStatus(Status.CREATED);
        product.setDiscounts(new ArrayList<>());
        product.setMarks(new ArrayList<>());
        product.setReviews(new ArrayList<>());

        return ProductMapper.toProductDtoFromProduct(productRepository.save(product));
    }

    public List<ProductDto> getAdminProductsByStatus(Status status) {
        return productRepository.findAllByStatus(status)
                .stream()
                .map(ProductMapper::toProductDtoFromProduct)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDto updateProductByAdmin(Long productId, AdminUpdateProductRequest adminUpdateProductRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (adminUpdateProductRequest.getName() != null) {
            product.setName(adminUpdateProductRequest.getName());
        }
        if (adminUpdateProductRequest.getDescription() != null) {
            product.setDescription(adminUpdateProductRequest.getDescription());
        }
        if (adminUpdateProductRequest.getOrganization() != null) {
            Organization organization = organizationRepository.findById(adminUpdateProductRequest.getOrganization())
                    .orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
            product.setOrganization(organization);
        }
        if (adminUpdateProductRequest.getPrice() != null) {
            product.setPrice(adminUpdateProductRequest.getPrice());
        }
        if (adminUpdateProductRequest.getCount() != null) {
            product.setCount(adminUpdateProductRequest.getCount());
        }
        if (adminUpdateProductRequest.getDiscounts() != null) {
            product.setDiscounts(discountRepository.findAllByIdIn(adminUpdateProductRequest.getDiscounts()));
        }
        if (adminUpdateProductRequest.getKeyWords() != null) {
            product.setKeyWords(keyWordRepository.findAllByIdIn(adminUpdateProductRequest.getKeyWords()));
        }
        if (adminUpdateProductRequest.getCharacteristics() != null) {
            product.setCharacteristics(characteristicRepository.findAllByIdIn(adminUpdateProductRequest.getCharacteristics()));
        }
        if (adminUpdateProductRequest.getMarks() != null) {
            product.setMarks(productMarkRepository.findAllByIdIn(adminUpdateProductRequest.getMarks()));
        }
        if (adminUpdateProductRequest.getReviews() != null) {
            product.setReviews(productReviewRepository.findAllByIdIn(adminUpdateProductRequest.getReviews()));
        }
        if (adminUpdateProductRequest.getIsAvailable() != null) {
            product.setAvailable(adminUpdateProductRequest.getIsAvailable());
        }
        if (adminUpdateProductRequest.getStatus() != null) {
            product.setStatus(Status.valueOf(adminUpdateProductRequest.getStatus()));
        }
        return ProductMapper.toProductDtoFromProduct(productRepository.save(product));
    }

    @Transactional
    public ProductDto publishProductByAdmin(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setStatus(Status.PUBLISHED);
        product.setAvailable(true);
        return ProductMapper.toProductDtoFromProduct(productRepository.save(product));
    }

    @Transactional
    public ProductDto rejectProductByAdmin(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        product.setStatus(Status.CANCELED);
        return ProductMapper.toProductDtoFromProduct(productRepository.save(product));
    }

    public List<ProductDto> getAvailableProduct(String name, Long organizationId, Integer price, List<Long> keyWords,
                                                List<Long> characteristics) {
        return productRepository.searchAvailableProducts(name, organizationId, price, keyWords, characteristics)
                .stream()
                .map(ProductMapper::toProductDtoFromProduct)
                .collect(Collectors.toList());
    }
}
