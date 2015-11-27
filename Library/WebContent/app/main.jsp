<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="ua.epam.library.Student" %>
<!DOCTYPE html>
<html>
<head>
<title>Main page so far</title>
</head>
<body>
<% 
	Student student = (Student) session.getAttribute("student");
	if (student != null) {
		out.println("<p>" + student.getFirstName() + "</p>");
		out.println("<p>" + student.getLastName() + "</p>");
	} else {
		out.println("you shouldn't be here");
	}
%>

<br>

<%@ include file="/header.jsp" %>

<form action="LogoutServlet" method="POST">
	<input type="submit" value="Logout">
</form>
<form action="my_books.jsp" method="POST">
	<input type="submit" value="My books">
</form>
<form action="book_shelf.jsp" method="POST">
	<input type="submit" value="Book shelf">
</form>
<%  
	if (student.isLabrerian()) {
		out.println("<form action=\"orders.jsp\" method=\"POST\">" + 
						"<input type=\"submit\" value=\"Orders\">" +
					"</form>");
	}
%>
</body>
</html>