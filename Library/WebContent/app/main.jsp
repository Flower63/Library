<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ua.epam.library.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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