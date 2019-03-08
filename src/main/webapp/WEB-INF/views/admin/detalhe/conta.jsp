<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Area administrativa</title>
</head>
<body>

	<h1>Conta</h1>
	<c:if test="${not empty status }">${status }</c:if>

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
		<form:form action="${s:mvcUrl('AC#cadastrarMovimentacao').build() }"
			method="post">
			<div>
				<label for="descricao">Descricao</label> <input type="text"
					id="descricao" name="movimentacao.descricao" required />
			</div>
			<div>
				<label for="valor">Valor</label> <input type="text" id="valor"
					name="movimentacao.valor" required />
			</div>
			<div>
				<label for="tipo">Tipo</label> <select id="tipo"
					name="movimentacao.tipo">
					<c:forEach items="${tipos }" var="tipo">
						<option value="${tipo }">${tipo }</option>
					</c:forEach>
				</select>
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
				<td>Categoria</td>

				<td>Editar</td>
				<td>Excluir</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${conta.movimentacoes }" var="movimentacao">
				<tr>
					<form:form action="${s:mvcUrl('AC#editarMovimentacao').build() }"
						method="POST">
						<input type="hidden" name="movimentacao.id"
							value="${movimentacao.id }">
						<td><select name="movimentacao.tipo">
								<c:forEach items="${tipos }" var="tipo">
									<option value="${tipo }"
										<c:if test="${ tipo == movimentacao.tipo }">
										selected 
										</c:if>>${tipo }</option>
								</c:forEach>

						</select></td>
						<td><input name="movimentacao.descricao" type="text"
							value="${movimentacao.descricao }" /></td>
						<td><input name="movimentacao.valor" type="text"
							value="${movimentacao.valor}" /></td>

						<td><select name="movimentacao.conciliada">
								<c:forEach items="${conciliacao }" var="conciliada">
									<option value="${conciliada }"
										<c:if test="${ conciliada == movimentacao.conciliada }">
										selected 
										</c:if>>${conciliada }</option>
								</c:forEach>

						</select></td>
						<td><select name="movimentacao.categoria">
								<c:forEach items="${categorias }" var="categoria">
									<option value="${categoria }"
										<c:if test="${ categoria == movimentacao.categoria }">
										selected 
										</c:if>>${categoria }</option>
								</c:forEach>

						</select></td> <input type="hidden" name="contaId" value="${conta.id }" />

						<td><button
								<c:if test="${movimentacao.conciliada == 'CONCILIADA' }">disabled</c:if>
								type="submit">Editar</button></td>
					</form:form>
					<td><form:form
							action="${s:mvcUrl('AC#excluirMovimentacao').build() }"
							method="POST">
							<input type="hidden" name="contaId" value="${conta.id }" /> <input
								type="hidden" name="movimentacao.id" value="${movimentacao.id }">
							<button
								<c:if test="${movimentacao.conciliada == 'CONCILIADA' }">disabled</c:if>
								type="submit">Excluir</button>
						</form:form></td>


				</tr>
			</c:forEach>

		</tbody>

		<tfoot>
			<tr>
				<td colspan="2">total</td>
				<td>${conta.saldoDisponivel }</td>
				<td><form action="${s:mvcUrl('AC#encerrarConta').build() }">
						<input type="hidden" name="id" value="${conta.id }">
						<c:if test="${conta.situacao == 'ATIVA' }">
						<button
							<c:if test="${not conta.movimentacoesEstaoConciliadas }">disabled</c:if>>Encerrar
							conta</button>
							</c:if>
					</form></td>
			</tr>
		</tfoot>
	</table>

</body>
</html>