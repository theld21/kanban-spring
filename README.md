# 📝 Kanban Spring Full-Stack Application

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Nuxt 3](https://img.shields.io/badge/Nuxt_3-00DC82?style=for-the-badge&logo=nuxt.js&logoColor=white)
![Vue.js](https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D)
![TailwindCSS](https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)

## 📌 Overview
This project is a modern, full-stack Kanban board application. It provides users with the ability to register, log in, manage boards, and track tasks efficiently in a Kanban-style interface. The system leverages a robust **Spring Boot 3** backend handling RESTful operations and JWT security, coupled with a responsive, lightning-fast **Nuxt 3 (Vue.js)** frontend.

## ✨ Key Features
- **Secure Authentication:** User registration and login flows protected by JWT (JSON Web Tokens) via Spring Security.
- **Kanban Board Management:** Create independent boards, and seamlessly transition tasks across different states.
- **Modern UI/UX:** A clean, responsive interface styled with Tailwind CSS and interactive components powered by Headless UI/Heroicons.
- **Global State Management:** Utilizing Pinia for highly performant frontend state handling.
- **Containerized Environment:** Fully dockerized stack (Frontend, Backend, and Database) for immediate execution and development consistency.

## 🛠 Tech Stack

### Frontend (Nuxt/Vue)
- **Framework:** Nuxt 3 (Vue 3, Composition API)
- **Styling:** Tailwind CSS
- **State Management:** Pinia
- **Icons & UI:** Heroicons (`@heroicons/vue`)

### Backend (Spring Boot)
- **Framework:** Spring Boot 3.2.5 (Java 17)
- **Security:** Spring Security & JWT (`jjwt`)
- **Data Access:** Spring Data JPA
- **Build Tool:** Maven

### Infrastructure & Database
- **Database:** MySQL 8.0.22
- **Containerization:** Docker & Docker Compose

---

## 🚀 Getting Started

Running the entire application stack is extremely straightforward thanks to Docker Compose. 

### Prerequisites
Make sure you have the following installed on your machine:
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- Make sure ports `3000` (Frontend), `8080` (assumed Backend), and `3306` (MySQL) are free, or adjust accordingly based on your `.env`/properties files.

### Starting the Application

Navigate to the root directory of the project in your terminal and execute:

```bash
docker-compose up --build
```
*(Optionally use `docker compose up --build` depending on your docker installation version).*

This command will simultaneously spin up:
1. The **MySQL database** container.
2. The **Spring Boot** backend application.
3. The **Nuxt 3** frontend application.

### Accessing the App
Once the containers are running and the initial build steps have completed:
- **Frontend / UI Client:** Access via your browser at `http://localhost:3000` (unless configured otherwise).
- **Backend API:** Generally accessible at `http://localhost:8080` (or as configured in your Spring properties).

---

<p align="center">
  <b>Happy coding! :)</b>
</p>
