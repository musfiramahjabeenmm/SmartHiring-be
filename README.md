# Smart Hiring Portal — Backend

A RESTful backend for a role-based hiring platform built with Spring Boot, Spring Security, JWT, and PostgreSQL (Supabase).

## Tech Stack
- Java 21
- Spring Boot 4.0.6
- Spring Security + JWT
- Spring Data JPA + Hibernate
- PostgreSQL (Supabase)
- Lombok

## Features
- Role-based authentication (RECRUITER / CANDIDATE)
- JWT stateless session management
- BCrypt password encryption
- Job posting and management
- Job application tracking
- Application status updates (APPLIED → SHORTLISTED → SELECTED / REJECTED)
- Recruiter and Candidate dashboards

## API Endpoints

### Auth
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/auth/signup | Register new user |
| POST | /api/auth/login | Login and get JWT |

### Jobs
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/jobs/post | Post a new job |
| GET | /api/jobs/all | Get all jobs |
| GET | /api/jobs/my | Get recruiter's jobs |
| DELETE | /api/jobs/{id} | Delete a job |

### Applications
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/applications/apply/{jobId} | Apply to a job |
| GET | /api/applications/my | Get my applications |
| GET | /api/applications/job/{jobId} | Get applicants for a job |
| PUT | /api/applications/{id}/status | Update application status |
| DELETE | /api/applications/{id}/withdraw | Withdraw application |
| GET | /api/applications/dashboard/candidate | Candidate dashboard stats |

## Setup

### Prerequisites
- Java 21
- Maven
- PostgreSQL / Supabase account

**1. Clone the repo**

git clone https://github.com/musfiramahjabeenmm/SmartHiring.git
cd SmartHiring

**2. Configure application.properties**

spring.datasource.url=your_supabase_url
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your_jwt_secret

**3. Run the app**

mvn spring-boot:run

Server runs on http://localhost:8081

## Frontend
Frontend repo: https://github.com/musfiramahjabeenmm/SmartHiring-fe.git
