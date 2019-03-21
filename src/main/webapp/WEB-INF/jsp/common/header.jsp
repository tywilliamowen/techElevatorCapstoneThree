<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<c:set var="title" value="National Park Geek" />
<title>${title}${param.pageTitle}</title>
<c:url value="/css/nationalparksgeek.css" var="cssHref" />
<link rel="stylesheet" href="${cssHref}">
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"
	integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w"
	crossorigin="anonymous">
</head>

<div class="container">
	<body>

		<header class="header column">
			<c:url value="/" var="homePageHref" />
			<c:url value="/img/logo.png" var="logoSrc" />
			<c:url value="/survey" var="survey" />
			<c:url value="/search" var="search" />

			<a href="${homePageHref}"> <img src="${logoSrc}"
				alt="National Parks Geek logo" />
			</a>
			<nav>
				<div class="topnav">
					<a class="active" href="${homePageHref}">Home</a> <a
						href="${survey}">Survey</a>
					<div class="search-container">
						<form action="${search}" method="GET">
							<input type="text" placeholder="Search.." name="search">
							<button id="searchButton" type="submit">
								Submit <i class="fa fa-search"></i>
							</button>
						</form>
					</div>
				</div>
			</nav>
		</header>