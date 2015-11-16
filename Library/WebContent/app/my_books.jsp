<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ua.epam.library.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My books</title>
</head>
<body>
<form action="main.jsp" method="POST">
	<input type="submit" value="Main">
</form>
<%  Student student = (Student) session.getAttribute("student");
	if (student != null) {
		List<Book> books = LibraryModel.getStudentBooks(student.getStudentId());
		
		out.println("<ol>");
		
		for (Book book : books) {
			out.println("<li>" + book.getAuthor() + " " + book.getName() + " " + book.getYear() + "</li>");
		}
		
		out.println("</ol>");
		
	}
		%>
</body>
</html>