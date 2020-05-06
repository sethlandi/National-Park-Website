<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<c:url value="./css/npgeek.css" var="cssURL" />
			<link rel="stylesheet" type="text/css" href="${cssURL}">
		<title>Home</title>
		</head>
		<body>
			<div>
				<table>
					<c:forEach var="park" items="${ parks }">  
						<a href="parkDetail?parkCode=${park.parkCode}">
							<img src="img/parks/${fn:toLowerCase(park.parkCode)}.jpg" /> 
						</a>
						<div>
							<h1>${park.parkName} , ${park.state}</h1>
							<p>${park.parkDescription}</p>
						</div>
					</c:forEach>
				 </table>
			</div>
		</body>
		<c:import url="/WEB-INF/jsp/common/footer.jsp" />
	</html>