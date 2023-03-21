package com.undina.message.mapper;

import com.undina.message.dto.MessageDto;
import com.undina.message.model.Message;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageMapper {
    public static MessageDto toMessageDto(Message message){
        return MessageDto.builder()
                .id(message.getId())
                .user(message.getUser())
                .admin(message.getAdmin())
                .text(message.getText())
                .build();
    }

    public static Message toMessage(MessageDto messageDto){
        return Message.builder()
                .user(messageDto.getUser())
                .admin(messageDto.getAdmin())
                .text(messageDto.getText())
                .build();
    }
}
