<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Account</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body>
	<h3>Create Account:</h3>
	<sf:form method="post" action="${pageContext.request.contextPath}/createAccount" commandName="user">
		<table>
		<tr>
			<td>Name: </td>
			<td><sf:input name="username" type="text" path="username"/></td>
			<td><sf:errors path="username" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td>Email: </td>
			<td><sf:input name="email" type="text" path="email"/></td>
			<td><sf:errors path="email" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td>Password: </td>
			<td><sf:input name="password" rows="5" cols="10" type="password" path="password"/></td>
			<td><sf:errors path="password" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td>Confirm Password: </td>
			<td><input name="confirmPassword" type="password"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input name="submit" value="Create" type="submit"/></td>
		</tr>
		</table>
	</sf:form>
</body>
</html>