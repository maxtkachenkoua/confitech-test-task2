# Spring Boot Microservice with Eureka, Spring Security, Flyway, and H2

This project is a simple microservice built with Spring Boot, registered with an Eureka server, secured with Spring Security, and configured with Flyway for database migrations. It includes features for user authentication, role-based authorization, and CRUD operations on posts.

## Features

- **Microservice Architecture**: Built with Spring Boot and registered with an Eureka server.
- **Authentication and Authorization**: Secured with Spring Security, with user roles managed in the database.
- **Database Migrations**: Using Flyway for initializing and managing the database schema.
- **DTO Mapping**: Using ModelMapper for mapping entities to DTOs.
- **Unit Tests**: Includes unit tests for the service layer using Mockito and Spring Boot Test.
- **In-Memory Database**: Tests are run against an in-memory H2 database.
