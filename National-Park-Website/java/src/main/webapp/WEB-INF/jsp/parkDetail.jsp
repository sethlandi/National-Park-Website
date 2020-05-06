<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:import url="/WEB-INF/jsp/common/header.jsp" />

<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<c:url value="./css/npgeek.css" var="cssURL" />
			<link rel="stylesheet" type="text/css" href="${cssURL}">
			<title>Park Detail</title>
		</head>
		<body>
			<div>
				<img src="./img/parks/${fn:toLowerCase(aPark.parkCode)}.jpg" /> 
				<p>"${aPark.inspirationalQuote}" -- ${aPark.inspirationalQuoteSource}
				<h1> (${aPark.parkCode}) ${aPark.parkName} , ${aPark.state}</h1>
				<h2> Established in ${aPark.yearFounded} </h2>
				<p>	${aPark.parkDescription} </p>
			</div>			
			<table>		
				<tr>	
					<th>Climate</th>
					<th>Acreage</th>
					<th>Miles of Trail</th>
					<th>Number of Animal Species</th>
					<th>Annual Visitors</th>
					<th>Entry Fee</th>
				</tr>
				<tr>
					<td>${aPark.climate}</td>
					<td>${aPark.acreage}</td>
					<td>${aPark.milesOfTrail}mi</td>
					<td>${aPark.numberOfAnimalSpecies}</td>
					<td>${aPark.annualVisitorCount}</td>
					<td>$${aPark.entryFee}</td>
				</tr>
			</table>	
			
			<label for="expand-toggle-fahrenheit">Fahrenheit (°F)</label>
			<input type="checkbox" id="expand-toggle-fahrenheit" checked>
				
			<label for="expand-toggle-celsius">Celsius (°C)</label>
			<input type="checkbox" id="expand-toggle-celsius">
			
			<c:forEach var="weather" items="${weather}"> 
				<table>
					<tr><td><img src="./img/weather/${weather.forecast }.png" /></td></tr>
					<tr><td class="expandable-fahrenheit">High ${weather.high} °F</td><td class="expandable-celsius">High ${weather.highC} °C </td></tr>
					<tr><td class="expandable-fahrenheit">Low ${weather.low} °F</td><td class="expandable-celsius">Low ${weather.lowC} °C</td></tr>
					<tr><td></td></tr>
				</table>
				<h3>Weather Advisory:</h3>
				<p>${weather.weatherMessage}</p>
			</c:forEach>
		</body>
		<c:import url="/WEB-INF/jsp/common/footer.jsp" />
	</html>