package com.undina.mainserver.service;

import com.undina.mainserver.dto.NewOrganizationDto;
import com.undina.mainserver.dto.OrganizationDto;
import com.undina.mainserver.exception.ObjectNotFoundException;
import com.undina.mainserver.exception.OrganizationNotFoundException;
import com.undina.mainserver.mapper.OrganizationMapper;
import com.undina.mainserver.model.Organization;
import com.undina.mainserver.model.Product;
import com.undina.mainserver.model.Status;
import com.undina.mainserver.repository.OrganizationRepository;
import com.undina.mainserver.repository.ProductRepository;
import com.undina.mainserver.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrganizationService(OrganizationRepository organizationRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.organizationRepository = organizationRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public OrganizationDto createOrganization(Long userId, NewOrganizationDto newOrganizationDto) {
        Organization organization = OrganizationMapper.toOrganization(newOrganizationDto);
        organization.setBalance(0);
        organization.setFrozen(false);
        organization.setStatus(Status.CREATED);
        organization.setProducts(new ArrayList<>());
        organization.setOwner(userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found")));
        return OrganizationMapper.toOrganizationDto(organizationRepository.save(organization));
    }

    public List<OrganizationDto> getAdminOrganizationsByStatus(Status status) {
        return organizationRepository.findAllByStatus(status)
                .stream()
                .map(OrganizationMapper::toOrganizationDto)
                .collect(Collectors.toList());
    }

    public OrganizationDto publishOrganizationByAdmin(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
        organization.setStatus(Status.PUBLISHED);
        return OrganizationMapper.toOrganizationDto(organizationRepository.save(organization));
    }

    public OrganizationDto rejectOrganizationByAdmin(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
        organization.setStatus(Status.CANCELED);
        return OrganizationMapper.toOrganizationDto(organizationRepository.save(organization));
    }

    public OrganizationDto frozeOrganizationByAdmin(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
        organization.setFrozen(true);
        List<Product> products = setFrozen(organization.getProducts());
        organization.setProducts(products);
        return OrganizationMapper.toOrganizationDto(organizationRepository.save(organization));
    }

    public OrganizationDto unfrozeOrganizationByAdmin(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
        organization.setFrozen(false);
        return OrganizationMapper.toOrganizationDto(organizationRepository.save(organization));
    }

    public OrganizationDto deleteOrganizationByAdmin(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
        organization.setFrozen(true);
        List<Product> products = setFrozen(organization.getProducts());
        organization.setProducts(products);
        organization.setDeleted(true);
        return OrganizationMapper.toOrganizationDto(organizationRepository.save(organization));
    }

    private List<Product> setFrozen(List<Product> products) {
        for (Product product : products) {
            product.setAvailable(false);
        }
        productRepository.saveAll(products);
        return products;
    }
}
