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

import com.example.entity.Employee;

public class EmployeeCrud {
	
	
	public EmployeeCrud() {
		super();
	}

	private String selectAllQuery = "select * from employee";
	private String selectOneQuery = "select * from employee where id=?";
	private String insertQuery = "insert into employee (id,name,email) values (?,?,?)";
	private String updateQuery = "update employee set name=?, email=? where id=?";
	private String deleteQuery = "delete from employee where id=?";
	
	private String dbUrl = "jdbc:mysql://localhost:3306/sampledb";
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
			if (statement!=null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Employee> readAll(){
		dbConnect(dbUrl, username, password);
		List<Employee> employees = new ArrayList<Employee>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(selectAllQuery);
			while (rs.next()) {
				employees.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return employees;
	}
	
	public Employee readById(int id) {
		dbConnect(dbUrl, username, password);
		Employee employee = new Employee();
				
		try {
			pstmt = connection.prepareStatement(selectOneQuery);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return employee;
	}
	
	public int save(Employee employee) {
		dbConnect(dbUrl, username, password);
		int status = 0;				
		try {
			pstmt = connection.prepareStatement(insertQuery);
			pstmt.setInt(1, employee.getId());
			pstmt.setString(2, employee.getName());
			pstmt.setString(3, employee.getEmail());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}
	
	public int update(int id, Employee employee) {
		dbConnect(dbUrl, username, password);
		int status = 0;				
		try {
			pstmt = connection.prepareStatement(updateQuery);
			pstmt.setInt(3, id);
			pstmt.setString(1, employee.getName());
			pstmt.setString(2, employee.getEmail());
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
