package com.onlinePharmacySystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.onlinePharmacySystem.model.Product;
import com.onlinePharmacySystem.model.User;

public interface UserDao {
     User login(String name , String password) throws SQLException;
     boolean register(User user) ;
     List<User> loadUsers() throws SQLException;
 	 User loadUser(int id);
 	 boolean removeUser(int id) ;
 	 boolean addUser(User user) throws SQLException;
 	 boolean updateUser(User user);
 	 boolean updateRole(int id,String role) throws SQLException;
     
}
