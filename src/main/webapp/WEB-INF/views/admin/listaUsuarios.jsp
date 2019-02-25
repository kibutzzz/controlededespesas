<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib uri="http://www.springframework.org/tags" prefix="s"%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Usuarios</title>
</head>
<body>

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
</html>