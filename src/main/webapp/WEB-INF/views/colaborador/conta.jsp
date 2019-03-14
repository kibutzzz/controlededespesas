<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Conta ${conta.cliente.nome }">

	<div class="card border-dark mb-3">
		<div class="card-header">
			<h2>Conta ${conta.cliente.nome }</h2>

		</div>

		<div class="card-body">
			<h4 class="card-title">Informações da conta</h4>
			<div class="row">
				<div class="col-12 col-lg-6">
					<p class="card-text">Cliente: ${conta.cliente.nome }</p>
					<p class="card-text">Colaborador: ${conta.usuario.nome }</p>
					<p class="card-text">Situação: ${conta.situacao }</p>
				</div>
				<div class="col-12 col-lg-6">
					<p class="card-text">Data de Inicio: ${conta.dataInicio }</p>
					<p class="card-text">
						Data de encerramento:
						<c:if test="${not empty conta.dataFim}">${conta.dataFim }</c:if>
					</p>
				</div>

			</div>
			<div class="row mt-5">

				<c:if test="${conta.situacao == 'ATIVA' }">
					<h4 class="col-12">Nova movimentação</h4>
					<form:form class="col-12"
						action="${s:mvcUrl('CC#cadastrarMovimentacao').build() }"
						method="post">
						<fieldset>
							<div class="form-group row">

								<div class="col-md-5">
									<input type="text" class="form-control-plaintext px-2"
										id="descricao" name="movimentacao.descricao" required
										placeholder="descrição da movimentação">
								</div>

								<div class="col-md-3">
									<input type="text" class="form-control-plaintext px-2"
										id="valor" name="movimentacao.valor" required
										placeholder="R$ 0.00">
								</div>
								<input type="hidden" name="conta.id" value="${conta.id }" />
								<div class=" col-md-4">
									<button type="submit" class="btn btn-block btn-primary">Cadastrar
										Movimentacao</button>
								</div>

							</div>
						</fieldset>

					</form:form>

				</c:if>

				<h2 class="col-12">Movimentações</h2>

			</div>

			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<td class="px-1">Tipo</td>
							<td class="px-1">Descrição</td>
							<td class="px-1">Valor</td>

							<td class="px-1">Editar</td>
							<td class="px-1">Excluir</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${conta.movimentacoes }" var="movimentacao">
							<tr>
								<form:form
									action="${s:mvcUrl('CC#editarMovimentacao').build() }"
									method="POST">
									<input type="hidden" name="movimentacao.id"
										value="${movimentacao.id }"
										class="form-control-plaintext px-2">
									<td class="py-0 px-1 align-middle">${movimentacao.tipo }</td>
									<td class="py-0 px-1"><input type="text"
										name="movimentacao.descricao"
										value="${movimentacao.descricao }"
										class="form-control-plaintext px-2" /></td>
									<td class="py-0 px-1"><input type="text"
										name="movimentacao.valor" value="${movimentacao.valor }"
										class="form-control-plaintext px-2" /></td>
									<!-- TODO expandir o tamanho disponivel para este campo -->
									<input type="hidden" name="contaId" value="${conta.id }" />

									<!-- TODO implementar metodo para verificar se a movimentação pode ser editada
									separado da view -->
									<td class="py-0 px-1"><button type="submit"
											class="btn btn-warning"
											<c:if test="${movimentacao.conciliada == 'CONCILIADA' 
											|| movimentacao.cadastradoPor == 'ADMIN'
											|| conta.situacao == 'INATIVA'}">disabled</c:if>>Editar</button></td>
								</form:form>
								<td class="py-0 px-1"><form:form
										action="${s:mvcUrl('CC#excluirMovimentacao').build() }"
										method="POST">
										<input type="hidden" name="contaId" value="${conta.id }" />
										<input type="hidden" name="movimentacao.id"
											value="${movimentacao.id }">
										<button class="btn btn-danger"
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
							<td colspan="4">total</td>
							<td>R$${conta.saldoDisponivel }</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>





</tags:pageTemplate>