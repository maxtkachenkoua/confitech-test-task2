package de.confitech.microservice.rest;

import de.confitech.microservice.dto.UserDto;
import de.confitech.microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserDto getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }
}
