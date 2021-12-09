package com.cloud.setup.service.controllers;

import com.cloud.setup.service.dto.CreateUserDto;
import com.cloud.setup.service.entities.User;
import com.cloud.setup.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("/user")
public class UserController {
    private final UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Iterable<User> getAllUsers() {
        return this.userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return this.userService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @PostMapping("/user")
    public User createUser(@RequestBody CreateUserDto createUserDto) {
        User user = new User();
        user.setUserName(createUserDto.name);
        user.setDescription(createUserDto.description);
        return this.userService.save(user);
    }
}
