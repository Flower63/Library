<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
	<style>
	form, h3 {
	text-align: center;
	}

	h4 {
	color: red;
	}
	</style>
</head>
<body>
	<%@ include file="header.jsp" %>
	${requestScope.error}
	<form action="MyLibraryController" method="post">
		<h3><fmt:message key="your_email"/></h3>
		<input type="text" name="email">
		<br>
		<h3><fmt:message key="your_password"/></h3>
		<input type="password" name="password">
		<br>
		<br>
		<input type="hidden" name="request" value="login">
		<button type="submit"><fmt:message key="signIn"/></button>
	</form>
</body>
</html>