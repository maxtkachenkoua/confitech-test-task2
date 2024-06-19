package de.confitech.microservice.service;


import de.confitech.microservice.db.model.User;
import de.confitech.microservice.db.repo.UserRepository;
import de.confitech.microservice.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@ComponentScan(basePackages = "de.confitech.microservice")
public class UserServiceTest {

    private final ModelMapper modelMapper = new ModelMapper();
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, modelMapper);
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User(1L, "testuser", "password", "USER");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUserByUsername("testuser");

        assertEquals("testuser", userDto.getUsername());
        assertEquals("USER", userDto.getRole());
    }

    @Test
    public void testGetUserByUsernameNotFound() {
        when(userRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.getUserByUsername("nonexistent"));
    }
}