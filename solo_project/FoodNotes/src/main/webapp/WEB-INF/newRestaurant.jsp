<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags" %>    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" integrity="undefined" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Add a Restaurant</title>
</head>
<body>
<t:wrapper>
		    <h1>Create a Restaurant</h1>
        <p><form:errors path="restaurant.*"/></p>
    
    <form:form method="POST" action="/restaurant/new" modelAttribute="restaurant">
    <form:hidden value="${user.id}" path="creator"/>
  
        
        <p>
            <form:label path="restaurantName">Restaurant Name</form:label>
            <form:input type="restaurantName" path="restaurantName"/>
        </p>
                <p>
            <form:label path="cuisine">Cusine</form:label>
            <form:input type="cuisine" path="cuisine"/>
        </p>
        <form:hidden value="/images/generic_restaurant.png" path="restaurantPic"/>    
        <input type="submit" value="Create"/>
    </form:form>
</t:wrapper>
</body>
</html>