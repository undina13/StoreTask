package com.undina.mainserver.service;

import com.undina.mainserver.dto.DiscountDto;
import com.undina.mainserver.dto.NewDiscountDto;
import com.undina.mainserver.model.Characteristic;
import com.undina.mainserver.repository.CharacteristicRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional(readOnly = true)
public class CharacteristicService {
    private final CharacteristicRepository characteristicRepository;

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


