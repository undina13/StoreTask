package com.undina.mainserver.controller;

import com.undina.mainserver.model.Characteristic;
import com.undina.mainserver.service.CharacteristicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin/characteristics")
@Slf4j
public class CharacteristicAdminController {
    private final CharacteristicService characteristicService;

    public CharacteristicAdminController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @PostMapping()
    public Characteristic createCharacteristic(@RequestBody String name,
                                               @RequestBody String description) {
        log.info("create characteristic {} ", name);
        return characteristicService.addCharacteristic(name, description);
    }

}
