package de.confitech.microservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String role;
}
