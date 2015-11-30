<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<body>
<c:if test="${not empty reader}">
	<c:redirect url="app/main.jsp"/>
</c:if>
</body>
</html>