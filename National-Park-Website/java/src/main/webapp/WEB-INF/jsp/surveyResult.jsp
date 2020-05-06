<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<c:url value="./css/npgeek.css" var="cssURL" />
			<link rel="stylesheet" type="text/css" href="${cssURL}">
			<title>Park Ranks</title>
		</head>
		<body>
			<c:forEach var="post" items="${ posts }">  
			<a href="parkDetail?parkCode=${post.parkCode}">
				<img src="img/parks/${fn:toLowerCase(post.parkCode)}.jpg" /> 
			</a>
			<h1>
				${post.parkName}
			</h1>
			<h2>
				${post.parkDescription}
			</h2>
			<h3>
				${post.parkCount}
			</h3>
			</c:forEach>
		</body>
		<c:import url="/WEB-INF/jsp/common/footer.jsp" />
	</html>