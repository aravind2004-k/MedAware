<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MedAware Login</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

    <div class="login-container">

        <h1>Welcome Back to MedAware</h1>

        <p>Login to your Account</p>

        <form action="${pageContext.request.contextPath}/LoginServlet"
              method="post">

            <label for="email">Email:</label>

            <input id="email"
                   type="email"
                   name="email"
                   placeholder="Enter your email"
                   required>

            <label for="password">Password:</label>

            <input id="password"
                   type="password"
                   name="password"
                   placeholder="Enter your password"
                   required>

            <button type="submit">Login</button>

        </form>

        <p>
            Don't have an account?
            <a href="${pageContext.request.contextPath}/register.jsp">
                Register here
            </a>
        </p>

    </div>

</body>
</html>
