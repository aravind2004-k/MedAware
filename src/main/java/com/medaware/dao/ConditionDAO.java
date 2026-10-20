package com.medaware.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.medaware.model.Condition;
import com.medaware.util.DBConnection;
public class ConditionDAO {
	public List<Condition> getAllConditions()
	{
		List<Condition> conditions = new ArrayList<>();
		String sql = "SELECT CONDITION_ID,CONDITION_NAME,DESCRIPTION "
		           + "FROM CONDITIONS ORDER BY CONDITION_ID";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery())
		{
			while(rs.next())
			{
				Condition condition = new Condition();
				condition.setConditionId(rs.getInt("CONDITION_ID"));
				condition.setConditionName(rs.getString("CONDITION_NAME"));
				condition.setDescription(rs.getString("DESCRIPTION"));
				conditions.add(condition);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conditions;
	}
	public List<Condition> searchConditions(String conditionName) {

	    List<Condition> conditions = new ArrayList<>();

	    String sql = "SELECT CONDITION_ID, CONDITION_NAME, DESCRIPTION "
	               + "FROM CONDITIONS "
	               + "WHERE LOWER(CONDITION_NAME) LIKE LOWER(?) "
	               + "ORDER BY CONDITION_ID";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, "%" + conditionName.trim() + "%");

	        try (ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {

	                Condition condition = new Condition();

	                condition.setConditionId(rs.getInt("CONDITION_ID"));
	                condition.setConditionName(rs.getString("CONDITION_NAME"));
	                condition.setDescription(rs.getString("DESCRIPTION"));

	                conditions.add(condition);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return conditions;
	}
}
