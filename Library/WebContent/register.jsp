<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<style>
form, h3 {
text-align: center;
}

h4 {
text-align: center;
color: red;
}
</style>
</head>
<body>
<% if (request.getAttribute("cause") != null) {
	out.println("<h4>" + request.getAttribute("cause") + "</h4>");
} %>
<form action="RegisterServlet" method="post">
<h3>Your ID</h3>
<input type="text" name="student_id">
<br>
<h3>Your first name</h3>
<input type="text" name="first_name">
<br>
<h3>Your last name</h3>
<input type="text" name="last_name">
<br>
<h3>Your password</h3>
<input type="password" name="pwd1">
<br>
<h3>Your password again</h3>
<input type="password" name="pwd2">
<br>
<br>
<input type="submit" value="Register">
</form>
</body>
</html>