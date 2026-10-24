# Usage Event Processor

A Spring Boot REST API that manages customer balances and processes usage events.

## Technologies Used

- Java 25
- Spring Boot
- Spring Data JPA
- MySQL 8.4
- Flyway database migrations
- Maven
- Swagger / OpenAPI
- Docker Compose

## Features

- Create customers with an initial balance
- Retrieve a customer's current balance
- Process usage events against customer balances
- Reject usage that exceeds the available balance
- Store customer balance data in MySQL

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/customers` | Create a customer |
| GET | `/customers/{customerId}/balance` | Retrieve a customer's balance |
| POST | `/usage` | Submit a usage event |

## Running Locally

1. Start Docker Desktop.
2. Configure the required database environment variables in a local `.env` file.
3. Start MySQL using Docker Compose.
4. Run the Spring Boot application from IntelliJ.

The application runs on `http://localhost:8080` by default.

## API Documentation

When the application is running, open Swagger UI:

`http://localhost:8080/swagger-ui/index.html`

## Configuration and Security

Database credentials are supplied through environment variables. Do not commit `.env` files or database passwords to GitHub.
