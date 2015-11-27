<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
	<% if (request.getAttribute("cause") != null) {
		out.println("<h4>" + request.getAttribute("cause") + " is incorrect!</h4>");
	} %>
	<form action="LoginServlet" method="post">
		<h3>Your ID</h3>
		<input type="text" name="student_id">
		<br>
		<h3>Your password</h3>
		<input type="password" name="pwd">
		<br>
		<br>
		<input type="submit" value="Login">
	</form>
</body>
</html>