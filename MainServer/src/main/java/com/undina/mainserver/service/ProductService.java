package com.undina.mainserver.service;

import com.undina.mainserver.dto.NewProductDto;
import com.undina.mainserver.dto.ProductDto;
import com.undina.mainserver.exception.OrganizationNotFoundException;
import com.undina.mainserver.exception.UserNotOwnerException;
import com.undina.mainserver.mapper.ProductMapper;
import com.undina.mainserver.model.*;
import com.undina.mainserver.repository.CharacteristicRepository;
import com.undina.mainserver.repository.KeyWordRepository;
import com.undina.mainserver.repository.OrganizationRepository;
import com.undina.mainserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final KeyWordRepository keyWordRepository;
    private final CharacteristicRepository characteristicRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, KeyWordRepository keyWordRepository, CharacteristicRepository characteristicRepository, OrganizationRepository organizationRepository) {
        this.productRepository = productRepository;
        this.keyWordRepository = keyWordRepository;
        this.characteristicRepository = characteristicRepository;
        this.organizationRepository = organizationRepository;
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

        return ProductMapper.toProductDtoFromProduct(productRepository.save(product));
    }
}
