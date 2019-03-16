<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:import url="/WEB-INF/jsp/common/header.jsp" />

  <main class="content column">
  
   
<div id="clouds">

<c:url var="homepage" value="/"/>
            <div class="cloud x1"></div>
            <div class="cloud x1_5"></div>
            <div class="cloud x2"></div>
            <div class="cloud x3"></div>
            <div class="cloud x4"></div>
            <div class="cloud x5"></div>
        </div>
        <div class="c">
            <div class="_404">404</div>
            <hr>
            <div class="_1">THE PAGE</div>
            <div class="_2">WAS NOT FOUND, TRY AGAIN!</div>
            <a class="btn" href="${homepage}">BACK TO HOMEPAGE</a>
        </div>


  </main>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />