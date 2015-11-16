<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<h1>Welcome</h1>
<h1>to</h1>
<h1>library</h1>
<form action="login.jsp" method="POST">
	<button type="submit">SIGN IN</button>
</form>
<form action="register.jsp" method="POST">
	<button type="submit">SIGN UP</button>
</form>
</body>
</html>