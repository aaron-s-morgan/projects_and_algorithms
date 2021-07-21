
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
</head>
<body>
<div class="container">
      <div class="header">
        <ul>
        <li>
        <c:choose>
								<c:when test = "${sessionUser.darkMode == true}">
								
								<a href="/home"><img src="/images/DishNotesIcon.gif" alt="icon" class="headerLogo"/></a>
								<link rel="stylesheet" href="/css/darkStyle.css">
								</c:when>
								<c:otherwise>
    							<a href="/home"><img src="/images/LogoDark.gif" alt="icon" class="headerLogo"/></a>
    							<link rel="stylesheet" href="/css/style.css">
								</c:otherwise>
				</c:choose>		
        <li><h1>Welcome, <c:out value="${sessionUser.firstName}" /></h1>
          <li><a href="/home">Home</a></li>
          <li><a href="/profile/${sessionUser.id}">Profile</a></li>
          <li><a href="/restaurant/new">Create a Restaurant</a></li>
          <li><a href="/logout">Logout</a></li>

          <li>    
          		<c:choose>
								<c:when test = "${sessionUser.darkMode == true}">
								<a href="/lightmode"><img src="/images/DayMode.png" alt="icon" /></a>
								<link rel="stylesheet" href="/css/darkStyle.css">
								</c:when>
								<c:otherwise>
    							<a href="/darkmode"><img src="/images/NightMode.png" alt="icon" /></a>
    							<link rel="stylesheet" href="/css/style.css">
								</c:otherwise>
				</c:choose>		
		  </li>
         
        </ul>
      </div>
   <jsp:doBody/>   
</div>
</body>
</html>