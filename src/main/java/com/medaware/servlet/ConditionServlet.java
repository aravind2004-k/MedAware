package com.medaware.servlet;
import java.io.IOException;
import com.medaware.dao.MedicineTimingDAO;
import com.medaware.model.MedicineTiming;
import com.medaware.dao.MedicineDAO;
import com.medaware.model.Medicine;
import com.medaware.dao.SearchHistoryDAO;
import com.medaware.model.User;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.medaware.dao.ConditionDAO;
import com.medaware.model.Condition;
@WebServlet("/ConditionServlet")
public class ConditionServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("loggedInUser")==null)
		{
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		ConditionDAO conditionDAO = new ConditionDAO();

		String searchName = request.getParameter("conditionName");

		List<Condition> conditions;

		if (searchName != null && !searchName.trim().isEmpty()) {

		    // Search for the entered condition
		    conditions = conditionDAO.searchConditions(searchName);

		} else {

		    // Display all conditions
		    conditions = conditionDAO.getAllConditions();

		}

		request.setAttribute("conditions", conditions);
		// Save search history for the logged-in user
		if (searchName != null && !searchName.trim().isEmpty()
		        && !conditions.isEmpty()) {

		    User user = (User) session.getAttribute("loggedInUser");

		    int userId = user.getUserId();

		    int conditionId = conditions.get(0).getConditionId();

		    SearchHistoryDAO historyDAO = new SearchHistoryDAO();

		    historyDAO.saveSearchHistory(userId, conditionId);
		}
		// Get medicines for the searched condition
		if (searchName != null && !searchName.trim().isEmpty()
		        && !conditions.isEmpty()) {

		    int conditionId = conditions.get(0).getConditionId();

		    MedicineDAO medicineDAO = new MedicineDAO();

		    List<Medicine> medicines =
		            medicineDAO.getMedicinesByConditionId(conditionId);

		    request.setAttribute("medicines", medicines);
		    //Get medicine timings
		    MedicineTimingDAO timingDAO = new MedicineTimingDAO();
		    List<MedicineTiming> timings = new java.util.ArrayList<>();
		    for (Medicine medicine : medicines) {

		        List<MedicineTiming> medicineTimings =
		                timingDAO.getTimingsByMedicineId(medicine.getMedicineId());

		        timings.addAll(medicineTimings);
		    }
		    request.setAttribute("timings", timings);
		}
		request.getRequestDispatcher("/dashboard.jsp")
		       .forward(request, response);	
	}
	
}
