package com.undina.mainserver.controller;

import com.undina.mainserver.client.MessageClient;
import com.undina.mainserver.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/messages")
@Slf4j
public class MessageAdminController {
    private final MessageClient messageClient;

    @Autowired
    public MessageAdminController(MessageClient messageClient) {
        this.messageClient = messageClient;
    }

    @PostMapping
    public void createMessage(@RequestBody MessageDto messageDto) {
        log.info("create message {}", messageDto);
        messageClient.createMessage(messageDto);
    }
}
