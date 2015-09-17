<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Login Form</title>
 <!--  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css"> -->
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body onload="document.f.j_username.focus();">
  <c:if test="${param.error != null}">
  	<p class="error">Login Failed </p>
  </c:if>
  
  <form method="POST" name="f" action="${pageContext.request.contextPath}/j_spring_security_check" class="login">
    <p>
      <label for="login">Email:</label>
      <input type="text" name="j_username" id="login">
    </p>

    <p>
      <label for="password">Password:</label>
      <input type="password" name="j_password" id="password">
    </p>

    <p>
      <label for="rememberMe">Remember me:</label>
      <input type="checkbox" name="j_spring_security_remember_me" checked="checked" id="password">
    </p>
	
    <p class="login-submit">
      <button type="submit" name="submit" class="login-button">Login</button>
    </p>

    <p class="forgot-password"><a href="${pageContext.request.contextPath}/login">Forgot your password?</a></p>
  </form>
  
  <p><a href="${pageContext.request.contextPath}/newAccount">Create New Account</a></p>
</body>
</html>