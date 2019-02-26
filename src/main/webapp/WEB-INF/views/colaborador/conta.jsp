<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Conta</title>
</head>
<body>

	<h1>Conta ${conta.cliente.nome }</h1>


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
	<form action="${s:mvcUrl('CC#cadastrarMovimentacao').build() }" method="post" }>
		<div>
			<label for="descricao">Descricao</label> <input type="text" id="descricao"
				name="movimentacao.descricao" required />
		</div>
		<div>
			<label for="valor">Valor</label> <input type="text" id="valor"
				name="movimentacao.valor" required />
		</div>
		<input type="hidden" name="conta.id" value="${conta.id }"/>
		
		<button type="submit">Cadastrar Movimentacao</button>
		
	</form>
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
	<!-- 	TODO formulario de cadastro de movimentação -->


	<!-- 	TODO mostrar todos as movimentações ja cadastradas -->



</body>
</html>