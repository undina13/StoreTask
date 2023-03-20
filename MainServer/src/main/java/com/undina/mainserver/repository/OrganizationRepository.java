package com.undina.mainserver.repository;

import com.undina.mainserver.model.Organization;
import com.undina.mainserver.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findById(Long id);

    List<Organization> findAllByStatus(Status status);
}
