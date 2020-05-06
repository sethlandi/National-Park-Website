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
			<title>Registration</title>
		</head>
		<body>
			<c:url var="submitAction" value="/registerInput"></c:url>
			<form:form action="${submitAction}" method="POST" modelAttribute="Register">
				<div>
				    <label for="userName">Username</label>
				    <form:input  path="userName"  />
				    <form:errors path="userName"  cssClass="error"/>
			    </div>
			    <div>
				    <label for="emailAddress">Email</label>
					<form:input  path="emailAddress" />            
				    <form:errors path="emailAddress" cssClass="error"/>
				    <form:errors path="emailMatching" cssClass="error"/>
			    </div>
			    <div>
				    <label for="verifyEmailAddress">Verify Email</label>
				    <form:input  path="verifyEmailAddress" />
				    <form:errors path="verifyEmailAddress" cssClass="error"/>
			    </div>
			    <div>
				    <label for="password">Password</label>
					<form:input  path="password" />            
				    <form:errors path="password" cssClass="error"/>
				    <form:errors path="passwordMatching" cssClass="error"/>
			    </div>
			    <div>
				    <label for="verifypassword">Verify Password</label>
				    <form:input  path="verifypassword" />
				    <form:errors path="verifypassword" cssClass="error"/>
			    </div>
			    <div>
				    <label for="passwordHint">Password Hint</label>
				    <form:input  path="passwordHint"  />
				    <form:errors path="passwordHint" cssClass="error"/>
			    </div>
			    <div>
			    	<input type="submit" value="Sign Me Up!"/>
				</div>
			</form:form>
			<c:import url="/WEB-INF/jsp/common/footer.jsp" />
		</body>
	</html>