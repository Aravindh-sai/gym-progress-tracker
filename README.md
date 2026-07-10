# Gym Progress Tracker

## Overview

Gym Progress Tracker is a full-stack web application designed to help users build and manage structured workout routines.

Users can securely register and log in, create workout days, add exercises with target sets, and save their routines for future workout tracking.

The long-term vision of the project is to provide a complete fitness companion where users can log workouts, monitor strength progression, track body weight, and visualize their fitness journey through analytics.

---

## Project Goals

The goal of this project is to build a production-inspired full-stack fitness tracking application while following modern software engineering practices.

The project focuses on writing clean, maintainable code, designing intuitive user interfaces, implementing secure authentication, and documenting the development process professionally.

## Features

### Authentication

- Secure user registration
- JWT-based user authentication
- Protected application routes

### Routine Management

- Create workout days
- Add multiple exercises to each day
- Configure target sets for every exercise
- Create rest days to complete a workout cycle
- Save routines to the database
- Automatically determine the current workout day based on the routine start date
- Display today's workout and target sets on the Home dashboard

### User Interface

- Modern dark theme
- Responsive authentication pages
- Responsive routine setup page
- Clean and minimal user experience

---

## Tech Stack

### Frontend

- React
- React Router
- CSS

### Backend

- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA

### Database

- MySQL

### Tools

- Git
- GitHub
- VS Code

---

## Project Architecture

```text
React Frontend
        │
 REST API (JSON)
        │
Spring Boot Backend
        │
Spring Security + JWT
        │
Spring Data JPA
        │
MySQL Database
```

---

## Database Design

Current Entity Relationship Diagram

```text
User
 │
 └── RoutineDay
        │
        └── Exercise
```

Future versions will introduce entities such as Workout Session, Workout Set, Body Weight Log, and Personal Records.

---

## API Endpoints

| Method | Endpoint              | Description                      |
|--------|-----------------------|----------------------------------|
| POST   | /signup               | Register a new user              |
| POST   | /login                | Authenticate a user              |
| POST   | /routine-days         | Save a workout day               |
| GET    | /routine-days/current | Retrieve the current workout day |

---

## Project Structure

```text
gym-progress-tracker/

├── frontend/
│   ├── src/
│   ├── public/
│   └── package.json
│
├── backend/
│   ├── src/
│   ├── pom.xml
│   └── target/
│
├── README.md
├── CHANGELOG.md
└── .gitignore
```

---

## Getting Started

## Prerequisites

Before running the project, ensure you have the following installed:

- Java 23
- Node.js
- MySQL 8+
- Git
- Maven (or use the included Maven Wrapper)

### Clone the repository

```bash
git clone <https://github.com/Aravindh-sai/gym-progress-tracker.git>
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

---

### Backend

```bash
cd backend
```
If using VS Code:

- Open the backend project.
- Open `GymtrackerApplication.java`.
- Click **Run**.

Or use Maven:

```bash
./mvnw spring-boot:run
```

---

### Database

Create a MySQL database.

```sql
CREATE DATABASE gymtracker;
```

Copy

```
application-example.properties
```

to

```
application.properties
```

Update the database credentials.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gymtracker
spring.datasource.username=your_username
spring.datasource.password=your_password
```


### Application URLs

Frontend

```
http://localhost:5173
```

Backend

```
http://localhost:8080
```

---

## Current Progress

Current Version

**v0.1.0**

Completed

- Authentication
- JWT Security
- Routine Setup
- Exercise Management
- Dark Theme
- Backend Integration

---

## Roadmap

### Implemented Features

- [x] User Authentication
- [x] JWT Authorization
- [x] Routine Setup
- [x] Exercise Management
- [x] Responsive Authentication UI

### Planned

- [ ] Workout Logging
- [ ] Progress Dashboard
- [ ] Personal Records
- [ ] Body Weight Tracking
- [ ] User Profile
- [ ] Workout Analytics
- [ ] Mobile Optimization

---

## Screenshots

Screenshots will be added after the completion of the MVP.

---

## Author

**Aravindh Sai**

B.Tech Student (Artificial Intelligence & Machine Learning)

Passionate about Full-Stack Development, Artificial Intelligence, and Software Engineering.