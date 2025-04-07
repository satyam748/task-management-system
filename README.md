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

## 🧩 Phase 3 : Next Functional Features'
- [ ] Add pagination and search filters.
- [ ] Add task comments/notes (1:N relationship).
- [ ] Add due date notifications or email triggers (Scheduler + Email Service).
- [ ] Add priority, labels/tags (Enum & additional filtering).
- [ ] Add activity logs or history tracking (Audit trail).
- [ ] Implement file attachments for tasks (S3 / Local storage).


## 💡 Phase 4: Low-Level Design (LLD) Concepts
- [ ] Understand LLD vs HLD: Terminologies, responsibilities.
- [ ] SOLID Principles with examples from your project.
- [ ] Practice writing Design Docs for features
- [ ] Implement common LLD patterns:
    - [ ] Singleton
    - [ ] Factory
    - [ ] Strategy
    - [ ] Observer
- [ ] Apply SOLID principles in the project
- [ ] Service Layer Design
- [ ] Interface-driven Development
- [ ] Entity Relationships & Aggregates
- [ ] DTO vs Entity vs Model
- [ ] Builder Pattern for DTO creation
- [ ] Clean Architecture / Hexagonal Architecture
- [ ] Refactoring for separation of concerns
- [ ] Code Modularization and Packages

## 🧠 Phase 5: Advanced Spring Concepts
- [ ] Exception Handling using `@ControllerAdvice`
- [ ] Global response wrapper (consistent API structure)
- [ ] Pagination & Sorting
- [ ] Logging with SLF4J / Logback
- [ ] Validation with `@Valid` and `@NotNull`, etc.
- [ ] Swagger API documentation

## 📦 Phase 6: System Design Basics
- [ ] Design RESTful APIs – Best Practices.
- [ ] API Rate Limiting, Throttling.
- [ ] Logging and Monitoring (Actuator, ELK).
- [ ] Database Indexing, Joins, Query Optimization.
- [ ] Caching with Redis.
- [ ] Async Processing using Queues or Events.
- [ ] Introduction to Microservices.

## 🚀 Bonus Learning Goals (To Become Senior)
- [ ] Build a microservice version of the app
- [ ] Containerize using Docker
- [ ] CI/CD basics with GitHub Actions or Jenkins
- [ ] Database versioning with Flyway or Liquibase
- [ ] Unit testing with JUnit & Mockito
- [ ] Integration testing
- [ ] System design fundamentals (CAP theorem, DB sharding, etc.)