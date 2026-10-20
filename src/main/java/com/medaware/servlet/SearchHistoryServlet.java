package com.medaware.servlet;
import java.io.IOException;
import java.util.List;

import com.medaware.dao.SearchHistoryDAO;
import com.medaware.model.SearchHistory;
import com.medaware.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SearchHistoryServlet")
public class SearchHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        // Check whether the user is logged in
        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedInUser") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp");
            return;
        }

        // Get logged-in user
        User user = (User) session.getAttribute("loggedInUser");

        int userId = user.getUserId();

        // Retrieve search history
        SearchHistoryDAO historyDAO = new SearchHistoryDAO();

        List<SearchHistory> historyList =
                historyDAO.getSearchHistoryByUserId(userId);

        // Send history to JSP
        request.setAttribute("historyList", historyList);

        // Open search history page
        request.getRequestDispatcher("/searchHistory.jsp")
               .forward(request, response);
    }
}

