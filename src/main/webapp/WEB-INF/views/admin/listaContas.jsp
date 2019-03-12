<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<c:url value="/" var="contextPath" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Contas</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="${contextPath }resources/css/bootstrap.min.css">

</head>
<body>

	<%@ include file="/WEB-INF/views/admin/templates/nav.jsp" %>

	<h1>Lista de Contas cadastradas no sistema</h1>
	<c:if test="${not empty statusCadastro }">
		<p>${statusCadastro}</p>
	</c:if>
	<table>
		<thead>
			<tr>
				<td>Cliente</td>
				<td>Colaborador</td>
				<td>Data de Inicio</td>
				<td>Situação</td>
				<td>detalhes</td>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${contas}" var="conta">
				<tr>
					<td>${conta.cliente.nome}</td>
					<td>${conta.usuario.nome}</td>
					<td>${conta.dataInicio}</td>
					<td>${conta.situacao}</td>
					<td><a href="${s:mvcUrl('AC#detalheConta').arg(0, conta.id).build() }">detalhes</a></td>
								 
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