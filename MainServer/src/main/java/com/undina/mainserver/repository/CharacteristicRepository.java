package com.undina.mainserver.repository;

import com.undina.mainserver.model.Characteristic;
import com.undina.mainserver.model.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
    List<Characteristic> findAllByIdIn(List<Long>ids);
}
