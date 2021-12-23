package com.cloud.setup.service.controllers;

import com.cloud.setup.service.dto.CreateUserDto;
import com.cloud.setup.service.entities.User;
import com.cloud.setup.service.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@Slf4j
@RestController("/user")
public class UserController {
    private final UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Get list of all created users",
            description = "Return all user Entities saved in the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Users list returned form database", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal error, can't return users list", content = @Content(schema = @Schema(implementation = HttpServerErrorException.InternalServerError.class))),
            }
    )
    @Cacheable("users")
    @GetMapping("/user")
    public Iterable<User> getAllUsers() {
        return this.userService.findAll();
    }

    @Operation(
            summary = "Get user by id",
            description = "Return user Entity with given id value",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User with specified id returned", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "404", description = "cant extract user with given id", content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))),
            }
    )
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return this.userService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Operation(
            summary = "Create user",
            description = "Save user with provided info in database",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User with specified id returned", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "500", description = "Internal error, user was not added to database", content = @Content(schema = @Schema(implementation = HttpServerErrorException.InternalServerError.class))),
            }
    )
    @CacheEvict(value = "users", allEntries = true)
    @PostMapping("/user")
    public User createUser(@RequestBody CreateUserDto createUserDto) {
        User user = new User();
        user.setUserName(createUserDto.name);
        user.setDescription(createUserDto.description);
        return this.userService.save(user);
    }
}
