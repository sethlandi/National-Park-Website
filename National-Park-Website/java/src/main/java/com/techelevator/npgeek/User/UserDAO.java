package com.techelevator.npgeek.User;

import java.util.List;

public interface UserDAO {

	List<User> getAllUsers();
	
	public void changePassword(User user, String newPassword);
	
	public User getValidUserWithPassword(String userName, String password);
}
