package com.undina.mainserver.service;

import com.undina.mainserver.model.Characteristic;
import com.undina.mainserver.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CharacteristicService {
    private final CharacteristicRepository characteristicRepository;

    @Autowired
    public CharacteristicService(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    public Characteristic addCharacteristic(String name, String description) {
        Characteristic characteristic = Characteristic.builder()
                .name(name)
                .description(description)
                .build();
        return characteristicRepository.save(characteristic);
    }
}


