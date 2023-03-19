package com.undina.mainserver.repository;

import com.undina.mainserver.model.Product;
import com.undina.mainserver.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByStatus(Status status);
}