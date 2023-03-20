package com.undina.mainserver.repository;

import com.undina.mainserver.model.Purchase;
import com.undina.mainserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
List<Purchase> findAllByUser(User user);

}


