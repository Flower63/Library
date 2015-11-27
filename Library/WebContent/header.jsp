<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.epam.library.Student" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.epam.library.properties.language" />

<!DOCTYPE html>
<html>
	<body>
		<fmt:message key="login.label.lang" var="langName"></fmt:message>
	
		<table style="float: right; margin-right: 3px">
			<tr>
				<td>
					<form action="login.jsp">
						<input type="submit" value="Login">
					</form>
				</td>
				<td>
					<form>
						<input type="hidden" name="language" value="${langName}">
						<input type="submit" value="${langName}">
					</form>
				</td>
			</tr>
		</table>
		
		${not empty student ? student.getFirstName() : 'bob'}
		
		<br>
		
<!--	<form>
			<select id="language" name="language" onchange="submit()">
    			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
				<option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
			</select>
		</form> -->
		
<!--	<form>
			<label for="username"><fmt:message key="login.label.username" />:</label>
			<input type="text" id="username" name="username">
			<br>
			<label for="password"><fmt:message key="login.label.password" />:</label>
			<input type="password" id="password" name="password">
			<br>
			<fmt:message key="login.button.submit" var="buttonValue" />
			<input type="submit" name="submit" value="${buttonValue}">
		</form> -->
	</body>
</html>