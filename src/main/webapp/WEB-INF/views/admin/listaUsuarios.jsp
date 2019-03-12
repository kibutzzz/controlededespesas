<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<c:url value="/" var="contextPath" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Usuarios</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="${contextPath }resources/css/bootstrap.min.css">

</head>
<body>
	<%@ include file="/WEB-INF/views/admin/templates/nav.jsp"%>

	<h1>Lista de Usuarios Cadastrados no sistema</h1>
	<c:if test="${not empty statusCadastro }">
		<p>${statusCadastro}</p>
	</c:if>
	<table>
		<thead>
			<tr>
				<td>Nome</td>
				<td>Login</td>
				<td>Senha</td>
				<td>Tipo</td>
				<td>Situação</td>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td>${usuario.nome}</td>
					<td>${usuario.login}</td>
					<td>${usuario.senha}</td>
					<td>${usuario.tipo}</td>
					<td>${usuario.situacao}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="${contextPath }resources/js/bootstrap.min.js"></script>
</html>