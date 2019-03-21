
<%@ attribute name="titulo" required="true"%>
<%@ attribute name="selectize" fragment="true"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

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
<link href="${contextPath }resources/open-iconic/font/css/open-iconic-bootstrap.css" rel="stylesheet">
<title>${titulo }| Controle de despesas</title>

<style rel="stylesheet">
* {
	transition: .3s ease-out;
}


body {
    overflow-y: scroll;
}
</style>
</head>
<body>
	<%@ include file="../views/templates/nav.jsp"%>
	<div class="container">
		<%@ include file="../views/templates/statusAlert.jsp"%>

		<jsp:doBody />
	</div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="${contextPath }resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(() => {
	setTimeout(() => {
		$("#alert-remove").fadeOut(300, () => $(this).remove());
	}, 3000);
});
</script>
<jsp:invoke fragment="selectize" />
</html>
