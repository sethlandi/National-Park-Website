package com.techelevator.npgeek.authentication;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.User.User;
import com.techelevator.npgeek.User.UserDAO;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JdbcAuthProvider implements AuthProviderDAO{
	
	 public static final String USER_KEY = "appCurrentUser";

	 private HttpSession session; 
	 private UserDAO dao; 

	 @Autowired 
	 public JdbcAuthProvider(HttpSession session, UserDAO dao) { 
		this.session = session; 
	 	this.dao = dao; 
	    }

	@Override
	public boolean isLoggedIn() { 
	    return session.getAttribute(USER_KEY) != null; 
	}

	@Override
	public User getCurrentUser() {
	    return (User) session.getAttribute(USER_KEY);
	}

	@Override
	public boolean signIn(User user) {
	    User authenticatedUser = dao.getValidUserWithPassword(user.getUserName(), user.getPassword());
	    if (authenticatedUser != null) {
	        session.setAttribute(USER_KEY, authenticatedUser); 
	        return true;
	    } else {
	    	return false;
	    }
	}

	@Override
	public void logOff() {
	        session.removeAttribute(USER_KEY);
	        session.invalidate();
	    }

	    @Override
	    public boolean changePassword(String existingPassword, String newPassword) {
	        User userFromSession = (User) session.getAttribute(USER_KEY);
	        if (userFromSession == null) {
	            return false;
	        }
	        User userFromDb = dao.getValidUserWithPassword(userFromSession.getUserName(), existingPassword);
	        if (userFromDb != null && userFromDb.getUserId() == userFromDb.getUserId()) {
	            dao.changePassword(userFromSession, newPassword);
	            return true;
	        } else {
	            return false;
	        }
	    }
}
