<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Detalhe da conta ${conta.cliente.nome }">
	<div class="card border-dark mb-3">
		<%@include file="../../../views/templates/contaCardHeader.jsp"%>
		<div class="card-body pt-2">
			<div class="row ">


				<c:if test="${conta.situacao == 'ATIVA' }">
					<h4 class="col-12">Nova movimentação</h4>
					<form:form class="col-12"
						action="${s:mvcUrl('AC#cadastrarMovimentacao').build() }"
						method="post">
						<fieldset>
							<div class="form-group row">

								<div class="col-12 col-md-8">
									<input autofocus type="text" class="form-control-plaintext px-2"
										id="descricao" name="movimentacao.descricao" required
										placeholder="descrição da movimentação">
								</div>

								<div class="col-12 col-md-4">
									<input type="text" class="form-control-plaintext px-2"
										id="valor" name="movimentacao.valor" required
										placeholder="R$ 0.00">
								</div>

							</div>

							<div class="form-group row">

								<div class="col-12 col-md-6">
									<select id="tipo" name="movimentacao.tipo" class="form-control">
										<c:forEach items="${tipos }" var="tipo">
											<option value="${tipo }">${tipo }</option>
										</c:forEach>
									</select>
								</div>
								<input type="hidden" name="conta.id" value="${conta.id }" />
								<div class="col-12 col-md-6">
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
							<td class="px-1">Conciliada</td>
							<td class="px-1">Categoria</td>

							<td class="px-1">Editar</td>
							<td class="px-1">Excluir</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${conta.movimentacoes }" var="movimentacao">
							<tr class="my-5">
								<form:form
									action="${s:mvcUrl('AC#editarMovimentacao').build() }"
									method="POST">
									<input type="hidden" name="movimentacao.id"
										value="${movimentacao.id }">
									<td class="py-0 px-1" ><select class="form-control px-2" name="movimentacao.tipo">
											<c:forEach items="${tipos }" var="tipo">
												<option value="${tipo }"
													<c:if test="${ tipo == movimentacao.tipo }">
										selected 
										</c:if>>${tipo }</option>
											</c:forEach>

									</select></td>
									<td class="py-0 px-1"><input class="form-control-plaintext px-2" name="movimentacao.descricao" type="text"
										value="${movimentacao.descricao }" /></td>
									<td class="py-0 px-1"><input class="form-control-plaintext px-2" name="movimentacao.valor" type="text"
										value="${movimentacao.valor}" /></td>

									<td class="py-0 px-1"><select class="form-control px-2"name="movimentacao.conciliada">
											<c:forEach items="${conciliacao }" var="conciliada">
												<option value="${conciliada }"
													<c:if test="${ conciliada == movimentacao.conciliada }">
										selected 
										</c:if>>${conciliada }</option>
											</c:forEach>

									</select></td>
									<td class="py-0 px-1"><select class="form-control px-2" name="movimentacao.categoria">
											<c:forEach items="${categorias }" var="categoria">
												<option value="${categoria }"
													<c:if test="${ categoria == movimentacao.categoria }">
										selected 
										</c:if>>${categoria }</option>
											</c:forEach>

									</select></td>
									<input type="hidden" name="contaId" value="${conta.id }" />

									<td class="py-0 px-1"><button class="btn btn-warning"
											<c:if test="${movimentacao.conciliada == 'CONCILIADA' }">disabled</c:if>
											type="submit">Editar</button></td>
								</form:form>
								<td class="py-0 px-1"><form:form
										action="${s:mvcUrl('AC#excluirMovimentacao').build() }"
										method="POST">
										<input type="hidden" name="contaId" value="${conta.id }" />
										<input type="hidden" name="movimentacao.id"
											value="${movimentacao.id }">
										<button class="btn btn-danger"
											<c:if test="${movimentacao.conciliada == 'CONCILIADA' }">disabled</c:if>
											type="submit">Excluir</button>
									</form:form></td>


							</tr>
						</c:forEach>

					</tbody>

					<tfoot>
						<tr>
							<td colspan="4">Total</td>
							<td>${conta.saldoDisponivel }</td>
							<td><form
									action="${s:mvcUrl('AC#confirmarEncerramento').build() }">
									<input type="hidden" name="id" value="${conta.id }">
									<c:if test="${conta.situacao == 'ATIVA' }">
										<button class="btn btn-danger"
											<c:if test="${not conta.movimentacoesEstaoConciliadas }">disabled</c:if>>Encerrar
											conta</button>
									</c:if>
								</form></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</tags:pageTemplate>