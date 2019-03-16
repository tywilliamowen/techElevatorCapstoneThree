<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 


<c:import url="/WEB-INF/jsp/common/header.jsp" />

  <main class="content column">


	<div class="parkInfo"> 
		
		<c:url var="image" value="/img/parks/${park.parkCode}.jpg"/>
		<a href="${parkCodeURL}"><img src="${image}"></a>
	
		</div>

	
	<div>
		<div> 
			<div class="parknameQuote"><h1>${park.parkName}</h1></div>
		</div>
		
			<div id="quote"><q id="inspirationalQuote">${park.inspirationalQuote}</q>
			<c:out value="-${park.inspirationalQuoteSource}"/></div>
		
		</div>
		<div> 
			<div id="parkDescription">Park Description: ${park.parkDescription}</div>
		</div>
		
		<div id="generalParkInfo">
		<div>State: ${park.state}</div>
		 
			<div>Founded: ${park.yearsFounded}</div>
		 
			<div>Annual Visitors: ${park.annualVisitorCount}</div>
		 
			<div>Entry Fee: $${park.entryFee}.00</div>
			</div>

	<div id="parkDetails">
		<div>Climate: ${park.climate} | Acerage: ${park.acerage} | Elevation in Feet: ${park.elevationInFeet} | Total Miles Of Trail: ${park.milesOfTrail}
		 | Total Campsites: ${park.numberOfCampsites} | Total Number of Animal Species in Park: ${park.numberOfAnimalSpecies} 
	</div>

	</div>

	<div class="chooseTemp">
		<c:url var="parkUrl" value="/parkDetail"/>
		<form action="${parkUrl}" method="POST" >
		<label for="selectedTempUnit">Choose A Temperature Scale</label> 
		<select name="selectedTempUnit">
	   			 <option value="fahrenheit" ${ fahrenheitChoice }>Fahrenheit</option>
	    		<option value="celsius" ${ celsiusChoice }>Celsius</option>
		</select>
  		<input type="hidden" name="parkCode" value="${park.parkCode}"/>
  		<input type="submit" value="Submit" />
  		</form>
	</div>
	
	
	<div class="weatherDeails"> 
		<c:forEach var="weather" items="${weather}">
		<div class="weather">
		<c:choose> 
			<c:when test="${weather.forecast == 'partly cloudy'}">
				<c:url var="forcastImage" value="/img/weather/partlyCloudy.png"/>
				<img src="${forcastImage}">
			</c:when> 	
			<c:otherwise> 
				<c:url var="forcastImage" value="/img/weather/${weather.forecast}.png"/>
				<img src="${forcastImage}">
			</c:otherwise>
		
		
		</c:choose>
		<div>${weather.forecastDayOfWeek}</div>
		
		<c:choose>
		<c:when test="${userTempChoice.equals('fahrenheit')}">
		<c:set var="celsiusChoice" value=""/>
		<c:set var="fahrenheitChoice" value="selected"/> 
		<div class="weatherside">
			<div class="side1">High: ${weather.high}<c:out value="${weather.showDegree}F"/></div>
			<div class="side2">Low: ${weather.low}<c:out value="${weather.showDegree}F"/> </div>
			</div>
		</c:when> 	
		<c:otherwise> 
			<c:set var="celsiusChoice" value="selected"/>
			<c:set var="fahrenheitChoice" value=""/> 
			<div class="weatherside">
			<div>High:  ${weather.highCelsius}<c:out value="${weather.showDegree}C"/></div>
			<div>Low: ${weather.lowCelsius}<c:out value="${weather.showDegree}C"/></div>
			</div>
		</c:otherwise>
	</c:choose>
	
		<%-- <div>Forecast: ${weather.forecast}</div> --%>
		
		 <%-- <div>Advisory:${weather.advisoryMessage}</div>  --%>
		
		</div>
		</c:forEach>
		
	</div>
	<div id="forecastMessage">
	<c:set var="message" value="${weather.get(0)}"/>
	<c:out value="${message.advisoryMessage}"/> 
	</div>
</div>
</main>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />

