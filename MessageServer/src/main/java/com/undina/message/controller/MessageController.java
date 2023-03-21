package com.undina.message.controller;

import com.undina.message.dto.MessageDto;
import com.undina.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/messages")
@Slf4j
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public MessageDto createHit(@RequestBody MessageDto messageDto) {
        log.info("create message {}", messageDto);
        return messageService.createMessage(messageDto);
    }

    @GetMapping("/{userId}")
    public List<MessageDto> getMessages(@RequestParam String userId) {
        log.info("getMessages {}", userId);
        return messageService.getMessages(userId);
    }
}
