<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<c:url value="/css/site.css" var="cssURL" />
			<link rel="stylesheet" type="text/css" href="${cssURL}">

		</head>
		<body>
		</body>
        <footer>
       		<jsp:useBean id="now" class="java.util.Date" />
            <p>&copy; <fmt:formatDate value="${now}" pattern="yyyy"/> Java[13] Super Developers</p>
        </footer>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</html>