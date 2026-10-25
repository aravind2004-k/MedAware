# MedAware – Health Condition and Medicine Information System

MedAware is a Java-based web application that allows users to search for common health conditions and view related medicine information. It provides general information about medicine uses, precautions, side effects, warnings, and timing details through a simple user-friendly interface.

**Disclaimer:** This application is developed for educational purposes only. It does not provide medical diagnosis or prescriptions. Always consult a qualified healthcare professional before taking medication.

## Features

- **User Registration:** Create a new account.
- **User Login:** Secure authentication using BCrypt password hashing.
- **Health Condition Search:** Search for available health conditions.
- **Medicine Information:** View general uses, precautions, side effects, and warnings.
- **Medicine Timing:** Display medicine timing and food instructions.
- **Search History:** View previously searched health conditions.
- **Logout:** Securely end the user session.

## Technologies Used

- **Programming Language:** Java
- **Backend:** Java Servlets, JSP
- **Database:** Oracle Database 21c XE
- **Database Connectivity:** JDBC
- **Frontend:** HTML, CSS
- **Web Server:** Apache Tomcat 10.1
- **Password Security:** BCrypt
- **IDE:** Eclipse IDE

## Database Structure

The application uses the following Oracle Database tables:

| Table | Description |
|---|---|
| USERS | Stores registered user information |
| CONDITIONS | Stores health condition details |
| MEDICINES | Stores medicine information |
| CONDITION_MEDICINE | Maps health conditions to medicines |
| SEARCH_HISTORY | Stores users' search history |

## Project Architecture

The application follows a layered architecture:

1. **Model:** Represents application data.
2. **DAO:** Handles database operations using JDBC.
3. **Servlet:** Processes HTTP requests and application logic.
4. **JSP:** Displays dynamic web pages.
5. **Utility:** Provides reusable functions such as database connectivity and password hashing.

## Prerequisites

- JDK 21
- Eclipse IDE for Enterprise Java and Web Developers
- Apache Tomcat 10.1
- Oracle Database 21c XE
- Oracle JDBC Driver

## Database Configuration

1. Start Oracle Database and ensure the `XEPDB1` pluggable database is available.
2. Create the `MEDAWARE` database user.
3. Create the required database tables.
4. Configure the database connection using `DBConnection.java`.

**Database Connection Details:**

- Database URL: `jdbc:oracle:thin:@localhost:1521/XEPDB1`
- Username: `MEDAWARE`
- Password: Configure through the `MEDAWARE_DB_PASSWORD` environment variable.

For Windows, configure the environment variable using:

```cmd
setx MEDAWARE_DB_PASSWORD "YOUR_DATABASE_PASSWORD"
```

Replace the placeholder with your own database password. Restart Eclipse after configuring the variable.

**Security:** Never upload actual database passwords or other sensitive credentials to GitHub.

## How to Run the Project

1. Clone or download this repository.
2. Import the project into Eclipse as an existing Dynamic Web Project.
3. Add the Oracle JDBC driver to the project's build path.
4. Configure Oracle Database and create the required tables.
5. Set the `MEDAWARE_DB_PASSWORD` environment variable.
6. Configure Apache Tomcat 10.1 in Eclipse.
7. Add the MedAware project to the Tomcat server.
8. Start the server and open:

```text
http://localhost:8080/Medaware/
```

## Future Enhancements

- Implement CSRF token protection.
- Add more health conditions and medicine information.
- Improve UI responsiveness.
- Add automated testing.

## Author

Developed as a Java web application project to demonstrate Core Java, JDBC, Servlets, JSP, Oracle Database, and web development skills.
