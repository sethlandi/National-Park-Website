package com.techelevator.npgeek.authentication;

import com.techelevator.npgeek.User.User;

public interface AuthProviderDAO {
	
		boolean isLoggedIn();

	    User getCurrentUser();
	   
	    boolean signIn(User user);
	   
	    void logOff();
	    
	    boolean changePassword(String existingPassword, String newPassword);
}
