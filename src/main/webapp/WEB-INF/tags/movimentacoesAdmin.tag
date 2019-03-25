<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ attribute name="conta" required="true"
	type="br.inf.safetech.model.ContaDespesa"%>


<c:forEach items="${conta.movimentacoes }" var="movimentacao">
	<div class="row d-lg-flex col-12 m-0 p-0">
		<form:form cssClass="d-lg-flex col-12 p-0 col-lg-11 mt-1"
			action="${s:mvcUrl('AC#editarMovimentacao').build() }" method="POST">


			<input type="hidden" name="movimentacao.id"
				value="${movimentacao.id }">

			<div class="col-sm-12 col-lg-2	 p-0 ">
				<select class="form-control px-2" name="movimentacao.tipo">
					<c:forEach items="${tipos }" var="tipo">
						<option value="${tipo }"
							<c:if test="${ tipo == movimentacao.tipo }">
										selected 
										</c:if>>${tipo }</option>
					</c:forEach>

				</select>
			</div>

			<div class="col-sm-12 col-lg-3 p-0">
				<input class="form-control-plaintext px-2"
					name="movimentacao.descricao" type="text"
					value="${movimentacao.descricao }" />
			</div>
			<div class="col-sm-12 col-lg-2 p-0">
				<input class="form-control-plaintext px-2" name="movimentacao.valor"
					type="text" value="${movimentacao.valor}" />
			</div>


			<div class="col-sm-12 col-lg-2 p-0">
				<select class="form-control px-2" name="movimentacao.conciliada">
					<c:forEach items="${conciliacao }" var="conciliada">
						<option value="${conciliada }"
							<c:if test="${ conciliada == movimentacao.conciliada }">
										selected 
										</c:if>>${conciliada }</option>
					</c:forEach>

				</select>
			</div>
			<div class="col-sm-12 col-lg-2 p-0">
				<select class="form-control px-2" name="movimentacao.categoria">
					<c:forEach items="${categorias }" var="categoria">
						<option value="${categoria }"
							<c:if test="${ categoria == movimentacao.categoria }">
										selected 
										</c:if>>${categoria }</option>
					</c:forEach>

				</select>
			</div>
			<input type="hidden" name="contaId" value="${conta.id }" />

			<div class="col-sm-12 col-lg-1 p-0 ">
				<button class="btn btn-block btn-warning" type="submit">
					<span class="oi oi-pencil"></span>
				</button>
			</div>
		</form:form>
		<form:form cssClass="col-lg-1 col-12 p-0 mt-1"
			action="${s:mvcUrl('AC#excluirMovimentacao').build() }" method="POST">
			<input type="hidden" name="contaId" value="${conta.id }" />
			<input type="hidden" name="movimentacao.id"
				value="${movimentacao.id }">
			<div class="col-sm-12   p-0 ">
				<button class="btn btn-danger btn-block" type="submit">
					<span class="oi oi-trash"></span>
				</button>

			</div>
		</form:form>

	</div>
</c:forEach>


<div class="row mt-2">
	<c:if test="${conta.situacao == 'ATIVA' }">
		<div class="col-12 col-lg-3 mr-auto">

			<form:form cssClass=""
				action="${s:mvcUrl('AC#confirmarEncerramento').build() }">
				<input type="hidden" name="id" value="${conta.id }">

				<button class="btn btn-block btn-danger"
					<c:if test="${not conta.movimentacoesEstaoConciliadas }">disabled</c:if>>Encerrar
					conta</button>

			</form:form>
		</div>
	</c:if>
	<div
		class="col-12 col-lg-3 form-control font-weight-bold d-flex justify-content-between">
		<div>Total:</div>
		<div>${conta.saldoDisponivel }</div>
	</div>
</div>