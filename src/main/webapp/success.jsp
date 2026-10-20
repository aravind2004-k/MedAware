
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Successful - MedAware</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

    <div class="login-container">

        <h1>MedAware</h1>
        <h2>Registration Successful!</h2>
        <p>Welcome, ${fullName}</p>
        <p>Your account has been created successfully.</p>
        <a href="${pageContext.request.contextPath}/login.jsp">
            <button type="button">Go to Login</button>
        </a>

    </div>

</body>
</html>
