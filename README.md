Car Rental System - Project Description
Overview
The Car Rental System is a Java-based GUI application designed to manage car rentals for both administrators and customers. It provides functionalities for user management, car inventory, rental bookings, and transaction tracking with a MySQL database backend.

Technologies Used
Category	Technology/Library
Programming Language	Java (JDK 17+)
GUI Framework	Java Swing
Database	MySQL
Database Connectivity	JDBC (Java Database Connectivity)
Build Tool	Apache Maven (or built-in NetBeans)
Version Control	Git (Optional)
IDE	NetBeans / IntelliJ IDEA / Eclipse
Key Features
1. User Authentication & Roles
Admin:

Manage users (CRUD operations)

Add/edit/delete cars

View all rentals

Customer:

Browse available cars

Book rentals

View rental history

2. Car Management
Add new cars with details (make, model, year, color, license plate, daily rate)

Mark cars as available/unavailable

Filter cars by availability

3. Rental Management
Book a car with rental & return dates

Calculate total cost based on daily rate

Mark rentals as returned

View active & past rentals

4. Database Integration
MySQL for data persistence

JDBC for secure database connectivity

Prepared statements to prevent SQL injection

5. UI Features
Responsive Swing GUI with proper layouts (GridBagLayout, BorderLayout)

Data tables (JTable) for displaying cars, users, and rentals

Input validation for forms

Role-based dashboards (Admin vs. User)

Functional Modules
1. Login & Registration
Secure login with username/password

New user registration

Password hashing (not plaintext storage)

2. Admin Dashboard
User Management:

View all users

Add/edit/delete users

Toggle admin privileges

Car Management:

Add/edit/delete cars

Update availability status

Rental Management:

View all rentals

Filter by status (active/returned)

3. User Dashboard
Available Cars:

Browse cars

Rent a car (select dates)

My Rentals:

View past & current rentals

Return cars

Database Schema
Tables
users

user_id (PK), username, password, full_name, email, phone, is_admin

cars

car_id (PK), make, model, year, color, license_plate, daily_rate, available

rentals

rental_id (PK), user_id (FK), car_id (FK), rental_date, return_date, total_cost, is_returned

How It Works
1. Login Flow
User enters credentials → System checks against database → Redirects to appropriate dashboard.

2. Admin Workflow
Manage Users: Add/edit/delete users.

Manage Cars: Update car inventory.

View Rentals: Track all bookings.

3. Customer Workflow
Browse Cars: Filter available cars.

Rent a Car: Select dates → Confirm booking.

View Rentals: Check past/active rentals.
🎯 Core Features
User registration and login (Admin & Customer roles)

View available cars with filters

Car booking and cancellation

Rental history and billing system

Admin dashboard to manage cars and users

🔐 Error Handling & Robustness
Handles invalid inputs (e.g., wrong date, unavailable cars)

Graceful failure messages for system errors

Try-catch blocks for file and database operations

🔗 Integration of Components
Smooth integration between modules like user authentication, car management, and booking interface

⚙️ Event Handling & Processing
Efficient event listeners for UI interactions

Real-time updates for car availability status

🛡️ Data Validation
Client-side and server-side checks for form inputs

Ensures valid dates, vehicle IDs, and payment entries

🧠 Code Quality & Innovation
Clean, modular Java code with layered architecture (GUI, Logic, DB)

Added features like car search filter, rental duration calculator

📄 Project Documentation
Setup instructions

Feature descriptions

Usage guide and screenshots
