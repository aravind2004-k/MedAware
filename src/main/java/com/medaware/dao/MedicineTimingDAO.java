package com.medaware.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.medaware.model.MedicineTiming;
import com.medaware.util.DBConnection;
public class MedicineTimingDAO {
	 public List<MedicineTiming> getTimingsByMedicineId(int medicineId) {

	        List<MedicineTiming> timings = new ArrayList<>();

	        String sql = "SELECT TIMING_ID, MEDICINE_ID, "
	                   + "TIME_OF_DAY, FOOD_INSTRUCTION "
	                   + "FROM MEDICINE_TIMINGS "
	                   + "WHERE MEDICINE_ID = ? "
	                   + "ORDER BY TIMING_ID";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, medicineId);

	            try (ResultSet rs = ps.executeQuery()) {

	                while (rs.next()) {

	                    MedicineTiming timing = new MedicineTiming();

	                    timing.setTimingId(rs.getInt("TIMING_ID"));
	                    timing.setMedicineId(rs.getInt("MEDICINE_ID"));
	                    timing.setTimeOfDay(rs.getString("TIME_OF_DAY"));
	                    timing.setFoodInstruction(
	                            rs.getString("FOOD_INSTRUCTION"));

	                    timings.add(timing);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return timings;
	    }
}
