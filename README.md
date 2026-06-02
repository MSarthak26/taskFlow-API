# TaskFlow API

A secure Task and Project Management REST API built using Spring Boot.

## Features

- User Registration and Login
- JWT Authentication
- Role-Based Authorization (ADMIN / USER)
- Project Management APIs
- Task Management APIs
- DTO-based API Responses
- MapStruct for Object Mapping
- Request Validation
- Global Exception Handling
- Swagger/OpenAPI Documentation
- PostgreSQL Database Integration

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA
- PostgreSQL
- MapStruct
- Lombok
- Maven
- Swagger/OpenAPI

---

## API Documentation

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

Use the `/auth/login` endpoint to obtain a JWT token and authorize requests using the Swagger Authorize button.

---

## Authentication

The API uses JWT-based authentication.

### Register

```http
POST /auth/register
```

### Login

```http
POST /auth/login
```

Successful login returns a JWT token that must be included in the Authorization header:

```text
Authorization: Bearer <token>
```

---

## Roles

### ADMIN

- Create Projects
- Update Projects
- Delete Projects
- Access Task APIs

### USER

- View Projects
- Access Task APIs

---

## Running the Application

### Prerequisites

- Java 21
- PostgreSQL
- Maven

### Environment Variables

Configure the following environment variables:

```text
DB_URL
DB_Username
DB_Password
JWT_SECRET
```

Example:

```text
DB_URL=jdbc:postgresql://localhost:5432/taskmanager
DB_Username=postgres
DB_Password=your_password
JWT_SECRET=your_secret_key
```

### Run

```bash
mvn spring-boot:run
```

---

## Project Structure

```text
src
├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
├── service
└── security
```

---

## Future Enhancements

- Pagination
- Docker Support
- Refresh Tokens
- Advanced Authorization Rules

---

## Author

Built by Sarthak Mohaniraj as a backend portfolio project using Spring Boot.
