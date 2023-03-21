package com.undina.message.service;

import com.undina.message.dto.MessageDto;
import com.undina.message.mapper.MessageMapper;
import com.undina.message.model.Message;
import com.undina.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageDto createMessage(MessageDto messageDto) {
        Message message = MessageMapper.toMessage(messageDto);
        return MessageMapper.toMessageDto(messageRepository.save(message));
    }

    public List<MessageDto> getMessages(String userId) {
        return messageRepository.findAllByUser(userId)
                .stream()
                .map(MessageMapper::toMessageDto)
                .collect(Collectors.toList());
    }
}
