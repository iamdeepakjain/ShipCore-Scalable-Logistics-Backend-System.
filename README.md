📦 ShipCore — Logistics Backend with Tracking, Analytics & AI Risk Prediction
🚀 Overview

ShipCore is a scalable backend system designed for courier and logistics management.
It supports shipment lifecycle tracking, analytics, and rule-based AI prediction for delay risk estimation.

🧠 Key Features
User & Role-based system (Admin, User, Partner, Hub)
Shipment creation and lifecycle tracking
Real-time status updates (CREATED → IN_TRANSIT → DELIVERED)
Tracking history with event logs
Analytics endpoint (time, activity, movement analysis)
AI-based delay risk prediction (rule-based intelligence)

⚙️ Tech Stack
java
Spring Boot
Hibernate
MySQL
REST APIs
Postman

🏗 Architecture
Controller → Service → Repository → Database
                     ↓
            AI Risk Prediction Layer

📡 API Endpoints
Method	Endpoint	Description
POST	/packages	Create shipment
GET	/packages	Get shipments
PUT	/packages/{id}/status	Update status
GET	/packages/{id}/history	Tracking history
GET	/packages/{id}/analytics	Analytics data
GET	/packages/{id}/ai-risk	AI risk prediction

🤖 AI Feature

A rule-based intelligence system analyzes:

Tracking frequency
Time since creation
Status progression

It predicts shipment delay risk as:

LOW
MEDIUM
HIGH

📸 Screenshots
<img width="960" height="510" alt="2026-05-02 (1)" src="https://github.com/user-attachments/assets/c86d12bc-964f-4f84-89a0-1cc3cb8df6d5" />

👨‍💻 Author
Deepak Kumar Jain
