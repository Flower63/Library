<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.epam.library.properties.language" />

<!DOCTYPE html>
<html>
	<body>
		<fmt:message key="login.label.lang" var="langName" />
	
		<table style="float: right; margin-right: 3px">
			<tr>
				<td>
					<form method="post">
						<input type="hidden" name="from" value="${pageContext.request.requestURI}" />
						<input type="hidden" name="language" value="${langName}" />
						<c:if test="${not empty req}">
							<input type="hidden" name="request" value="${req}">
						</c:if>
						<input type="submit" value="${langName}">
					</form>
				</td>
			</tr>
		</table>
		<br>
	</body>
</html>