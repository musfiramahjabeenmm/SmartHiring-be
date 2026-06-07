# Smart Hiring Portal — Backend

A RESTful backend for a role-based hiring platform built with Spring Boot, Spring Security, JWT, and PostgreSQL (Supabase).

---

## Tech Stack

- Java 21
- Spring Boot 4.0.6
- Spring Security + JWT
- Spring Data JPA + Hibernate
- PostgreSQL (Supabase)
- Lombok

---

## Features

- Role-based authentication (RECRUITER / CANDIDATE)
- JWT stateless session management
- BCrypt password encryption
- Job posting and management
- Job application tracking
- Application status updates (APPLIED → SHORTLISTED → SELECTED / REJECTED)
- Recruiter and Candidate dashboards

---

## Database Schema

### users
| Column | Type | Description |
|---|---|---|
| id | BIGSERIAL | Primary key |
| name | VARCHAR(100) | User full name |
| email | VARCHAR(100) | Unique email |
| password | VARCHAR(255) | BCrypt encrypted |
| role | VARCHAR(20) | RECRUITER or CANDIDATE |
| created_at | TIMESTAMP | Auto generated |

### jobs
| Column | Type | Description |
|---|---|---|
| id | BIGSERIAL | Primary key |
| title | VARCHAR(100) | Job title |
| description | TEXT | Job description |
| location | VARCHAR(100) | Job location |
| salary_range | VARCHAR(50) | Salary range |
| opened_date | DATE | Opening date |
| closing_date | DATE | Closing date |
| recruiter_id | BIGINT | Foreign key to users |
| created_at | TIMESTAMP | Auto generated |

### job_applications
| Column | Type | Description |
|---|---|---|
| id | BIGSERIAL | Primary key |
| candidate_id | BIGINT | Foreign key to users |
| job_id | BIGINT | Foreign key to jobs |
| resume_link | VARCHAR(255) | Resume URL |
| status | VARCHAR(20) | APPLIED / SHORTLISTED / SELECTED / REJECTED |
| applied_at | TIMESTAMP | Auto generated |

---

## API Endpoints

### Auth — /api/auth
| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | /api/auth/signup | Register new user | Public |
| POST | /api/auth/login | Login and get JWT | Public |

### Jobs — /api/jobs
| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | /api/jobs/post | Post a new job | Authenticated |
| GET | /api/jobs/all | Get all jobs | Public |
| GET | /api/jobs/my | Get recruiter's posted jobs | Authenticated |
| DELETE | /api/jobs/{id} | Delete a job | Authenticated Owner |

### Applications — /api/applications
| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | /api/applications/apply/{jobId} | Apply to a job | Authenticated |
| GET | /api/applications/my | Get my applications | Authenticated |
| GET | /api/applications/job/{jobId} | Get applicants for a job | Authenticated |
| PUT | /api/applications/{id}/status | Update application status | Authenticated Owner |
| DELETE | /api/applications/{id}/withdraw | Withdraw application | Authenticated |
| GET | /api/applications/dashboard/candidate | Candidate dashboard stats | Authenticated |

---

## Business Rules

- One candidate can apply to one job only once
- Only the recruiter who posted the job can update application status
- Passwords are encrypted using BCrypt
- All routes except /api/auth/** and GET /api/jobs/all require a valid JWT token

---

## Setup

### Prerequisites

- Java 21
- Maven
- PostgreSQL or Supabase account

### Steps

**1. Clone the repo**

```
git clone https://github.com/musfiramahjabeenmm/SmartHiring-be.git
cd SmartHiring
```

**2. Configure application.properties**

Open `src/main/resources/application.properties` and update:

```
spring.datasource.url=your_supabase_url
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.use_get_generated_keys=true
spring.datasource.hikari.data-source-properties.prepareThreshold=0
jwt.secret=your_jwt_secret
server.port=8081
```

**3. Run the app**

```
mvn spring-boot:run
```

Server runs on http://localhost:8081

---

## Project Structure

```
src/main/java/com/hire/smart/hiring/
├── controller/
│   ├── AuthController.java
│   ├── JobController.java
│   └── ApplicationController.java
├── service/
│   ├── AuthService.java
│   ├── JobService.java
│   └── ApplicationService.java
├── repository/
│   ├── UserRepository.java
│   ├── JobRepository.java
│   └── JobApplicationRepository.java
├── model/
│   ├── User.java
│   ├── Job.java
│   └── JobApplication.java
├── dto/
│   ├── LoginRequest.java
│   ├── SignupRequest.java
│   ├── JobRequest.java
│   ├── ApplicationRequest.java
│   └── StatusUpdateRequest.java
├── security/
│   ├── JwtFilter.java
│   ├── JwtUtil.java
│   └── SecurityConfig.java
└── SmartHiringApplication.java
```

---

## Frontend

Frontend repo: https://github.com/musfiramahjabeenmm/SmartHiring-fe.git
