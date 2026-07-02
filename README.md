# 📰 Fake News Detection & Reporting System

A GUI-based **Fake News Detection & Reporting System** developed using **Java Swing** and **MySQL** as part of a **Database Management Systems (DBMS)** course project.

The system allows users to register, log in, report suspicious news articles, and enables fact checkers to review reported news. All information is stored and managed using a relational database with proper entity relationships.

---

## 📌 Features

### 👤 User Authentication
- User Registration (Sign Up)
- Secure Login
- Role-based access (User / Fact Checker / Admin)

### 📰 News Management
- Add News
- Store news with source information
- Maintain news records in MySQL database

### 🚩 News Reporting
- Users can report suspicious news
- Reports are stored with reasons
- Multiple users can report the same news

### ✅ Fact Checking
- Only reported news can be fact-checked
- System analyzes report count
- Automatically classifies news as:
  - **Real**
  - **Fake**
- Stores fact-check remarks and status

### 📊 Reports
- View all reported news
- View fake news records
- SQL JOIN operations for complete report information

### 💻 Modern GUI
- Professional Java Swing Interface
- Login Screen
- Registration Screen
- Dashboard
- User-friendly Forms
- Responsive Layout

---

# 🛠 Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- SQL
- Object-Oriented Programming (OOP)

---

# 📂 Project Structure

```
DB_Project.java
│
├── DBConnection
├── LoginFrame
├── RegisterFrame
├── Dashboard
├── Add User
├── Add News
├── Report News
├── Fact Check
├── View Reports
└── View Fake News
```

---

# 🗄 Database Tables

The project uses the following relational tables:

- Users
- News
- Reports
- Fact_Checks
- Sources

Relationships are implemented using **Primary Keys** and **Foreign Keys**.

---

## 🗄 Database Tables

### Users

Stores user information.

| Column   | Description         |
| -------  | ------------------- |
| user_id  | Unique User ID      |
| name     | User Name           |
| email    | User Email          |
| role     | user / fact_checker |
| password | user password       |

### Sources

Stores information about news sources.

| Column            | Description        |
| ----------------- | ------------------ |
| source_id         | Unique Source ID   |
| source_name       | Source Name        |
| credibility_score | Source Credibility |

### News

Stores news articles.

| Column    | Description      |
| --------- | ---------------- |
| news_id   | Unique News ID   |
| title     | News Title       |
| content   | News Content     |
| source_id | Source Reference |

### Reports

Stores user reports.

| Column    | Description      |
| --------- | ---------------- |
| report_id | Unique Report ID |
| user_id   | Reporter ID      |
| news_id   | Reported News ID |
| reason    | Report Reason    |

### Fact_Checks

Stores verification results.

| Column     | Description        |
| ---------- | ------------------ |
| check_id   | Unique Check ID    |
| news_id    | Checked News ID    |
| checker_id | Fact Checker ID    |
| status     | Real / Fake        |
| remarks    | Verification Notes |

---

# 🖥 GUI Screens

- Login Page
- Registration Page
- Dashboard
- Add User
- Add News
- Report News
- Fact Check
- View Reports
- View Fake News

---

# ⚙ Database Setup

## Step 1

Create a database

```sql
CREATE DATABASE fakenewsdb;
```

---

## Step 2

Import all SQL tables into MySQL.

Required tables:

- Users
- News
- Reports
- Fact_Checks
- Sources

---

## Step 3

Update database credentials inside `DBConnection`.

```java
DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/fakenewsdb",
    "root",
    "YOUR_PASSWORD"
);
```

---

## Step 4

Add MySQL JDBC Driver to your project.

Example:

```
mysql-connector-j-8.x.x.jar
```

---

## Step 5

Run

```
DB_Project.java
```

---


# 🚀 Getting Started

Follow these steps to run the project on your local machine.

## Step 1: Clone the Repository

```bash
git clone https://github.com/YourUsername/Fake-News-Detection-System.git
```

or download the project as a ZIP file and extract it.

---

## Step 2: Open the Project

Open the project in your preferred Java IDE:

- Eclipse
- IntelliJ IDEA
- NetBeans
- VS Code (with Java Extension Pack)

---

## Step 3: Create the Database

Open MySQL Workbench and create a new database.

```sql
CREATE DATABASE fakenewsdb;
```

---

## Step 4: Import the SQL File

Import the provided SQL file into the `fakenewsdb` database.

This will create all required tables:

- Users
- Sources
- News
- Reports
- Fact_Checks

---

## Step 5: Configure Database Connection

Open the `DBConnection` class and update your MySQL credentials.

```java
DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/fakenewsdb",
    "root",
    "YOUR_PASSWORD"
);
```

---

## Step 6: Add MySQL JDBC Driver

Download the MySQL Connector/J driver and add it to your project's libraries.

Example:

```
mysql-connector-j-8.x.x.jar
```

---

## Step 7: Run the Application

Run the main Java file:

```
DB_Project.java
```

---

## Step 8: Register a New User

When the application starts:

- Click **Sign Up**
- Create a new account
- Log in using your registered email and password

---

## Step 9: Start Using the System

You can now:

- Add News
- Report News
- Perform Fact Checks
- View Reports
- View Fake News

  
# 📖 How It Works

### User

- Registers a new account
- Logs into the system
- Reports suspicious news

### Fact Checker

- Views reported news
- Performs fact checking
- System stores result

### Admin

- Manages users
- Adds news
- Views reports
- Reviews fake news

---

# 📊 SQL Concepts Used

This project demonstrates several important DBMS concepts:

- CREATE TABLE
- INSERT
- UPDATE
- DELETE
- SELECT
- WHERE
- JOIN
- COUNT()
- Foreign Keys
- Primary Keys
- Relational Database Design
- Entity Relationship Model

---

# 📚 Object-Oriented Programming Concepts

- Classes
- Objects
- Interfaces
- Abstraction
- Encapsulation
- JDBC Connectivity
- Exception Handling

---

# 🎯 Future Improvements

- AI-based fake news detection
- News API integration
- Image verification
- User profile management
- Password encryption
- Admin analytics dashboard
- Search functionality
- Export reports to PDF
- Email notifications
- News categories

---

# 📷 Screenshots

You can add screenshots here.

Example:

```
screenshots/
    login.png
    dashboard.png
    add_news.png
    reports.png
```

---

# 👨‍💻 Author

**Bharat Kumar**

Database Management Systems (DBMS) Project

---

# 📄 License

This project was developed for educational purposes as part of a university Database Management Systems course.

Feel free to fork, modify, and learn from the project.

---

## ⭐ If you found this project useful, consider giving it a Star!
