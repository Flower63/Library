<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Book shelf</title>
<style>
h1, h3, li, ol {
text-align: center;
}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>
<form action="MyLibraryController" method="POST">
	<input type="hidden" name="request" value="main">
	<button type="submit"><fmt:message key="menu.main"/></button>
</form>

<c:if test="${not empty error}">
	<h3 style="text-align: center; color:red;"><fmt:message key="${error}"/></h3>
</c:if>

<h1><fmt:message key="library"/></h1>
<h3><fmt:message key="menu.search"/></h3>

<form style="text-align: center" action="MyLibraryController" method="POST">
	<input type="hidden" name="request" value="search">
	<input type="text" name="subject" value="${sub}">
	<button type="submit"><fmt:message key="menu.search"/></button>
</form>

<table style="width:100%">
	<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.name}</td>
			<td>${book.author}</td>
			<td>${book.year}</td>
			<td>${book.quantity} <fmt:message key="book.left"/></td>
			<td>
				<form method="POST">
				<input type="hidden" name="request" value="order">
				<button type="submit" name="book" value="${book.id}" ${book.quantity == 0 ? 'disabled' : ''}><fmt:message key="book.order"/></button>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>