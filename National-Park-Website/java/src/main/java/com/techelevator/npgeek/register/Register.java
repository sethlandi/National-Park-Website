package com.techelevator.npgeek.register;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Register {
	
	private Long userId;
	
	@NotBlank(message="Password hint is required")
	@Size(min=5,max=30, message="Password hint must be greater than 5 characters long")
	private String passwordHint;
	
	@NotBlank(message="Username is required")
	@Size(min=4,max=20, message="Username must be greater than 4 characters long")
	private String userName;
	
	@NotBlank(message="Password is required")
	@Size(min=8,max=20, message="Password must be greater than 8 characters long")
	private String password;
	
	@NotBlank(message="Verify email address is required")
	private String verifypassword;
	
	@NotBlank(message="Email address is required")
	@Email(message="Email must be a valid email address")
	private String emailAddress;
	
	@NotBlank(message="Verify password is required")
	private String verifyEmailAddress;
	
	@SuppressWarnings("unused")
	private boolean emailMatching;
	
	@AssertTrue(message="Emails must match")
	public boolean isEmailMatching() {
		if(emailAddress != null) {
			return emailAddress.equals(verifyEmailAddress);
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unused")
	private boolean passwordMatching;
	
	@AssertTrue(message="Passwords must match")
	public boolean isPasswordMatching() {
		if(password != null) {
			return password.equals(verifypassword);
		} else {
			return false;
		}
	}

	public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifypassword() {
		return verifypassword;
	}

	public void setVerifypassword(String verifypassword) {
		this.verifypassword = verifypassword;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getVerifyEmailAddress() {
		return verifyEmailAddress;
	}

	public void setVerifyEmailAddress(String verifyEmailAddress) {
		this.verifyEmailAddress = verifyEmailAddress;
	}

	public void setEmailMatching(boolean emailMatching) {
		this.emailMatching = emailMatching;
	}

	public void setPasswordMatching(boolean passwordMatching) {
		this.passwordMatching = passwordMatching;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}	
}
