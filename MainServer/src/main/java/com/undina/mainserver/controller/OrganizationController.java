package com.undina.mainserver.controller;

import com.undina.mainserver.dto.NewOrganizationDto;
import com.undina.mainserver.dto.OrganizationDto;
import com.undina.mainserver.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/{userId}/organizations")
@Slf4j
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @PostMapping
    public OrganizationDto createOrganization(@PathVariable Long userId,
                                              @RequestBody @Valid NewOrganizationDto newOrganizationDto) {
        log.info("create organization  userId{} {}", userId, newOrganizationDto);
        return organizationService.createOrganization(userId, newOrganizationDto);
    }


}
