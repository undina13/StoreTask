package com.undina.mainserver.repository;

import com.undina.mainserver.model.Product;
import com.undina.mainserver.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByStatus(Status status);

    @Query("SELECT p FROM Product AS p " +
            "WHERE ((:name) IS NULL OR p.name = :name) " +
            "AND ((:organizationId) IS NULL OR p.organization.id = :organizationId)" +
            "AND ((:price) IS NULL OR p.price = :price) " +
            "AND ((:keyWords) IS NULL OR p.keyWords IN :keyWords) " +
            "AND ((:characteristics) IS NULL OR p.characteristics IN :characteristics) " +
            "AND (p.isAvailable = true) ")
    List<Product> searchAvailableProducts(String name, Long organizationId, Integer price, List<Long> keyWords, List<Long> characteristics);
}