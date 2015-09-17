<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Offer</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body>
	<sf:form method="post" action="${pageContext.request.contextPath}/doCreate" commandName="offer">
		<table>
		<tr>
			<td>Name: </td>
			<td><sf:input name="name" type="text" path="name"/></td>
			<td><sf:errors path="name" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td>Email: </td>
			<td><sf:input name="email" type="text" path="email"/></td>
			<td><sf:errors path="email" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td>Text: </td>
			<td><sf:textarea name="text" rows="5" cols="10" path="text"></sf:textarea></td>
			<td><sf:errors path="text" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input name="submit" value="Submit" type="submit"/></td>
		</tr>
		</table>
	</sf:form>
</body>
</html>