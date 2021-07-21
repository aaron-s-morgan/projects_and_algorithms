<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags" %>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
<title>Homepage</title>
</head>
<body>
<t:wrapper>
	<h1><c:out value="${error}"/></h1>


	<h3>Here are some posted Restaurants:</h3>
          		<c:choose>
								<c:when test = "${sessionUser.darkMode == true}">
								<table class="table table-dark">
								</c:when>
								<c:otherwise>
    							<table class="table table-light">
								</c:otherwise>
				</c:choose>
				<thead>
				<tr>
					<td>Restaurant</td>
					<td>Created By</td>
					<td>Likes</td>
					<td>Action</td>
				</tr>
				</thead>
				<tbody>
					<c:forEach items ="${allRestaurants}" var ="restaurant">
						<tr>
							<td><a href="/restaurant/${restaurant.id}"><img src="${restaurant.restaurantPic}" alt="Admin" class="profile"> ${restaurant.restaurantName}</a></td>
							<td>${restaurant.cuisine}</td>
							<td>${restaurant.likers.size()}</td>
							<td>
								<c:choose>
								<c:when test="${restaurant.likers.contains(sessionUser)}"><a href="/restaurant/unlike/${restaurant.id}">Unlike</a> 
								</c:when>
								<c:otherwise><a href="/restaurant/like/${restaurant.id}">Like</a> 
								</c:otherwise>
								</c:choose>	
							</td>							
						</tr>
					</c:forEach>	
				</tbody>
			</table>

			<hr>
						<h3>Your Feed</h3>
<table class="table table-dark">
				<thead>
				<tr>
					<td>User</td>
					<td>Notes Posted</td>
					<td>Followers</td>
					<td>Favorite Dishes</td>

				</tr>
				</thead>
				<tbody>
					<c:forEach items ="${allUsers}" var ="user">
					<c:choose>
					<c:when test="${sessionUser.following.contains(user)}">
						<tr>
							<td><a href="/profile/${user.id}"><img src="${user.profilePic}" alt="Admin" class="profile">${user.firstName} ${user.lastName}</a></td>
							<td>${user.notesPosted.size()}</td>
							<td>${user.followers.size()}</td>
							<td> <h4><c:forEach items="${user.favoriteDishes}" var="dish"><a href="/dish/${dish.id}"> ${dish.dishName} </a> from <a href="${dish.server.id}">${dish.server.restaurantName} </a><br></c:forEach></h4></td>							
						</tr>
					</c:when>
													<c:when test = "${event.host.id == user.id}">
            					<a href="/events/delete/${event.id}">Delete</a> | <a href="/events/edit/${event.id}">Edit</a>
         						</c:when>
								<c:when test="${event.attendees.contains(user)}"><a href="/events/unjoin/${event.id}">Leave</a> 
								</c:when>
								<c:otherwise>
								</c:otherwise>
								</c:choose>	
					</c:forEach>	
				</tbody>
			</table>
						
			<h3>Some users you may like:</h3>
			<c:forEach items ="${allUsers}" var ="user">
			<c:choose>
			<c:when test="${sessionUser.id == user.id}">
			<br>
			</c:when>
			<c:when test="${sessionUser.following.contains(user)}">
			
			</c:when>
			<c:otherwise><a href="/profile/${user.id}"><img src="${user.profilePic}" alt="Admin" class="profile">${user.firstName} ${user.lastName}</a><p>     </p></c:otherwise>
			</c:choose>
			</c:forEach>
</t:wrapper>
</body>
</html>