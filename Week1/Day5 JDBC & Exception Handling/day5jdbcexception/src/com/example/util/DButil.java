package com.example.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DButil {
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement pstmt;
	private static CallableStatement cstmt;
	private static ResultSet rs;

	public static void dbConnect(String url, String username, String password) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public static void clearResource() {

		try {
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			if (pstmt != null)
				pstmt.close();
			if (statement!=null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
