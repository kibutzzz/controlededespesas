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
									<input autofocus type="text"
										class="form-control-plaintext px-2" id="descricao"
										name="movimentacao.descricao" required
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
			
			
			<tags:movimentacoesAdmin conta="${conta }"></tags:movimentacoesAdmin>
			<div class="row col-12">
				<div class="col-12">
					<form:form action="${s:mvcUrl('AC#gerarRelatorioGeral').build() }" method="post">
						<input type="hidden" name="contaId" value="${conta.id }"/>
						<button type="submit" class="btn btn-block btn-primary">Gerar Relatorio Administrativo</button>
					</form:form>
				</div>
				
				<div class="col-12">
				
				</div>
			</div>

		</div>
	</div>

</tags:pageTemplate>