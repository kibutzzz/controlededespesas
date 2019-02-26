<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Area administrativa</title>
</head>
<body>

	<h1>Conta</h1>

	<table>
		<thead>
			<tr>
				<td>Cliente</td>
				<td>Colaborador</td>
				<td>Data de inicio</td>
				<td>Data de Fim</td>
				<td>Situação</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${conta.cliente.nome }</td>
				<td>${conta.usuario.nome }</td>
				<td>${conta.dataInicio }</td>
				<td><c:if test="${not empty conta.dataFim}">${conta.dataFim }</c:if>
				</td>
				<td>${conta.situacao }</td>
			</tr>

		</tbody>
	</table>

	<h1>Movimentações</h1>
	<table>
		<thead>
			<tr>
				<td>Tipo</td>
				<td>Descrição</td>
				<td>Valor</td>
				<td>Conciliada</td>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${conta.movimentacoes }" var="movimentacao">
				<tr>
					<td>${movimentacao.tipo }</td>
					<td>${movimentacao.descricao }</td>
					<td>${movimentacao.valor}</td>
					<td>${movimentacao.conciliada }</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>


	<!-- 	TODO desenvolver area administrativa  -->



</body>
</html>