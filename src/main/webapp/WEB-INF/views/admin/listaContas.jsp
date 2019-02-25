<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib uri="http://www.springframework.org/tags" prefix="s"%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Contas</title>
</head>
<body>

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
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${contas}" var="conta">
				<tr>
					<td>${conta.cliente.nome}</td>
					<td>${conta.usuario.nome}</td>
					<td>${conta.dataInicio}</td>
					<td>${conta.situacao}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>