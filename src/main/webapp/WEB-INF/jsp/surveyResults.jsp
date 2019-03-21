<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:import url="/WEB-INF/jsp/common/header.jsp">
	<c:param name="pageTitle" value="- Survey Result" />
</c:import>

<main class="content column">

<div>
	<div>
		<h1>Survey Results</h1>
		<p>${thankyou} ${surveyUrl.emailAddress}${votecast} Check below to see see how your park
			stacks up against our other users favorite National Parks!</p>
	</div>
	<div class="surveydisplay">
		<div id="surveyImage">
			<h4>Image</h4>
		</div>
		<div id="surveyparkName">
			<h4>Park Name</h4>
		</div>
		<div id="surveyparkVote">
			<h4>Vote</h4>
		</div>
	</div>
	<c:forEach var="park" items="${park}">

		<c:url var="parkCodeURL" value="/parkDetail">
			<c:param name="parkCode" value="${park.parkCode}" />
		</c:url>
		<c:url var="image" value="/img/parks/${park.parkCode}.jpg" />

		<div class="surveyResult">
			<div class="row">
				<a href="${parkCodeURL}"><img src="${image}"></a>
			</div>
			<div class="row">
				<p id="parkNamesurvey">
					<a href="${parkCodeURL}"><c:out value="${park.parkName}" /></a>
				</p>
			</div>
			<div class="row">
				<p>
					<c:out value="${park.votes}" />
				</p>
			</div>
		</div>
	</c:forEach>
</div>
</main>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />