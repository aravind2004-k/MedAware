package com.medaware.util;
import java.sql.Connection;
public class TestDBConnection {

	public static void main(String[] args) {
		try
		{
			Connection con = DBConnection.getConnection();
			System.out.println("Connect to the Medaware database successfully!!");
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
	