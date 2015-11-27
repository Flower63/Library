<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<title>Library</title>
<style>
h1, form {
text-align: center;
}
</style>
<%
	if (session.getAttribute("student") != null) {
		response.sendRedirect("app/main.jsp");
	}
%>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="welcome"></fmt:message></h1>
<form action="login.jsp" method="POST">
	<button type="submit"><fmt:message key="signIn"></fmt:message></button>
</form>
<form action="register.jsp" method="POST">
	<button type="submit"><fmt:message key="signUp"></fmt:message></button>
</form>
</body>
</html>