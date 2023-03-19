package com.undina.mainserver.repository;

import com.undina.mainserver.model.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyWordRepository extends JpaRepository<KeyWord, Long> {
    List<KeyWord>findAllByIdIn(List<Long>ids);
}
