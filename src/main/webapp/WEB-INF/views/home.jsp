<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Controle de despesas</title>
</head>
<body>

	<h1>Home</h1>
	
	
	<a href="${s:mvcUrl('CC#usuarioOverview').build() }">Área do Usuário</a>
	<a href="${s:mvcUrl('AC#adminOverview').build() }">Área Administrativa</a>

</body>
</html>