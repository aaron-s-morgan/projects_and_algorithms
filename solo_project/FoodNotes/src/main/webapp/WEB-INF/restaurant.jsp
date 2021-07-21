<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags" %>      
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/style.css">
    <title><c:out value="${restaurant.restaurantName}" />'s Page</title>
</head>
<body>
<t:wrapper>
<h1><c:out value="${restaurant.restaurantName}" /></h1>
<img src="${restaurant.restaurantPic}" alt="Admin" class="profile">
				<form method="POST" action="/upload/restaurant/${restaurant.id}" enctype= "multipart/form-data">
					<div class="form-data"><input type="file" name="picture"></div>
					<button class="btn btn-primary">Update Dish Picture</button>
				</form>

				<h3>Restaurant Dishes</h3>
				 <h4><c:forEach items="${restaurant.restaurantDishes}" var="dish"><img src="${dish.dishPic}" alt="dish" class="icon"><a href="/dish/${dish.id}">${dish.dishName}</a>
				 				<c:choose>
								<c:when test="${dish.favoritors.contains(sessionUser)}"><a class="gold" href="/dish/unfavorite/${dish.id}"> ★ </a> 
								</c:when>
								<c:otherwise><a href="/dish/favorite/${dish.id}">☆</a> 
								</c:otherwise>
								</c:choose>	<br></c:forEach></h4>

				<c:forEach items="${restaurant.restaurantDishes}" var="restaurant">
					
					<p class="right">${dish.dishName} <span class="yourname">

					</span>
					</p>
					
				</c:forEach>
				<h3>Add a Dish</h3>
				<form:form method="POST" action="/restaurant/dish/${restaurant.id}" modelAttribute="dish">
					<div class="form-group">
						<form:label path="dishName">Dish Name</form:label>
		        <form:errors path="dishName"/>
		        <form:input class="form-control" path="dishName"/>
						<form:hidden value="/images/generic_dish.jpg" path="dishPic"/>
						<button>Submit</button>
					</div>
				</form:form>

</t:wrapper>
</body>
</html>