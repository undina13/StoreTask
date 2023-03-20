package com.undina.mainserver.service;

import com.undina.mainserver.dto.NewUserDto;
import com.undina.mainserver.dto.UserDto;
import com.undina.mainserver.exception.ObjectNotFoundException;
import com.undina.mainserver.mapper.UserMapper;
import com.undina.mainserver.model.User;
import com.undina.mainserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserDto> getUsers(List<Long> ids, int from, int size) {
        if (ids.isEmpty()) {
            return userRepository.findAll(PageRequest.of(from / size, size))
                    .stream()
                    .map(UserMapper::toUserDto)
                    .collect(Collectors.toList());
        }
        return userRepository.findAllByIdIn(ids, PageRequest.of(from / size, size))
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto saveUser(NewUserDto newUserDto) {
        User user = UserMapper.toUser(newUserDto);
        user.setBalance(0);
        user.setFrozen(false);
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.delete(checkAndGetUser(userId));
    }

    public User checkAndGetUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ObjectNotFoundException("user with id = " + userId + " not found"));
    }

    @Transactional
    public UserDto frozeUser(Long userId) {
        User user = checkAndGetUser(userId);
        user.setFrozen(true);
        return UserMapper.toUserDto(userRepository.save(user));
    }

    public UserDto unfrozeUser(Long userId) {
        User user = checkAndGetUser(userId);
        user.setFrozen(false);
        return UserMapper.toUserDto(userRepository.save(user));
    }
}