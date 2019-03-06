<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
	<c:if test="${conta.situacao == 'ATIVA' }">
		<form:form action="${s:mvcUrl('CC#cadastrarMovimentacao').build() }"
			method="post"}>
			<div>
				<label for="descricao">Descricao</label> <input type="text"
					id="descricao" name="movimentacao.descricao" required />
			</div>
			<div>
				<label for="valor">Valor</label> <input type="text" id="valor"
					name="movimentacao.valor" required />
			</div>
			<input type="hidden" name="conta.id" value="${conta.id }" />

			<button type="submit">Cadastrar Movimentacao</button>

		</form:form>
	</c:if>
	<table>
		<thead>
			<tr>
				<td>Tipo</td>
				<td>Descrição</td>
				<td>Valor</td>
				<td>Conciliada</td>
				<td>Editar</td>
				<td>Excluir</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${conta.movimentacoes }" var="movimentacao">
				<tr>
					<form:form action="${s:mvcUrl('CC#editarMovimentacao').build() }"
						method="POST">
						<input type="hidden" name="movimentacao.id"
							value="${movimentacao.id }">
						<td>${movimentacao.tipo }</td>
						<td><input type="text" name="movimentacao.descricao"
							value="${movimentacao.descricao }" /></td>
						<td><input type="text" name="movimentacao.valor"
							value="${movimentacao.valor }" /></td>
						<td>${movimentacao.conciliada }</td>
						<input type="hidden" name="contaId" value="${conta.id }" />

						<!-- TODO impolementar metodo para verificar se a movimentação pode ser editada
									separado da view -->
						<td><button type="submit"
								<c:if test="${movimentacao.conciliada == 'CONCILIADA' 
											|| movimentacao.cadastradoPor == 'ADMIN'
											|| conta.situacao == 'INATIVA'}">disabled</c:if>>Editar</button></td>
					</form:form>
					<td><form:form
							action="${s:mvcUrl('CC#excluirMovimentacao').build() }"
							method="POST">
							<input type="hidden" name="contaId" value="${conta.id }" />
							<input type="hidden" name="movimentacao.id"
								value="${movimentacao.id }">
							<button
								<c:if test="${movimentacao.conciliada == 'CONCILIADA' 
											|| movimentacao.cadastradoPor == 'ADMIN'
											|| conta.situacao == 'INATIVA'}">disabled</c:if>
								type="submit">Excluir</button>
						</form:form></td>

				</tr>
			</c:forEach>
		</tbody>

		<tfoot>
			<tr>
				<td colspan="2">total</td>
				<td>${conta.saldoDisponivel }</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>