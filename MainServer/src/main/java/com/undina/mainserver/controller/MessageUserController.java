package com.undina.mainserver.controller;

import com.undina.mainserver.client.MessageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/messages")
@Slf4j
public class MessageUserController {
    private final MessageClient messageClient;

    @Autowired
    public MessageUserController(MessageClient messageClient) {
        this.messageClient = messageClient;
    }

    @GetMapping("/userId")
    public ResponseEntity<Object> getMessages(Long userId) {
        log.info("get messages {}", userId);
        return messageClient.getMessages(userId.toString());
    }
}
