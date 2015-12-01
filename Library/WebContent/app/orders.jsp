<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Orders</title>
</head>
<body>
	<%@ include file="/header.jsp" %>
	
	<c:if test="${!reader.librerian}">
		<c:redirect url="app/main.jsp"/>
	</c:if>
	
	<form action="MyLibraryController" method="POST">
		<input type="hidden" name="request" value="main">
		<button type="submit"><fmt:message key="menu.main"/></button>
	</form>
	
	<table style="width:100%">
		<c:forEach items="${orders}" var="order">
			<tr>
				<td>${order.bookId}</td>
				<td>${order.bookName}</td>
				<td>${order.readerFirstName}</td>
				<td>${order.readerLastName}</td>
				<td>
					<form method="POST">
					<input type="hidden" name="request" value="allow_order">
					<input type="hidden" name="book_id" value="${order.bookId}">
					<input type="hidden" name="reader" value="${order.readerEmail}">
					<button type="submit"><fmt:message key="book.allow"/></button>
					</form>
				</td>
				<td>
					<form method="POST">
					<input type="hidden" name="request" value="deny_order">
					<input type="hidden" name="book_id" value="${order.bookId}">
					<input type="hidden" name="reader" value="${order.readerEmail}">
					<button type="submit"><fmt:message key="book.deny"/></button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>