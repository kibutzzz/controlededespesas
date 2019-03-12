
<%@ attribute name="titulo" required="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:url value="/" var="contextPath" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="theme-color" content="#1a1a1a">

<link rel="stylesheet"
	href="${contextPath }resources/css/bootstrap.min.css">

<title>${titulo }| controlededespesas</title>
</head>
<body>
	<%@ include file="../views/admin/templates/nav.jsp"%>
	<div class="container">
		<jsp:doBody />
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="${contextPath }resources/js/bootstrap.min.js"></script>

</html>
