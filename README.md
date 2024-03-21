# Classroom Reservation System Microservices

This project implements a microservices-based classroom reservation system using Spring Boot.

## Microservices

The system consists of four independent microservices:

1. **Classroom Service:**
   - Manages classrooms.
   - Provides CRUD (Create, Read, Update, Delete) operations on classrooms.

2. **Reservation Service:**
   - Handles classroom reservations.
   - Provides CRUD operations on reservations, ensuring no overlaps.

3. **User Service:**
   - Handles user authentication and authorization.
   - Allows user registration, login, and token issuance.
   - Implements role-based access control.

4. **Recommendation Service:**
   - Recommends optimal classrooms based on user needs.
   - Consumes data from Classroom and Reservation services.

## Technology Stack

- Spring Boot
- Spring Cloud
- Lombok
- JPA
- Oauth2

## Getting Started

1. Clone this repository.
2. Install required dependencies (refer to project-specific instructions).
3. Configure database connections in application properties files.
4. Configure security settings in application properties files.
5. Run each microservice using its Spring Boot application class.

## Contributing

We welcome contributions! Please refer to the contribution guidelines (if provided).
