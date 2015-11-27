<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="ua.epam.library.entity.*" %>
<%@ page import="ua.epam.library.*" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
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