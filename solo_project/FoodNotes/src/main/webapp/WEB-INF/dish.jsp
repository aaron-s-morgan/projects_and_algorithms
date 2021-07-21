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
    <title><c:out value="${dish.dishName}" />'s Page</title>
</head>
<body>
<t:wrapper>
<h1><c:out value="${dish.dishName}"/> from <c:out value="${dish.server.restaurantName}"/></h1>
<img src="${dish.dishPic}" alt="Admin" class="profile">


				<h3>Dish Notes</h3>
				 <h4><c:forEach items="${dish.dishNoted}" var="note">${note.content} - <a href="/profile/${note.author.id}">${note.author.firstName} ${note.author.lastName}</a><br></c:forEach></h4>
							

				<form action="/dish/note/${dish.id}" method="post">
					<div class="form-group">
						<label for="dish">Add Note</label>
						<span>${error}</span>
						<textarea name="content" id="content" class="form-control"></textarea>
						<button>Submit</button>
					</div>
				</form>
				<form method="POST" action="/upload/dish/${dish.id}" enctype= "multipart/form-data">
					<div class="form-data"><input type="file" name="picture"></div>
					<button class="btn btn-primary">Update Dish Picture</button>
				</form>
</t:wrapper>
</body>
</html>