<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>My books</title>
</head>
<body>
<%@ include file="/header.jsp" %>
<form action="MyLibraryController" method="POST">
	<input type="hidden" name="request" value="main">
	<button type="submit"><fmt:message key="menu.main"/></button>
</form>
<table style="width:100%">
	<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.key.name}</td>
			<td>${book.key.author}</td>
			<td>${book.key.year}</td>
			<td>
				<c:if test="${!book.value}">
					<fmt:message key="book.not.approved"/>
				</c:if>
				<c:if test="${book.value}">
					<form method="POST">
						<input type="hidden" name="request" value="return">
						<input type="hidden" name="book" value="${book.key.id}">
						<button type="submit"><fmt:message key="book.return"/></button>
					</form>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>