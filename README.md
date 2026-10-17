# 🏦 Banking Management System

A console-based Banking Management System developed using **Java, JDBC, and MySQL** to perform basic banking operations. The application allows users to register, log in, manage their accounts, and perform financial transactions through a menu-driven interface.

## ✨ Features

* **User Registration:** Register new users in the system.
* **User Login:** Authenticate users before accessing banking operations.
* **Debit Money:** Withdraw money from an account.
* **Credit Money:** Deposit money into an account.
* **Fund Transfer:** Transfer funds between accounts.
* **Database Integration:** Store and manage user and banking data using MySQL.

## 🛠️ Tech Stack

| Technology  | Purpose                 |
| ----------- | ----------------------- |
| Java        | Application logic       |
| JDBC        | Database connectivity   |
| MySQL       | Data storage            |
| Eclipse IDE | Development environment |

## 📂 Project Structure

The project is organized around Java classes responsible for user interaction, banking operations, and database connectivity.

*Note: The exact package and class structure depends on the source code in the repository.*

## ⚙️ Prerequisites

Before running the project, ensure you have:

* Java JDK installed
* MySQL Server installed and running
* Eclipse IDE or another Java IDE
* MySQL JDBC Driver configured in the project

## 🚀 Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/kashishb625/BANKING_SYSTEM_PROJECT.git
```

### 2. Open the Project

Import the cloned project into Eclipse IDE.

### 3. Configure MySQL

Create the database in MySQL:

```sql
CREATE DATABASE banking_management_system;
```

Create the required tables using the SQL queries and table definitions from the project source code.

### 4. Configure Database Connectivity

Update the JDBC connection URL, username, and password in the relevant Java class to match your local MySQL configuration.

Example:

```java
String url = "jdbc:mysql://localhost:3306/banking_management_system";
String username = "your_username";
String password = "your_password";
```

Make sure the database name matches your configuration. Never commit actual database passwords to GitHub.

### 5. Run the Application

Run the main Java class from Eclipse IDE and follow the console menu to access the available banking operations.

## 📚 Concepts Demonstrated

* Core Java
* Object-Oriented Programming (OOP)
* JDBC and SQL
* MySQL database connectivity
* CRUD operations
* User authentication
* Exception handling
* Banking transaction logic

## 🎯 Learning Outcomes

This project helped me strengthen my understanding of Java programming, database connectivity, SQL queries, and implementing basic banking operations using a relational database.

## 👩‍💻 Author

**Kashish Bhatnagar**

Aspiring Java Backend Developer

* GitHub: [kashishb625](https://github.com/kashishb625)
* Project Repository: [BANKING_SYSTEM_PROJECT](https://github.com/kashishb625/BANKING_SYSTEM_PROJECT)

---

⭐ If you find this project interesting, feel free to explore the repository!
