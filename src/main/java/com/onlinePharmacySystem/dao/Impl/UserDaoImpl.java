package com.onlinePharmacySystem.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.onlinePharmacySystem.dao.UserDao;
import com.onlinePharmacySystem.model.Product;
import com.onlinePharmacySystem.model.User;

public class UserDaoImpl implements UserDao{
	private DataSource dataSource;
	

	public UserDaoImpl(DataSource datasource) {
		this.dataSource = datasource;
	}


	@Override
	public User login(String email, String password) throws SQLException {
	    User user = null;

	    try (Connection con = dataSource.getConnection();
	         PreparedStatement ps = con.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?")) {

	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String dbPassword = rs.getString("PASSWORD");

	            if (dbPassword != null && dbPassword.equals(password)) {
	                user = new User();
	                user.setUSER_ID(rs.getInt("USER_ID"));
	                user.setEMAIL(rs.getString("EMAIL"));
	                user.setFULL_NAME(rs.getString("FULL_NAME"));
	                user.setPASSWORD(dbPassword);
	                user.setUSER_ROLE(rs.getString("USER_ROLE"));
	                user.setCREATED_AT(rs.getDate("CREATED_AT"));
	                System.out.println(user);
	            }
	        }

	       
	    }
       
	    return user;
	}


	@Override
	public boolean register(User user) {
		try {
			Connection con = dataSource.getConnection();
			String query = "INSERT INTO USERS (FULL_NAME,EMAIL,PASSWORD) VALUES (?,?,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, user.getFULL_NAME());
			st.setString(2, user.getEMAIL());
			st.setString(3, user.getPASSWORD());
			int row = st.executeUpdate();
			System.out.println(row);
			if (row == 1) {
				System.out.println("Register success ");
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
		
	}


	@Override
	public List<User> loadUsers() throws SQLException {
		List<User> Users = new ArrayList<User>();
       
		try {
			Connection con = dataSource.getConnection();
			String query = "SELECT * FROM HR.USERS ORDER BY USER_ID";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			System.out.print("You here");
			
			while(rs.next()) {
				User user = new User();
				user.setEMAIL(rs.getString("EMAIL"));
				user.setFULL_NAME(rs.getString("FULL_NAME"));
				user.setPASSWORD(rs.getString("PASSWORD"));
				user.setUSER_ID(rs.getInt("USER_ID"));
				user.setCREATED_AT(rs.getDate("CREATED_AT"));
				user.setUSER_ROLE(rs.getString("USER_ROLE"));
				Users.add(user);
			}
			
			
			
		} catch (SQLException e) {
			System.out.println("Error is in dao impl");
			System.out.println(e.getMessage());
			
		}
		return Users;
	}


	@Override
	public User loadUser(int id) {
		try {
			Connection con = dataSource.getConnection();
			
			String query = "SELECT * FROM USERS WHERE PRODUCT_ID =? ";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				
				User user = new User();
				user.setEMAIL(rs.getString("EMAIL"));
				user.setFULL_NAME(rs.getString("FULL_NAME"));
				user.setPASSWORD(rs.getString("PASSWORD"));
				user.setUSER_ID(rs.getInt("USER_ID"));
				user.setCREATED_AT(rs.getDate("CREATED_AT"));
				user.setUSER_ROLE(rs.getString("USER_ROLE"));
				return user;

				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean removeUser(int id) {
try {
			
			Connection con = dataSource.getConnection();
			String query = "DELETE FROM USERS WHERE PRODUCT_ID = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, id);
			int row = st.executeUpdate();
			System.out.println("error here"+row);
			if(row > 0) {
				System.out.println("User deldeted Succecfully");
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return false;
	}


	@Override
	public boolean addUser(User user) throws SQLException {
		try {
			 Connection con = dataSource.getConnection();
			 String query = "INSERT INTO USERS(FULL_NAME,EMAIL,PASSWORD) VALUES(?,?,?)";
			 PreparedStatement st = con.prepareStatement(query);
			 st.setString(1, user.getFULL_NAME());
			 st.setString(2, user.getEMAIL());
			 st.setString(3, user.getPASSWORD());
			 int row = st.executeUpdate();
			 if(row > 0) {
				 System.out.println("user add succecfuly");
				 return true;
			 }
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}


	@Override
	public boolean updateUser(User user) {
		System.out.println(user);
		try {
			 Connection con = dataSource.getConnection();
			 String query = "UPDATE USERS SET FULL_NAME=? ,EMAIL=? ,PASSWORD=? WHERE USER_ID =?";
			 PreparedStatement st = con.prepareStatement(query);
			 st.setString(1, user.getFULL_NAME());
			 st.setString(2, user.getEMAIL());
			 st.setString(3, user.getPASSWORD());
			 st.setInt(4, user.getUSER_ID());
			 
			 int row = st.executeUpdate();
			 if(row > 0) {
				 System.out.println("user updated succecfuly");
				 return true;
			 }
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}


	@Override
	public boolean updateRole(int id, String role) throws SQLException {
		System.out.println("id"+id+"role"+role);
		Connection con = dataSource.getConnection();
		String query = "UPDATE USERS SET USER_ROLE = ? WHERE USER_ID = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, role);
		ps.setInt(2, id);
		int row = ps.executeUpdate();
		if (row > 0) {
			System.out.println("Updat Role succesfully");
			return true;
		}else {
			return false;
		}
	}

}