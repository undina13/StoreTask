package com.undina.mainserver.service;

import com.undina.mainserver.model.KeyWord;
import com.undina.mainserver.repository.KeyWordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class KeyWordService {
    private final KeyWordRepository keyWordRepository;

    public KeyWordService(KeyWordRepository keyWordRepository) {
        this.keyWordRepository = keyWordRepository;
    }

    public KeyWord addKeyWord(String name) {
        KeyWord keyWord = KeyWord.builder()
                .word(name)
                .build();
        return keyWordRepository.save(keyWord);
    }
}


