package com.undina.mainserver.repository;

import com.undina.mainserver.model.ProductMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductMarkRepository extends JpaRepository<ProductMark, Long> {
    List<ProductMark> findAllByIdIn(List<Long> ids);
}
