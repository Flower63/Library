<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Main page so far</title>
</head>
<body>

<br>

<%@ include file="/header.jsp" %>

${reader.firstName}

<table>
	<tr>
		<td>
			<form action="MyLibraryController" method="POST">
				<input type="hidden" name="request" value="logout">
				<button type="submit"><fmt:message key="logout"/></button>
			</form>
		</td>

		<td>
			<form action="MyLibraryController" method="POST">
				<input type="hidden" name="request" value="my_books">
				<button type="submit"><fmt:message key="my_books"/></button>
			</form>
		</td>
	
		<td>
			<form action="MyLibraryController" method="POST">
				<input type="hidden" name="request" value="book_shelf">
				<button type="submit"><fmt:message key="book_shelf"/></button>
			</form>
		</td>

		<td>
			<c:if test="${ reader.librerian }">
				<form action="MyLibraryController" method="POST">
					<input type="hidden" name="request" value="orders">
					<button type="submit"><fmt:message key="orders"/></button>
				</form>
			</c:if>
		</td>
	</tr>
</table>
</body>
</html>