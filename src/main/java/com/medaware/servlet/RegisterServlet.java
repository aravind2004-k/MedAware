
package com.medaware.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.medaware.model.User;
import com.medaware.dao.UserDAO;
import com.medaware.util.PasswordUtil;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        // 1. Receive form data
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 2. Validate input
        if (fullName == null || fullName.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.isEmpty()) {

            out.println("<h2>Please fill in all required fields.</h2>");
            return;
        }

        fullName = fullName.trim();
        email = email.trim().toLowerCase(Locale.ROOT);

        try {
            // 3. Hash the password
            String hashedPassword =
                    PasswordUtil.hashPassword(password);

            // 4. Create User object
            User user = new User(
                    fullName,
                    email,
                    hashedPassword
            );

            // 5. Call UserDAO
            UserDAO userDAO = new UserDAO();

            boolean isRegistered = userDAO.registerUser(user);

            // 6. Display result
            if (isRegistered) {

                request.setAttribute("fullName", fullName);

                request.getRequestDispatcher("/success.jsp")
                       .forward(request, response);

                return;
            } else {

                out.println("<h2>Registration Failed!</h2>");
                out.println("<p>Please check your details. "
                        + "The email may already be registered "
                        + "or a database error may have occurred.</p>");
            }

        } catch (Exception e) {

            // Do not display technical details to the user
            getServletContext().log(
                    "Registration error occurred.", e);

            out.println("<h2>Something went wrong.</h2>");
            out.println("<p>Please try again later.</p>");
        }
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
