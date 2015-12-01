<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
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
	
		<%@ include file="/header.jsp" %>
		
		<c:if test="${not empty error}">
			<h3 style="text-align: center; color:red;"><fmt:message key="${error}"/></h3>
		</c:if>

		<form action="MyLibraryController" method="post">
			<h3><fmt:message key="login.your_email"/></h3>
			<input type="text" name="email">
			<br>
			<h3><fmt:message key="register.first_name"/></h3>
			<input type="text" name="first_name">
			<br>
			<h3><fmt:message key="register.last_name"/></h3>
			<input type="text" name="last_name">
			<br>
			<h3><fmt:message key="login.your_password"/></h3>
			<input type="password" name="password1">
			<br>
			<h3><fmt:message key="login.your_password_again"/></h3>
			<input type="password" name="password2">
			<br>
			<br>
			<input type="hidden" name="request" value="register">
			<button type="submit"><fmt:message key="menu.signUp"/></button>
		</form>
	</body>
</html>