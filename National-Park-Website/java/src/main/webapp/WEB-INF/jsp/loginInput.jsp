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
			<title>Login</title>
		</head>
		<body>
			<h1>Login</h1>
			<div>
				<c:url var="submitAction" value="/loginInput"></c:url>
				<form:form action="${submitAction}" method="POST" modelAttribute="User">
					<div>
						<label for="userName">Username </label>
						<form:input  path="userName"/>
						<form:errors path="userName"  cssClass="error"/>
					</div>
					<div>
						<label for="password">Password </label>
						<form:input  path="password"/>
						<form:errors path="password"  cssClass="error"/>
					</div>
					<div>
						<label for="expand-toggle">Hint</label>
						<input type="checkbox" id="expand-toggle">
						<table>
							<tr class="expandable" ><td>${aLogin.passwordHint } </td></tr>
						</table>
					</div>
					<div>
						<input type="submit" value="Login">
					</div>
					<div>
						<c:url var="registerInputUrl" value="/registerInput"></c:url>
						<a href="${registerInputUrl}">
							<input type="button" value="Register">
						</a>
					</div>
				</form:form>
			</div>
		</body>
		<c:import url="/WEB-INF/jsp/common/footer.jsp" />
	</html>