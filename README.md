# ShipCore 🚀
### Scalable Logistics Backend System

ShipCore is a backend system designed for managing courier and logistics operations, built using Spring Boot and MySQL. It provides REST APIs for handling shipment lifecycle, tracking updates, and user management with a scalable architecture.

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- MySQL
- JPA / Hibernate
- Maven
- Postman

---

## 📌 Features

- 🔐 User Management & Authentication
- 📦 Shipment (Courier) Creation & Handling
- 📍 Parcel Tracking System
- 🔄 Status Updates (In Transit, Delivered, etc.)
- 🧱 Layered Architecture (Controller–Service–Repository)
- ✅ Input Validation & Error Handling

---

## 🏗️ Architecture

The application follows a clean layered architecture:

Controller → Service → Repository → Database

---

## 📡 API Overview

- `POST /auth/register` → Register user  
- `POST /auth/login` → Login user  

- `POST /shipments` → Create shipment  
- `GET /shipments/{id}` → Get shipment details  
- `GET /shipments` → List shipments  

- `PUT /shipments/{id}/status` → Update shipment status  

---

## 🚀 Getting Started

### Clone the repository
```bash
git clone https://github.com/iamdeepakjain/shipcore-backend.git
cd shipcore-backend
