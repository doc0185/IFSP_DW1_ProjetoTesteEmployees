package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
public class EmployeeDAO {
	
	String serverName="localhost";
	String databasePort="3306";
	String mydatabase="employees";
	String url = "jdbc:mysql://" + serverName + ":" + databasePort + "/" + mydatabase;
	
	String username = "root";
	String password = "";

	public int registerEmployee(Employee employee) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO employee" + 
				"(id, first_name, last_name, username, password, address, contact) VALUES" +
				"(?, ?, ?, ?, ?, ?, ?);";
		int result = 0;
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = DriverManager.
				getConnection(url, username, password);
				
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){;
				
				preparedStatement.setInt(1, 1);
				preparedStatement.setString(2, employee.getFirstName());
				preparedStatement.setString(3, employee.getLastName());
				preparedStatement.setString(4, employee.getUsername());
				preparedStatement.setString(5, employee.getPassword());
				preparedStatement.setString(6, employee.getAddress());
				preparedStatement.setString(7, employee.getContact());
				
				System.out.println(preparedStatement);
				
				result = preparedStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	
	}
	
	public int loginEmployee(Employee employee) throws ClassNotFoundException {
		String LOGIN_USERS_SQL = "SELECT first_name, last_name FROM employee "
				+ "WHERE username = ? AND password = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = DriverManager.
				getConnection(url, username, password);
				
				PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USERS_SQL)){;
				
				preparedStatement.setString(1, employee.getUsername());
				preparedStatement.setString(2, employee.getPassword());
				
				System.out.println(preparedStatement);
				
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getString("first_name"));
					System.out.println(rs.getString("last_name"));
		
					return 1;
				}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public Employee detailsEmployee (Employee employee) throws ClassNotFoundException {
		String LOGIN_USERS_SQL = "SELECT first_name, last_name, address, contact FROM employee "
				+ "WHERE username = ? AND password = ?;";
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = DriverManager.
				getConnection(url, username, password);
				
				PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USERS_SQL)){;
				
				preparedStatement.setString(1, employee.getUsername());
				preparedStatement.setString(2, employee.getPassword());
				
				System.out.println(preparedStatement);
				
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getString("first_name"));
					System.out.println(rs.getString("last_name"));
					System.out.println(rs.getString("address"));
					System.out.println(rs.getString("contact"));
					
					Employee loginEmployee = new Employee();
					loginEmployee.setFirstName(rs.getString("first_name"));
					loginEmployee.setLastName(rs.getString("last_name"));
					loginEmployee.setAddress(rs.getString("address"));
					loginEmployee.setContact(rs.getString("contact"));
					return loginEmployee;
				}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
