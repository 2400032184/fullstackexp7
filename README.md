# Course Management REST API

A Spring Boot REST API for managing university courses with complete CRUD operations using ResponseEntity.

## Features

- Create, Read, Update, Delete courses
- Search courses by title
- Proper HTTP status codes (OK, CREATED, NOT_FOUND, BAD_REQUEST)
- Structured JSON responses
- MySQL database integration

## Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Postman (for testing)

## Setup Instructions

### 1. Database Setup

Create a MySQL database:
```sql
CREATE DATABASE university_db;
```

### 2. Configure Database Connection

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/university_db
spring.datasource.username=root
spring.datasource.password=Srij@207
```

### 3. Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### 1. Create Course
**POST** `/courses`

Request Body:
```json
{
  "title": "Java Programming",
  "duration": 60,
  "fee": 5000.0
}
```

Response (201 CREATED):
```json
{
  "message": "Course created successfully",
  "status": "CREATED",
  "data": {
    "courseId": 1,
    "title": "Java Programming",
    "duration": 60,
    "fee": 5000.0
  }
}
```

### 2. Get All Courses
**GET** `/courses`

Response (200 OK):
```json
{
  "message": "Courses retrieved successfully",
  "status": "OK",
  "data": [
    {
      "courseId": 1,
      "title": "Java Programming",
      "duration": 60,
      "fee": 5000.0
    }
  ]
}
```

### 3. Get Course by ID
**GET** `/courses/{courseId}`

Response (200 OK):
```json
{
  "message": "Course retrieved successfully",
  "status": "OK",
  "data": {
    "courseId": 1,
    "title": "Java Programming",
    "duration": 60,
    "fee": 5000.0
  }
}
```

Response (404 NOT_FOUND):
```json
{
  "message": "Course not found",
  "status": "NOT_FOUND"
}
```

### 4. Update Course
**PUT** `/courses/{courseId}`

Request Body:
```json
{
  "title": "Advanced Java",
  "duration": 90,
  "fee": 7000.0
}
```

Response (200 OK):
```json
{
  "message": "Course updated successfully",
  "status": "OK",
  "data": {
    "courseId": 1,
    "title": "Advanced Java",
    "duration": 90,
    "fee": 7000.0
  }
}
```

### 5. Delete Course
**DELETE** `/courses/{courseId}`

Response (200 OK):
```json
{
  "message": "Course deleted successfully",
  "status": "OK"
}
```

### 6. Search Courses by Title
**GET** `/courses/search/{title}`

Response (200 OK):
```json
{
  "message": "Courses found",
  "status": "OK",
  "data": [
    {
      "courseId": 1,
      "title": "Java Programming",
      "duration": 60,
      "fee": 5000.0
    }
  ]
}
```

Response (404 NOT_FOUND):
```json
{
  "message": "No courses found with title: Python",
  "status": "NOT_FOUND"
}
```

## Testing with Postman

### Test Cases

#### Valid Cases:
1. Create a course with valid data
2. Retrieve all courses
3. Retrieve a specific course by ID
4. Update a course with valid data
5. Search courses by title
6. Delete a course

#### Invalid Cases:
1. Create course with missing fields (BAD_REQUEST)
2. Get course with non-existent ID (NOT_FOUND)
3. Update course with non-existent ID (NOT_FOUND)
4. Delete course with non-existent ID (NOT_FOUND)
5. Search with non-existent title (NOT_FOUND)

## Project Structure

```
course-management/
├── src/main/java/com/university/
│   ├── CourseManagementApplication.java
│   ├── entity/
│   │   └── Course.java
│   ├── repository/
│   │   └── CourseRepository.java
│   ├── service/
│   │   └── CourseService.java
│   └── controller/
│       └── CourseController.java
├── src/main/resources/
│   └── application.properties
├── pom.xml
└── README.md
```

## Technologies Used

- Spring Boot 3.1.5
- Spring Data JPA
- MySQL 8.0
- Maven
- Java 17

## Author

University Course Management System
