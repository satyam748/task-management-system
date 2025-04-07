### Task Management System
# ✅ Spring Boot & Low-Level Design Roadmap Checklist

## 🛠️ Phase 1: Spring Boot Fundamentals (CRUD Project)
- [ ] Set up Spring Boot project with Spring Initializr
- [ ] Create Model (Entity) classes like `TaskEntity`, `UserEntity`
- [ ] Create JPA repositories
- [ ] Implement basic CRUD operations
- [ ] Implement soft delete functionality
- [ ] Add filtering and sorting using JPA Specification
- [ ] Implement DTOs to avoid circular dependencies in response

## 🔐 Phase 2: Authentication & Authorization
- [ ] Implement JWT-based login system
- [ ] Create `AuthController` with `/login` and `/register` endpoints
- [ ] Build `JwtFilter` to intercept and validate token in requests
- [ ] Secure all APIs using `SecurityFilterChain` & `AuthenticationManager`
- [ ] Implement Role-Based Access Control (RBAC)
- [ ] Restrict `TaskEntity` fetching to the logged-in user

## 💡 Phase 3: Low-Level Design (LLD) Concepts
- [ ] Understand Class Diagrams, Object Diagrams
- [ ] Practice writing Design Docs for features
- [ ] Implement common LLD patterns:
    - [ ] Singleton
    - [ ] Factory
    - [ ] Strategy
    - [ ] Observer
- [ ] Apply SOLID principles in the project
- [ ] Design and build reusable service layers

## 🧠 Phase 4: Advanced Spring Concepts
- [ ] Exception Handling using `@ControllerAdvice`
- [ ] Global response wrapper (consistent API structure)
- [ ] Pagination & Sorting
- [ ] Logging with SLF4J / Logback
- [ ] Validation with `@Valid` and `@NotNull`, etc.
- [ ] Swagger API documentation

## 🚀 Bonus Learning Goals (To Become Senior)
- [ ] Build a microservice version of the app
- [ ] Containerize using Docker
- [ ] CI/CD basics with GitHub Actions or Jenkins
- [ ] Database versioning with Flyway or Liquibase
- [ ] Unit testing with JUnit & Mockito
- [ ] Integration testing
- [ ] System design fundamentals (CAP theorem, DB sharding, etc.)
