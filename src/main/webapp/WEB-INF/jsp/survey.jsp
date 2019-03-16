<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page errorPage="errors.jsp" %>  

<c:import url="/WEB-INF/jsp/common/header.jsp" />

  <main class="content column">

	<div>
		<div>
			<h1>Daily Survey</h1>
			<p>What is your favorite park! Fill out all of the forms below to
				see how your park stacks up against our other users favorite
				National Parks!</p>
		</div>
		<c:url var="surveyUrl" value="/survey" />
		<form:form class="pure-form pure-form-aligned" action="${surveyUrl}" method="POST" modelAttribute="survey" >
			
			<div class = "pure-control-group">
				<div class="block">
					<label for="parkCode">Favorite National Park:</label> 
					<form:select path="parkCode">
						<option value="CVNP">Cuyahoga Valley National Park</option>
						<option value="ENP">Everglades National Park</option>
						<option value="GCNP">Grand Canyon National Park</option>
						<option value="GNP">Glacier National Park</option>
						<option value="GSMNP">Great Smoky Mountains National Park</option>
						<option value="GTNP">Grand Teton National Park</option>
						<option value="MRNP">Mount Rainier National Park</option>
						<option value="RMNP">Rocky Mountain National Park</option>
						<option value="YNP">Yellowstone National Park</option>
						<option value="YNP2">Yosemite National Park</option>
					</form:select>
				</div>
				<br>
			<div>
			
        		<label for="emailAddress">Email:</label>
        		<form:input path="emailAddress" />
        		<form:errors path="emailAddress" cssClass="error" />		
        	</div>
        		<br>
				<div> 
					<label for="state">State of residence:</label> 
					<form:select path="state">
						<option value="AL">Alabama</option>
						<option value="AK">Alaska</option>
						<option value="AZ">Arizona</option>
						<option value="AR">Arkansas</option>
						<option value="CA">California</option>
						<option value="CO">Colorado</option>
						<option value="CT">Connecticut</option>
						<option value="DE">Delaware</option>
						<option value="DC">District Of Columbia</option>
						<option value="FL">Florida</option>
						<option value="GA">Georgia</option>
						<option value="HI">Hawaii</option>
						<option value="ID">Idaho</option>
						<option value="IL">Illinois</option>
						<option value="IN">Indiana</option>
						<option value="IA">Iowa</option>
						<option value="KS">Kansas</option>
						<option value="KY">Kentucky</option>
						<option value="LA">Louisiana</option>
						<option value="ME">Maine</option>
						<option value="MD">Maryland</option>
						<option value="MA">Massachusetts</option>
						<option value="MI">Michigan</option>
						<option value="MN">Minnesota</option>
						<option value="MS">Mississippi</option>
						<option value="MO">Missouri</option>
						<option value="MT">Montana</option>
						<option value="NE">Nebraska</option>
						<option value="NV">Nevada</option>
						<option value="NH">New Hampshire</option>
						<option value="NJ">New Jersey</option>
						<option value="NM">New Mexico</option>
						<option value="NY">New York</option>
						<option value="NC">North Carolina</option>
						<option value="ND">North Dakota</option>
						<option value="OH">Ohio</option>
						<option value="OK">Oklahoma</option>
						<option value="OR">Oregon</option>
						<option value="PA">Pennsylvania</option>
						<option value="RI">Rhode Island</option>
						<option value="SC">South Carolina</option>
						<option value="SD">South Dakota</option>
						<option value="TN">Tennessee</option>
						<option value="TX">Texas</option>
						<option value="UT">Utah</option>
						<option value="VT">Vermont</option>
						<option value="VA">Virginia</option>
						<option value="WA">Washington</option>
						<option value="WV">West Virginia</option>
						<option value="WI">Wisconsin</option>
						<option value="WY">Wyoming</option>
					</form:select>
				</div>
				<br>
				<div class="rattingRadio">
				<label for="activityLevel">Activity Level:</label> 
  					<form:radiobutton path = "activityLevel" value = "Inactive"/> &nbsp;&nbsp;&nbsp;&nbsp;  Inactive
  					<form:radiobutton path = "activityLevel" value = "Sedentary"/> &nbsp;&nbsp;&nbsp;&nbsp;Sedentary
  					<form:radiobutton path = "activityLevel" value = "Active"/>  &nbsp;&nbsp;&nbsp;&nbsp; Active
  					<form:radiobutton path = "activityLevel" value = "Extremely Active"/> &nbsp;&nbsp;&nbsp;&nbsp; Extremely Active
  					<form:errors path="activityLevel" cssClass="error" />

				</div>
			</div>
			
			<div>
				<input id="submit" type="submit" value="Submit" />
			</div>
		</form:form>	
	</div>
	</main>
	
<c:import url="/WEB-INF/jsp/common/footer.jsp" />