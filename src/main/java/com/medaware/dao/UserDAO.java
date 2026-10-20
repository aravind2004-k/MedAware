package com.medaware.dao;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.medaware.model.User;
import com.medaware.util.DBConnection;

public class UserDAO {

    public boolean registerUser(User user) {

        String sql = "INSERT INTO USERS "
                   + "(FULL_NAME, EMAIL, PASSWORD_HASH) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public User getUserByEmail(String email)
    {
    	String sql = "SELECT USER_ID, FULL_NAME, EMAIL, PASSWORD_HASH "
    	           + "FROM USERS WHERE EMAIL = ?";
    	try(Connection con = DBConnection.getConnection();
    			PreparedStatement ps = con.prepareStatement(sql))
    	{
    		ps.setString(1,email);
    		try (ResultSet rs = ps.executeQuery())
    		{
    			if(rs.next())
    			{
    				User user = new User();
    				user.setUserId(rs.getInt("USER_ID"));
    				user.setFullName(rs.getString("FULL_NAME"));
    				user.setEmail(rs.getString("EMAIL"));
    				user.setPassword(rs.getString("PASSWORD_HASH"));
    				return user;
    			}
    		}
    	}catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return null;
    }
}