package com.techelevator.npgeek.User;

import java.util.ArrayList; 
import java.util.List;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.authentication.PasswordHasher;

@Component
public class JdbcUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;
	 
	@Autowired
    public JdbcUserDAO(DataSource dataSource, PasswordHasher passwordHasher) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordHasher = passwordHasher;
    }
	
	@Override
	public List<User> getAllUsers() {
			List<User> Users = new ArrayList<User>();
			String sqlGetAllUsers = "SELECT * "
								  + "FROM userinfo ";
			
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllUsers);
			
			while (results.next()) {
				User theUser = mapRowToUser(results);
				Users.add(theUser);
			}
			return Users;
	}
	
	private User mapRowToUser(SqlRowSet results) {
			
		User theUser = new User();
			
			theUser.setUserName(results.getString("username"));
			theUser.setPassword(results.getString("password"));
			theUser.setPasswordHint(results.getString("passwordhint"));
			theUser.setEmailAddress(results.getString("email"));
			
			return theUser;
		}
	
	@Override
	public void changePassword(User user, String newPassword) {
		 byte[] salt = passwordHasher.generateRandomSalt();
	     String hashedPassword = passwordHasher.computeHash(newPassword, salt);
	     String saltString = new String(Base64.encode(salt));
	
	     jdbcTemplate.update("UPDATE userinfo "
	     					+ "SET password=?, salt=? "
	     					+ "WHERE id=?", hashedPassword, saltString, user.getUserId());	
	}
	
	@Override
	public User getValidUserWithPassword(String userName, String password) {
		
		String sqlSearchForUser = "SELECT * "
								+ "FROM userinfo "
								+ "WHERE username = ?";

	        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName);
	        
	        if (results.next()) {
	            String storedSalt = results.getString("salt");
	            String storedPassword = results.getString("password");
	            String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
	            if (storedPassword.equals(hashedPassword)) {
	                return mapRowToUser(results);
	            } else {
	                return null;
	            }
	        } else {
	            return null;
	        }
	}

}
