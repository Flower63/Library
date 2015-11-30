<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<title>Library</title>
<style>
h1, form {
text-align: center;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<%@ include file="user_redirect.jsp" %>
<h1><fmt:message key="welcome" /></h1>
<form action="login.jsp" method="POST">
	<button type="submit"><fmt:message key="signIn" /></button>
</form>
<form action="register.jsp" method="POST">
	<button type="submit"><fmt:message key="signUp" /></button>
</form>
</body>
</html>