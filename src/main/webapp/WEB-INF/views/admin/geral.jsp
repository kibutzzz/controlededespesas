<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<c:url value="/" var="contextPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Area administrativa</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="${contextPath }resources/css/bootstrap.min.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/templates/nav.jsp" %>
	
	<div class="container ">
		<h1 class="mt-4">Area administrativa</h1>
		<c:if test="${not empty status }">${status }</c:if>

	</div>


	<!-- 	TODO desenvolver area administrativa  -->



</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>

<script type="text/javascript" src="${contextPath }resources/js/bootstrap.min.js"></script>
</html>