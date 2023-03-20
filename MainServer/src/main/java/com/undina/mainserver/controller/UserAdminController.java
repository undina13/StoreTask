package com.undina.mainserver.controller;

import com.undina.mainserver.dto.NewUserDto;
import com.undina.mainserver.dto.UserDto;
import com.undina.mainserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "admin/users")
@Slf4j
public class UserAdminController {
    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getUsers(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(defaultValue = "0", required = false) int from,
            @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("get users");
        return userService.getUsers(ids, from, size);
    }

    @PostMapping
    public UserDto saveUser(@RequestBody NewUserDto newUserDto) {
        log.info("save user");
        return userService.saveUser(newUserDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        log.info("delete user{}", userId);
        userService.deleteUser(userId);
    }

    @PutMapping("froze/{userId}")
    public UserDto frozeUser(@PathVariable Long userId) {
        log.info("froze user{}", userId);
       return userService.frozeUser(userId);
    }

    @PutMapping("unfroze/{userId}")
    public UserDto unfrozeUser(@PathVariable Long userId) {
        log.info("unfroze user{}", userId);
        return userService.unfrozeUser(userId);
    }
}
