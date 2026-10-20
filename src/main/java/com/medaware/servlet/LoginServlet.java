package com.medaware.servlet;
import java.io.IOException;
import java.util.Locale;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.medaware.dao.UserDAO;
import com.medaware.model.User;
import com.medaware.util.PasswordUtil;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		//Receive the login details
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		if(email == null || email.trim().isEmpty()||password==null || password.isEmpty())
        {
		 response.getWriter().println("<h2>Please enter email and password.</h2>");
		 return;
        }
		email = email.trim().toLowerCase(Locale.ROOT);
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserByEmail(email);
		if(user!=null && PasswordUtil.checkPassword(password,user.getPassword()))
		{
			//create login session
			jakarta.servlet.http.HttpSession session = request.getSession();
			session.setAttribute("loggedInUser",user);
			response.sendRedirect(
				    request.getContextPath() + "/ConditionServlet"
				);
		}
		else
		{
			response.getWriter().println("<h2>Invalid email or password!</h2>");
		}
	}
}
