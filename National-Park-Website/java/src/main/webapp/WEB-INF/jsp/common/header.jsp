<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<c:url value="/css/site.css" var="cssURL" />
			<link rel="stylesheet" type="text/css" href="${cssURL}">
		</head>
		<body>
			<c:url value="img/logo.png" var="logoImg"/>
			<img src="${logoImg}" id="logo" />
			<nav>
				<c:url var="homeUrl" value="homePage"/>
				<c:url var="surveyUrl" value="surveyInput"/>
				<c:url var="profileUrl" value="profile"/>
				<c:url var="logoutUrl" value="loginInput"/>
				
			        <c:if test="${empty appCurrentUser}">
				        <ul>
				            <li><a href="${homeUrl }">Home</a></li>
				            <li><a href="${surveyUrl }">Survey</a></li>
				             <li><a href="${profileUrl }">Profile</a></li>
				            <li><a href="${logoutUrl }">Logout</a></li>
				        </ul>
				   	</c:if>
		    </nav>
		</body>
	</html>