# Uber Entity Service

A Java-based entity module for managing Uber-like ride bookings, passengers, drivers, and reviews. Built using Spring Boot, JPA (Hibernate), and MySQL.


---

## Microservices

This Uber project is split into multiple microservices. Below are the links to their repositories or APIs:

| Service          | Description                                                                 | Link                                                                      |
|------------------|-----------------------------------------------------------------------------|---------------------------------------------------------------------------|
| Entity Service   | Handles all entities like Booking, Passenger, Driver, Review, and Locations | [GitHub Repo](https://github.com/himansh025/Comman-Entity)                |
| Booking Service  | Manages ride bookings, availability, and scheduling                         | [GitHub Repo](https://github.com/himansh025/Booking-Service)              |
| Socket Service   | Handles the async request between the booking, location, driver, passenger  | [GitHub Repo](https://github.com/himansh025/Uber-SocketServer.git)        |
| Auth Service     | Handles Authentication & Authorization making the endpoints secure          | [GitHub Repo](https://github.com/himansh025/AuthService)        |
| Location Service | Manages nearby driver for the passenger for their location                  | [GitHub Repo](https://github.com/himansh025/Location-Service)     |
| Review Service   | Collects and manages ride reviews for passengers and drivers                | [GitHub Repo](https://github.com/himansh025/ReviewServices)        |


## Table of Contents

- [About](#about)
- [Features](#features)
- [Models](#models)
- [Relationships](#relationships)
- [Dependencies](#dependencies)
- [Setup](#setup)
- [License](#license)

---

## About

This module contains entity models for an Uber-like service, including:

- **BaseModel**: Common fields for all entities (ID, created date, updated date).
- **Booking**: Details about each ride booking, including driver, passenger, locations, and status.
- **Passenger & Driver**: User entities with personal details, ratings, and location tracking.
- **Car**: Driver’s car information (type, color, etc.).
- **Review & PassengerReview**: Ride feedback and ratings associated with bookings.
- **ExactLocation**: Precise coordinates for pickup and drop-off.

The service uses JPA annotations for mapping relationships between entities and supports lazy and eager loading as needed.

---

## Features

- One-to-one, one-to-many, and many-to-one relationships.
- Cascade operations to maintain referential integrity.
- Booking status tracking.
- Passenger and driver ratings.
- OTP and location tracking for rides.

---

## Models

### BaseModel
- `id`: Unique identifier.
- `created`: Timestamp for entity creation.
- `updated`: Timestamp for last update.

### Booking
- `bookingStatus`: Current status of the booking (ENUM).
- `startTime` / `endTime`: Ride timestamps.
- `totalDistance`: Distance traveled.
- `driver` / `passenger`: Relationships to Driver and Passenger.
- `startLocation` / `endLocation`: References to ExactLocation entity.

### Passenger
- `name`, `email`, `phoneNumber`, `password`
- `activeBooking`: Current booking of the passenger.
- `lastKnownLocation`, `home`: Location references.
- `rating`: Average rating (0-5).
- `bookings`: List of all past bookings.

### Driver
- `name`, `licenseNumber`, `phoneNumber`
- `car`: One-to-one mapping with Car entity.
- `driverApprovalStatus`: ENUM for driver verification status.
- `lastKnownLocation`, `home`
- `activeCity`: Current operational city.
- `rating`: Average rating (0-5)
- `isActive`: Current activity status
- `bookings`: List of all driver bookings

### Review & PassengerReview
- `booking`: One-to-one relationship with Booking
- `content` / `passengerReviewContent`
- `rating` / `passengerRating`

---

## Relationships

- **Passenger – Booking**: One-to-many
- **Driver – Booking**: One-to-many
- **Booking – Review**: One-to-one
- **Passenger – ExactLocation**: One-to-one (home & last known location)
- **Driver – ExactLocation**: One-to-one (home & last known location)
- **Driver – Car**: One-to-one

---


Setup

Clone the repository:

git clone <repository-url>


Configure MySQL database in application.properties.

Run the application using Spring Boot:

./gradlew bootRun


Entities will be automatically created in the database via JPA/Hibernate.
```gradle
dependencies {
    implementation 'jakarta.persistence:jakarta.persistence-api'
    runtimeOnly 'com.mysql:mysql-connector-j'
    implementation 'org.springframework.boot:spring-boot-starter'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa:3.3.3'
    implementation 'com.fasterxml.jackson.core: jackson-annotations:2.17.2'
    compileOnly 'org.projectlombok:lombok:1.18.32'
    annotationProcessor 'org.projectlombok:lombok:1.18.32'
    testImplementation 'org.springframework.boot:spring-boot-starter-test:3.3.3'
    implementation 'org.springframework.boot:spring-boot-starter-validation:4.0.0-M1'
}
