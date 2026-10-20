
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.medaware.model.SearchHistory" %>
<%@ page import="com.medaware.model.User" %>

<%
    User user = (User) session.getAttribute("loggedInUser");

    if (user == null) {
        response.sendRedirect(
            request.getContextPath() + "/login.jsp");
        return;
    }

    List<SearchHistory> historyList =
        (List<SearchHistory>) request.getAttribute("historyList");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MedAware Search History</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

    <div class="history-container">

        <h1>MedAware - Search History</h1>

        <h3>Welcome, <%= user.getFullName() %></h3>

        <hr>

        <br>

        <%
            if (historyList != null && !historyList.isEmpty()) {
        %>

            <table>

                <tr>
                    <th>History ID</th>
                    <th>Condition Name</th>
                    <th>Search Date</th>
                </tr>

                <%
                    for (SearchHistory history : historyList) {
                %>

                    <tr>
                        <td><%= history.getHistoryId() %></td>

                        <td><%= history.getConditionName() %></td>

                        <td><%= history.getSearchDate() %></td>
                    </tr>

                <%
                    }
                %>

            </table>

        <%
            } else {
        %>

            <p>No search history available.</p>

        <%
            }
        %>

        <a class="back-button"
           href="<%= request.getContextPath() %>/ConditionServlet">
            Back to Dashboard
        </a>

    </div>

</body>
</html>
