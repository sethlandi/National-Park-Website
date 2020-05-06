package com.techelevator.npgeek.survey;

import javax.validation.constraints.AssertTrue;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {

	private Long parkCount;
	private Long surveyId;
	private String parkDescription;
	private String parkCode;
	private String parkName;
	private String state;
	private String activityLevel;
	
	
	@NotBlank(message="Email address is required")
	@Email(message="Email must be a valid email address")
	private String emailAddress;
	
	@NotBlank(message="Verify email address is required")
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
	
	
	public String getVerifyEmailAddress() {
		return verifyEmailAddress;
	}

	public void setVerifyEmailAddress(String verifyEmail) {
		this.verifyEmailAddress = verifyEmail;
	}
	public Long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Long id) {
		this.surveyId = id;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}


	public Long getParkCount() {
		return parkCount;
	}


	public void setParkCount(Long parkCount) {
		this.parkCount = parkCount;
	}


	public String getParkName() {
		return parkName;
	}


	public void setParkName(String parkName) {
		this.parkName = parkName;
	}


	public String getParkDescription() {
		return parkDescription;
	}


	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	
}
