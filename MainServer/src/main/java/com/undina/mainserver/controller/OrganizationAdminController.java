package com.undina.mainserver.controller;

import com.undina.mainserver.dto.OrganizationDto;
import com.undina.mainserver.model.Status;
import com.undina.mainserver.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/organizations")
@Slf4j
public class OrganizationAdminController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationAdminController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/{statusString}")
    public List<OrganizationDto> getAdminOrganizationsByStatus(@PathVariable String statusString) {
        log.info("get admin organization by status");
        Status status = Status.valueOf(statusString);
        return organizationService.getAdminOrganizationsByStatus(status);
    }


    @PatchMapping("/{organizationId}/publish")
    public OrganizationDto publishOrganizationByAdmin(@PathVariable Long organizationId) {
        log.info("publish organization{} by admin", organizationId);
        return organizationService.publishOrganizationByAdmin(organizationId);
    }

    @PatchMapping("/{organizationId}/reject")
    public OrganizationDto rejectOrganizationByAdmin(@PathVariable Long organizationId) {
        log.info("reject organization {} by admin", organizationId);
        return organizationService.rejectOrganizationByAdmin(organizationId);
    }

    @PatchMapping("/{organizationId}/froze")
    public OrganizationDto frozeOrganizationByAdmin(@PathVariable Long organizationId) {
        log.info("froze organization {} by admin", organizationId);
        return organizationService.frozeOrganizationByAdmin(organizationId);
    }

    @PatchMapping("/{organizationId}/unfroze")
    public OrganizationDto unfrozeOrganizationByAdmin(@PathVariable Long organizationId) {
        log.info("unfroze organization {} by admin", organizationId);
        return organizationService.unfrozeOrganizationByAdmin(organizationId);
    }

    @PatchMapping("/{organizationId}/delete")
    public OrganizationDto deleteOrganizationByAdmin(@PathVariable Long organizationId) {
        log.info("delete organization {} by admin", organizationId);
        return organizationService.deleteOrganizationByAdmin(organizationId);
    }
}
