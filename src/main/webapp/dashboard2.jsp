
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.medaware.model.Condition" %>
<%@ page import="com.medaware.model.User" %>
<%@ page import="com.medaware.model.Medicine" %>
<%@ page import="com.medaware.model.MedicineTiming" %>

<%
    // Get logged-in user
    User user = (User) session.getAttribute("loggedInUser");

    // Protect dashboard
    if (user == null) {
        response.sendRedirect(
            request.getContextPath() + "/login.jsp"
        );
        return;
    }

    // Get conditions
    List<Condition> conditions =
        (List<Condition>) request.getAttribute("conditions");

    // Get medicines
    List<Medicine> medicines =
        (List<Medicine>) request.getAttribute("medicines");

    // Get medicine timings
    List<MedicineTiming> timings =
        (List<MedicineTiming>) request.getAttribute("timings");

    // Get search input
    String searchName = request.getParameter("conditionName");
%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>MedAware Dashboard</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="dashboard-layout">

    <!-- LEFT SIDEBAR -->

    <div class="sidebar">

        <h2>♥ MedAware</h2>

        <a href="#dashboard">Dashboard</a>

        <a href="#search">Search Conditions</a>

        <a href="#medicines">Medicine Information</a>

        <a href="#timings">Medicine Timings</a>

        <a href="${pageContext.request.contextPath}/SearchHistoryServlet">
            Search History
        </a>

        <a href="${pageContext.request.contextPath}/LogoutServlet">
            Logout
        </a>

    </div>


    <!-- MAIN DASHBOARD CONTENT -->

    <div class="main-content" id="dashboard">

        <!-- WELCOME SECTION -->

        <div class="welcome-card">

            <h1>Welcome to MedAware!</h1>

            <h2>Welcome, <%= user.getFullName() %></h2>

            <p>You have successfully logged in.</p>

        </div>


        <!-- SEARCH HEALTH CONDITION -->

        <div class="search-card" id="search">

            <h3>Search Health Condition</h3>

            <form action="${pageContext.request.contextPath}/ConditionServlet"
                  method="get">

                <input type="text"
                       name="conditionName"
                       placeholder="Enter condition name"
                       required>

                <button type="submit">Search</button>

            </form>

        </div>


        <!-- DISPLAY SEARCH RESULTS -->

        <%
            if (searchName != null && !searchName.trim().isEmpty()) {
        %>

            <h3>Search Results for: <%= searchName %></h3>

        <%
            } else {
        %>

            <h3>Available Health Conditions</h3>

        <%
            }
        %>


        <!-- DISPLAY HEALTH CONDITIONS -->

        <%
            if (conditions != null && !conditions.isEmpty()) {

                for (Condition condition : conditions) {
        %>

                    <div class="info-card">

                        <h4><%= condition.getConditionName() %></h4>

                        <p><%= condition.getDescription() %></p>

                    </div>

        <%
                }

            } else {

                if (searchName != null && !searchName.trim().isEmpty()) {
        %>

                    <p>No matching health conditions found.</p>

        <%
                } else {
        %>

                    <p>No health conditions available.</p>

        <%
                }
            }
        %>


        <!-- MEDICINE INFORMATION -->

        <div id="medicines">

        <%
            if (medicines != null && !medicines.isEmpty()) {
        %>

            <h3>Suggested Medicines Information</h3>

        <%
                for (Medicine medicine : medicines) {
        %>

                    <div class="info-card">

                        <h4><%= medicine.getMedicineName() %></h4>

                        <p>
                            <strong>General Uses:</strong>
                            <%= medicine.getGeneralUses() %>
                        </p>

                        <p>
                            <strong>Precautions:</strong>
                            <%= medicine.getPrecautions() %>
                        </p>

                        <p>
                            <strong>Side Effects:</strong>
                            <%= medicine.getSideEffects() %>
                        </p>

                        <p>
                            <strong>Warnings:</strong>
                            <%= medicine.getWarnings() %>
                        </p>

                    </div>

        <%
                }

            } else if (searchName != null
                    && !searchName.trim().isEmpty()
                    && conditions != null
                    && !conditions.isEmpty()) {
        %>

                <p>No medicine information available for this condition.</p>

        <%
            }
        %>

        </div>


        <!-- MEDICINE TIMINGS -->

        <div id="timings">

        <%
            if (timings != null && !timings.isEmpty()) {
        %>

            <h3>Medicine Timings Information</h3>

        <%
                for (MedicineTiming timing : timings) {
        %>

                    <div class="info-card">

                        <h4><%= timing.getTimeOfDay() %></h4>

                        <p>
                            <strong>Food Instruction:</strong>
                            <%= timing.getFoodInstruction() %>
                        </p>

                    </div>

        <%
                }

            } else if (medicines != null && !medicines.isEmpty()) {
        %>

                <p>No timing information available for these medicines.</p>

        <%
            }
        %>

        </div>


        <!-- MEDICAL DISCLAIMER -->

        <div class="footer">

            <p>
                <small>
                    This information is for educational purposes only.
                    Consult a qualified healthcare professional before
                    taking any medicine.
                </small>
            </p>

            <p>© 2026 MedAware. All rights reserved.</p>

        </div>

    </div>

</div>

</body>
</html>
