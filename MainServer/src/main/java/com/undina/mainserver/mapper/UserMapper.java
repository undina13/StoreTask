package com.undina.mainserver.mapper;

import com.undina.mainserver.dto.NewUserDto;
import com.undina.mainserver.dto.UserDto;
import com.undina.mainserver.model.Role;
import com.undina.mainserver.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static UserDto toUserDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .balance(user.getBalance())
                .role(user.getRole().toString())
                .isFrozen(user.isFrozen())
                .build();
    }

    public static User toUser(NewUserDto newUserDto) {
        return User
                .builder()
                .username(newUserDto.getUsername())
                .email(newUserDto.getEmail())
                .password(newUserDto.getPassword())
                .role(Role.valueOf(newUserDto.getRole()))
                .build();
    }
}
