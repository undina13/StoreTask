package com.undina.mainserver.controller;

import com.undina.mainserver.model.KeyWord;
import com.undina.mainserver.service.KeyWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin/keywords")
@Slf4j
public class KeyWordAdminController {
    private final KeyWordService keyWordService;

    @Autowired
    public KeyWordAdminController(KeyWordService keyWordService) {
        this.keyWordService = keyWordService;
    }

    @PostMapping("/{word}")
    public KeyWord createKeyWord(@PathVariable String word) {
        log.info("create keyWord {} ", word);
        return keyWordService.addKeyWord(word);
    }
}
