package com.niit.project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.niit.project.config.DBConfig;
import com.niit.project.model.User;

public class UserRepository {

	PreparedStatement preparedStatement;
	Statement statement;
	ResultSet resultSet;
	Connection connection;
	
	Scanner scanner = new Scanner(System.in);

	// Constructor is used to start the DB connection
	public UserRepository() {
		connection = DBConfig.createConnection();
	}

	// method used to add user
	public void addUser(User user) {
		String sql = "INSERT INTO User (user_id, fname, lname, age, dob, email_id, phone_number, total_yrs_of_experience, TOTAL_NM_OF_STDNTS_HANDLED, STATUS,password, manager_id, user_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
		try {

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, user.getUser_id());
			preparedStatement.setString(2, user.getFname());
			preparedStatement.setString(3, user.getLname());
			preparedStatement.setInt(4, user.getAge());
			preparedStatement.setDate(5, (java.sql.Date) user.getDob());
			preparedStatement.setString(6, user.getEmail_id());
			preparedStatement.setString(7, user.getPhone_number());
			preparedStatement.setInt(8, user.getTotal_yrs_of_experience());
			preparedStatement.setInt(9, user.getTotal_nm_of_stdnts_handled());
			preparedStatement.setInt(10, user.getStatus());
			String password = user.getPassword();
			System.out.println("password is: "+password);
			preparedStatement.setString(11, user.getPassword());
			preparedStatement.setInt(12, user.getManager_id());
			preparedStatement.setString(13, user.getUser_type());
			System.out.println("here");

			int rowsInserted = preparedStatement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("A new user was inserted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editUser(int user_id, User user) {
		//method use to edit user
		String sql = "UPDATE user SET fname=?, lname=?, age=? WHERE user_id="+user_id;
		 
		int rowsUpdated = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getFname());
			preparedStatement.setString(2, user.getLname());
			preparedStatement.setInt(3, user.getAge());
			 
			rowsUpdated = preparedStatement.executeUpdate();
			System.out.println("User edited");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rowsUpdated > 0) {
		    System.out.println("An existing user was updated successfully!");
		}
	}
	
	public void approvingUser(int user_id, User user) {
		//method use to edit user
		String sql = "UPDATE user SET status='1' WHERE user_id="+user_id;
		 
		int rowsUpdated = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
						 
			rowsUpdated = preparedStatement.executeUpdate();
			System.out.println("User edited");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rowsUpdated > 0) {
		    System.out.println("An existing user was updated successfully!");
		}
	}
	
	public void getUser() {
		try {
			String sql = "Select * from User";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			int count = 0;

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String fname = resultSet.getString(2);
				String lname = resultSet.getString(3);
				// String email = result.getString("email");

				// String output = "User #%d: %s - %s - %s - %s";
				System.out.println(id + fname + lname);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User> userListForApproval() throws SQLException {
		 System.out.println("here..");
	        List<User> userList = new ArrayList<>();
	         
	        String sql = "SELECT * FROM user where status = 0";	        
	         
	        statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	        	User user = new User();
	        	
	        	user.setUser_id(resultSet.getInt("user_id"));
		           user.setFname(resultSet.getString("fname"));
		           user.setLname(resultSet.getString("lname"));
		          user.setAge(resultSet.getInt("age"));
		          user.setDob(resultSet.getDate("dob"));
		          user.setEmail_id(resultSet.getString("email_id"));
		          user.setPhone_number(resultSet.getString("phone_number"));
		          user.setTotal_nm_of_stdnts_handled(resultSet.getInt("total_nm_of_stdnts_handled"));
		          user.setTotal_yrs_of_experience(resultSet.getInt("total_yrs_of_experience"));
		          user.setUser_type(resultSet.getString("user_type"));             
	            
	            userList.add(user);
	            
	        }
			return userList;
	        }
	
	 public List<User> listAllUsers() throws SQLException {
		 System.out.println("here..");
	        List<User> userList = new ArrayList<>();
	         
	        String sql = "SELECT * FROM user";	        
	         
	        statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	        	User user = new User();
	        	
	            user.setUser_id(resultSet.getInt("user_id"));
	           user.setFname(resultSet.getString("fname"));
	           user.setLname(resultSet.getString("lname"));
	          user.setAge(resultSet.getInt("age"));             
	            
	            userList.add(user);
	            
	        }
			return userList;
	        }

	/*public void getUserById(int user_id) {
		try {
			String sql = "Select * from User where user_id =" + user_id;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String fname = resultSet.getString(2);
				String lname = resultSet.getString(3);
				// String email = result.getString("email");

				System.out.println(id + fname + lname);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public User getUserById(int user_id) {
		User user = null;
		try {	
			
			String sql = "Select * from User where user_id =" + user_id;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String fname = resultSet.getString(2);
				String lname = resultSet.getString(3);
				int age = resultSet.getInt(4);

				System.out.println(id + fname + lname);				
				user=new User(id,fname,lname,age);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUserByUserName(String email_id) {
		User user = null;
		try {	
			
			String sql = "Select * from User where email_id =" + "'"+email_id + "'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				email_id = resultSet.getString("email_id");
				String password = resultSet.getString("password");
				System.out.println(email_id + password);		
				user=new User();
				
				
				user.setUser_id(resultSet.getInt("user_id"));
		           user.setFname(resultSet.getString("fname"));
		           user.setLname(resultSet.getString("lname"));
		          user.setAge(resultSet.getInt("age"));
		          user.setDob(resultSet.getDate("dob"));
		          user.setEmail_id(resultSet.getString("email_id"));
		          user.setPhone_number(resultSet.getString("phone_number"));
		          user.setTotal_nm_of_stdnts_handled(resultSet.getInt("total_nm_of_stdnts_handled"));
		          user.setTotal_yrs_of_experience(resultSet.getInt("total_yrs_of_experience"));
		          user.setUser_type(resultSet.getString("user_type"));
			}       	
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> getUserByUserNameForManager(String fname) throws SQLException {
		 System.out.println("here..");
	        List<User> userList = new ArrayList<>();
	         
	        String sql = "Select * from User where status=1 and fname like "+ "'%"+fname + "%'";	        
	         
	        statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
	         
	        while (resultSet.next()) {
	        	User user = new User();
	        	
	            user.setUser_id(resultSet.getInt("user_id"));
	           user.setFname(resultSet.getString("fname"));
	           user.setLname(resultSet.getString("lname"));
	          user.setAge(resultSet.getInt("age"));             
	            
	            userList.add(user);
	            
	        }
			return userList;
	        }
	
	public void deleteUser(int user_id) {

		try {
			String sql = "delete from user where user_id =" + user_id;
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setInt(1, 1);
			int rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Rows deleted");
	}

}
