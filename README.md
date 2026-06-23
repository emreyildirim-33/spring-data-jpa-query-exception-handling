# Spring Data JPA Query & Exception Handling API

A Java and Spring Boot REST API project focused on Spring Data JPA queries, sorting, filtering, validation, global exception handling, and layered backend architecture.

The project simulates a fruits and vegetables API to practice custom repository methods, CRUD operations, centralized error handling, and clean REST endpoint design.

## Overview

This project was developed as a hands-on backend training project with Spring Boot and Spring Data JPA.

The main goal was to practice building REST APIs with database-backed entities, repository query methods, service-layer abstraction, validation rules, and global exception handling.

The application includes two main domain models:

* `Fruit`
* `Vegetable`

Both models support CRUD operations, price-based sorting, name-based searching, validation checks, and centralized error responses.

## Tech Stack

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* REST API
* Validation
* Global Exception Handling
* SLF4J Logging
* Layered backend architecture

## Core Concepts Practiced

* Spring Boot REST API development
* Spring Data JPA repository methods
* Query method naming
* Sorting by price ascending and descending
* Name-based filtering/searching
* CRUD endpoint design
* Service layer abstraction
* Dependency Injection
* Entity validation
* Global exception handling
* Centralized error responses
* Server-side error logging with SLF4J

## Domain Models

### Fruit

The `Fruit` entity includes:

* `id`
* `name`
* `price`
* `fruitType`

`fruitType` is represented as an enum:

```text
SWEET
SOUR
```

### Vegetable

The `Vegetable` entity includes:

* `id`
* `name`
* `price`
* `isGrownOnTree`

## Features

* Fruit CRUD operations
* Vegetable CRUD operations
* Get fruits sorted by price ascending
* Get fruits sorted by price descending
* Search fruits by name
* Get vegetables sorted by price ascending
* Get vegetables sorted by price descending
* Search vegetables by name
* Validation for invalid IDs
* Validation for missing request data
* Global exception handling
* Centralized error response structure
* SLF4J-based server error logging
* Layered backend architecture using controller, service, repository, entity, and exception layers

## Main API Endpoints

### Fruit Endpoints

| Method | Endpoint                    | Description                               |
| ------ | --------------------------- | ----------------------------------------- |
| GET    | `/workintech/fruits`        | Get all fruits sorted by price ascending  |
| GET    | `/workintech/fruits/{id}`   | Get fruit by ID                           |
| GET    | `/workintech/fruits/desc`   | Get all fruits sorted by price descending |
| POST   | `/workintech/fruits`        | Create or update a fruit                  |
| POST   | `/workintech/fruits/{name}` | Search fruits by name                     |
| DELETE | `/workintech/fruits/{id}`   | Delete fruit by ID                        |

### Vegetable Endpoints

| Method | Endpoint                        | Description                                   |
| ------ | ------------------------------- | --------------------------------------------- |
| GET    | `/workintech/vegetables`        | Get all vegetables sorted by price ascending  |
| GET    | `/workintech/vegetables/{id}`   | Get vegetable by ID                           |
| GET    | `/workintech/vegetables/desc`   | Get all vegetables sorted by price descending |
| POST   | `/workintech/vegetables`        | Create or update a vegetable                  |
| POST   | `/workintech/vegetables/{name}` | Search vegetables by name                     |
| DELETE | `/workintech/vegetables/{id}`   | Delete vegetable by ID                        |

## Backend Architecture

The project follows a layered backend architecture:

```text
Controller Layer
↓
Service Layer
↓
Repository / DAO Layer
↓
Entity / Database Layer
↓
Exception Handling Layer
```

This structure separates request handling, business logic, database access, data modeling, and error handling.

## Exception Handling

The API includes validation and exception handling for cases such as:

* ID value smaller than zero
* Entity not found in the database
* Missing or invalid request data
* Unexpected server-side errors

Errors are handled through a centralized global exception handling structure instead of being managed directly inside controller classes.

## Project Structure

```text
src/
 └── main/
     ├── java/
     │   └── ...
     │       ├── controller/
     │       ├── services/
     │       ├── dao/
     │       ├── entity/
     │       ├── exceptions/
     │       └── validations/
     └── resources/
         └── application.properties
```

## What I Practiced

* Building REST APIs with Spring Boot
* Creating entity models with JPA annotations
* Writing Spring Data JPA repository query methods
* Implementing sorting and filtering logic
* Separating controller, service, and repository responsibilities
* Handling invalid input with validation logic
* Managing errors from a global exception handler
* Logging unexpected errors with SLF4J
* Designing cleaner and more maintainable backend APIs

## Getting Started

### Prerequisites

Make sure you have the following installed:

* Java 17+
* Maven
* PostgreSQL
* IntelliJ IDEA or another Java IDE

### Installation

Clone the repository:

```bash
git clone https://github.com/emreyildirim-33/spring-data-jpa-query-exception-handling.git
cd spring-data-jpa-query-exception-handling
```

Configure your database connection in `application.properties`.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.default_schema=fsweb
```

Run the project:

```bash
./mvnw spring-boot:run
```

The application runs locally at:

```text
http://localhost:8080
```

## API Testing

You can test the endpoints using:

* Postman
* IntelliJ HTTP Client
* Browser for simple GET requests

## Notes

This project was built as a backend training project focused on Spring Data JPA, custom query methods, sorting, filtering, validation, global exception handling, and layered Spring Boot architecture.

The main focus was not building a production fruits and vegetables platform, but practicing clean REST API design and backend error-handling patterns.

## Repository

GitHub: https://github.com/emreyildirim-33/spring-data-jpa-query-exception-handling
