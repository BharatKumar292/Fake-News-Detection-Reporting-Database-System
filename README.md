# 📰 Fake News Detection & Reporting Database System

## 📌 Project Overview

The **Fake News Detection & Reporting Database System** is a DBMS academic project developed using **Java, JDBC, and MySQL**. The system provides a structured platform where users can report suspicious news articles and fact-checkers can verify whether the news is real or fake.

The project demonstrates the practical implementation of database concepts, SQL queries, JDBC connectivity, and Object-Oriented Programming (OOP) principles.

---

## 🎯 Objectives

* Allow users to report suspicious news articles.
* Store and manage news information in a relational database.
* Enable fact-checkers to verify reported news.
* Maintain records of reports and fact-checking results.
* Demonstrate the use of JDBC with MySQL.
* Apply OOP concepts such as Abstraction, Encapsulation, Inheritance, and Polymorphism.

---

## 🛠 Technologies Used

* Java
* JDBC
* MySQL
* SQL
* Object-Oriented Programming (OOP)

---

## 👥 System Users

### User

* Reports suspicious news.
* Views system information.

### Fact Checker

* Reviews reported news.
* Verifies whether news is Real or Fake.

### Admin (Future Enhancement)

* Manage users and system activities.

---

## 🗄 Database Tables

### Users

Stores user information.

| Column  | Description         |
| ------- | ------------------- |
| user_id | Unique User ID      |
| name    | User Name           |
| email   | User Email          |
| role    | user / fact_checker |

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

## ⚙ Features

* Add Users
* Add News
* Report News
* Fact Check News
* View Reports
* View Fake News
* JDBC Database Connectivity
* SQL JOIN Queries
* CRUD Operations

---

## 🧠 OOP Concepts Used

### Encapsulation

Used through classes and data members.

### Abstraction

Implemented using interfaces.

### Inheritance

Implemented through interface implementation.

### Polymorphism

Used by creating interface references to objects.

---

## 🔄 Project Workflow

1. User submits a suspicious news report.
2. Report is stored in the Reports table.
3. Fact-checker reviews the reported news.
4. Verification result is stored in Fact_Checks.
5. System displays fake news records.

---

## 🚀 How to Run

### Step 1: Create Database

```sql
CREATE DATABASE fakenewsdb;
```

### Step 2: Create Required Tables

Run all table creation scripts provided in the project.

### Step 3: Configure JDBC Connection

Update your database credentials:

```java
DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/fakenewsdb",
    "root",
    "your_password"
);
```

### Step 4: Run the Project

Compile and execute the Java file:

```bash
javac DB_Project.java
java DB_Project
```

---

## 📊 Sample SQL Queries

### View All Reports

```sql
SELECT Users.name, News.title, Reports.reason
FROM Reports
JOIN Users ON Reports.user_id = Users.user_id
JOIN News ON Reports.news_id = News.news_id;
```

### View Fake News

```sql
SELECT News.title, Fact_Checks.status
FROM Fact_Checks
JOIN News ON Fact_Checks.news_id = News.news_id
WHERE status='Fake';
```

---

## 🔮 Future Enhancements

* AI-based Fake News Detection
* User Authentication
* GUI Interface
* News API Integration
* Source Credibility Analysis
* Web-Based Version

---

## 📚 Academic Purpose

This project was developed as part of a **Database Management Systems (DBMS)** course to demonstrate practical implementation of database design, SQL, JDBC, and Object-Oriented Programming concepts.

---

## 👨‍💻 Author

**Bharat Kumar**

DBMS Course Project
