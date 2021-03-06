<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
</head>
<body>
	<p><a href="${pageContext.request.contextPath}/offers">Show offers</a></p>
	<p><a href="${pageContext.request.contextPath}/createOffer">Add a new offer</a></p>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<p><a href="<c:url value='/admin'/>">Admin</a></p>	
	</sec:authorize>
	
		<sec:authorize access="!isAuthenticated()">
		<p><a href="<c:url value='/login'/>">Log In</a></p>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<p><a href="<c:url value='/j_spring_security_logout'/>">Log Out</a></p>
	</sec:authorize>
</body>
</html>