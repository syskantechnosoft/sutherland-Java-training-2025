package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Trainer;

public class TrainerCrud {

	private String selectAllQuery = "select * from trainer";
	private String selectOneQuery = "select * from trainer where id=?";
	private String insertQuery = "insert into trainer (id,first_name,last_name,gender,email,mobile,date_of_birth) values (?,?,?,?,?,?,?)";
	private String updateQuery = "update trainer set first_name=?, last_name=?, gender=?, email=?, mobile=?, date_of_birth=? where id=?";
	private String deleteQuery = "delete from trainer where id=?";

	private String dbUrl = "jdbc:mysql://localhost:3306/sutherland";
	private String username = "root";
	private String password = "root";

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
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Trainer> readAll() {
		dbConnect(dbUrl, username, password);
		List<Trainer> trainers = new ArrayList<Trainer>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(selectAllQuery);
			while (rs.next()) {
				trainers.add(new Trainer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getLong(6), rs.getDate(7)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return trainers;
	}

	

	public Trainer readById(int id) {
		dbConnect(dbUrl, username, password);
		Trainer trainer = new Trainer();

		try {
			pstmt = connection.prepareStatement(selectOneQuery);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				trainer.setId(rs.getInt("id"));
				trainer.setFirstName(rs.getString("fist_name"));
				trainer.setLastName(rs.getString("last_name"));
				trainer.setGender(rs.getString("gender"));
				trainer.setEmail(rs.getString("email"));
				trainer.setMobile(rs.getLong("mobile"));

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return trainer;
	}

	public int save(Trainer trainer) {
		dbConnect(dbUrl, username, password);
		int status = 0;
		try {
			pstmt = connection.prepareStatement(insertQuery);
			pstmt.setInt(1, trainer.getId());
			pstmt.setString(2, trainer.getFirstName());
			pstmt.setString(3, trainer.getLastName());
			pstmt.setString(4, trainer.getGender());
			pstmt.setString(5, trainer.getEmail());
			pstmt.setLong(6, trainer.getMobile());
			pstmt.setDate(7, trainer.getDateOfBirth());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	public int update(int id, Trainer trainer) {
		dbConnect(dbUrl, username, password);
		int status = 0;
		try {
			pstmt = connection.prepareStatement(updateQuery);
			pstmt.setInt(7, id);
			pstmt.setString(1, trainer.getFirstName());
			pstmt.setString(2, trainer.getLastName());
			pstmt.setString(3, trainer.getGender());
			pstmt.setString(4, trainer.getEmail());
			pstmt.setLong(5, trainer.getMobile());
			pstmt.setDate(6, trainer.getDateOfBirth());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	public int delete(int id) {
		dbConnect(dbUrl, username, password);
		int status = 0;
		try {
			pstmt = connection.prepareStatement(deleteQuery);
			pstmt.setInt(1, id);
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

}
