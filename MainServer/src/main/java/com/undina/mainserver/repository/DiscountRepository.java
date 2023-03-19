package com.undina.mainserver.repository;

import com.undina.mainserver.model.Discount;
import com.undina.mainserver.model.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findAllByIdIn(List<Long>ids);
}
