<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:import url="/WEB-INF/jsp/common/header.jsp" />

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<c:url value="./css/npgeek.css" var="cssURL" />
			<link rel="stylesheet" type="text/css" href="${cssURL}">
			<title>Daily Survey</title>
		</head>
		<body>
			<h1>New Survey</h1>
			<div>
			<c:url var="submitAction" value="/surveyInput"></c:url>
				<form:form action="${submitAction}" method="POST" modelAttribute="Survey">
					<div>
						<label for="parkCode">Park:</label>
						<select name="parkCode">
							<c:forEach var="park" items="${parks}"> 
								<option value="${park.parkCode}">${park.parkName}</option>
							</c:forEach>
						</select>
					</div>
					<div>
						<label for="emailAddress">Email </label>
						<form:input  path="emailAddress"/>
						<form:errors path="emailAddress"  cssClass="error"/>
						<form:errors path="emailMatching" cssClass="error"/>
					</div>
					<div>
	            		<label for="verifyEmailAddress">Verify Email</label>
	            		<form:input  path="verifyEmailAddress" />
	            		<form:errors path="verifyEmailAddress" cssClass="error"/>
	      		    </div>
					<div>
						<label for="state">State:</label> 
						<select name="state">
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
							<option value="DE">Delaware</option>
							<option value="DC">District Of Columbia</option>
							<option value="FL">Florida</option>
							<option value="GA">Georgia</option>
							<option value="HI">Hawaii</option>
							<option value="ID">Idaho</option>
							<option value="IL">Illinois</option>
							<option value="IN">Indiana</option>
							<option value="IA">Iowa</option>
							<option value="KS">Kansas</option>
							<option value="KY">Kentucky</option>
							<option value="LA">Louisiana</option>
							<option value="ME">Maine</option>
							<option value="MD">Maryland</option>
							<option value="MA">Massachusetts</option>
							<option value="MI">Michigan</option>
							<option value="MN">Minnesota</option>
							<option value="MS">Mississippi</option>
							<option value="MO">Missouri</option>
							<option value="MT">Montana</option>
							<option value="NE">Nebraska</option>
							<option value="NV">Nevada</option>
							<option value="NH">New Hampshire</option>
							<option value="NJ">New Jersey</option>
							<option value="NM">New Mexico</option>
							<option value="NY">New York</option>
							<option value="NC">North Carolina</option>
							<option value="ND">North Dakota</option>
							<option value="OH">Ohio</option>
							<option value="OK">Oklahoma</option>
							<option value="OR">Oregon</option>
							<option value="PA">Pennsylvania</option>
							<option value="RI">Rhode Island</option>
							<option value="SC">South Carolina</option>
							<option value="SD">South Dakota</option>
							<option value="TN">Tennessee</option>
							<option value="TX">Texas</option>
							<option value="UT">Utah</option>
							<option value="VT">Vermont</option>
							<option value="VA">Virginia</option>
							<option value="WA">Washington</option>
							<option value="WV">West Virginia</option>
							<option value="WI">Wisconsin</option>
							<option value="WY">Wyoming</option>
						</select>
					</div>
					<div>
						<label for="activityLevel">Activity Level:</label>
						<select name="activityLevel">
								<option value="Inactive">Inactive</option>
								<option value="Sedentary">Sedentary</option>
								<option value="Active">Active</option>
								<option value="Extremely Active">Extremely Active</option>
						</select>	
					</div>
				 	<input type="submit" value="Submit">
				 	<input type="reset">
				 </form:form>
			 </div>
		</body>
		<c:import url="/WEB-INF/jsp/common/footer.jsp" />
	</html>