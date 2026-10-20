
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MedAware - Registration</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

    <div class="register-container">

        <h1>Welcome to MedAware</h1>

        <p>Create Your Account</p>

        <form action="${pageContext.request.contextPath}/RegisterServlet"
              method="post">

            <label for="fname">Full Name:</label>

            <input id="fname"
                   type="text"
                   name="fullName"
                   placeholder="Enter your full name"
                   required>

            <label for="e-mail">Email:</label>

            <input id="e-mail"
                   type="email"
                   name="email"
                   placeholder="Enter your email"
                   required>

            <label for="pwd">Password:</label>

            <input id="pwd"
                   type="password"
                   name="password"
                   placeholder="Enter your password"
                   required>

            <button type="submit">Create Account</button>

        </form>

        <p>
            Already have an account?
            <a href="${pageContext.request.contextPath}/login.jsp">
                Login here
            </a>
        </p>

    </div>

</body>
</html>
