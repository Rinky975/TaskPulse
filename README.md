# TaskPulse – Smart Task Management & Follow-up System

## Overview
TaskPulse is a Spring Boot-based application that helps organizations manage employee tasks and automatically handle follow-ups for overdue tasks.  
It reduces manual tracking by sending automated email reminders and escalating pending tasks to managers.

## Features
- Employee task creation and updates
- Automatic overdue task detection
- Email reminder system for pending tasks
- Scheduler-based background processing
- Escalation to manager if task is not completed
- Task status tracking (Pending / Completed / Overdue)

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Spring Scheduler
- Java Mail Sender

## How to Run
1. Clone the repository:
   git clone https://github.com/your-username/TaskPulse.git

2. Open the project in VS Code or IntelliJ

3. Configure MySQL in application.properties:
   spring.datasource.url=jdbc:mysql://localhost:3306/taskpulse
   spring.datasource.username=root
   spring.datasource.password=your_password

4. Run the application:
   mvn spring-boot:run

## Author
Rinky Yadav
