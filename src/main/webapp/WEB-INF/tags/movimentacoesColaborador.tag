<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ attribute name="conta" required="true"
	type="br.inf.safetech.model.ContaDespesa"%>



<c:forEach items="${conta.movimentacoes }" var="movimentacao">

	<div class="row d-lg-flex col-12 m-0 p-0">
		<form:form cssClass="d-lg-flex col-12 p-0 col-lg-10"
			action="${s:mvcUrl('CC#editarMovimentacao').build() }" method="POST">


			<input type="hidden" name="movimentacao.id"
				value="${movimentacao.id }">

			<div class="col-sm-12 col-lg-3 p-0  ">
				<input class="form-control-plaintext px-2" disabled
					value="${movimentacao.tipo }">
			</div>
			<div class="col-sm-12 col-lg-4 p-0 ">
				<input type="text" name="movimentacao.descricao"
					value="${movimentacao.descricao }"
					class="form-control-plaintext px-2" />
			</div>
			<div class="col-sm-12 col-lg-3 p-0 ">
				<input type="text" name="movimentacao.valor"
					value="${movimentacao.valor }" class="form-control-plaintext px-2" />
			</div>


			<div class="col-sm-12 col-lg-2 p-0  ">
				<button type="submit" class="btn btn-block btn-warning"
					<c:if test="${movimentacao.conciliada == 'CONCILIADA' 
											|| movimentacao.cadastradoPor == 'ADMIN'
											|| conta.situacao == 'INATIVA'}">disabled</c:if>>Editar</button>
			</div>

			<input type="hidden" name="contaId" value="${conta.id }" />

		</form:form>
		<form:form cssClass="col-lg-2 col-12 p-0  "
			action="${s:mvcUrl('CC#excluirMovimentacao').build() }" method="POST">
			<input type="hidden" name="contaId" value="${conta.id }" />
			<input type="hidden" name="movimentacao.id"
				value="${movimentacao.id }">

			<div class="col-sm-12   p-0 m-">
				<button class="btn btn-danger btn-block"
					<c:if test="${movimentacao.conciliada == 'CONCILIADA' 
													|| movimentacao.cadastradoPor == 'ADMIN' 
													|| conta.situacao == 'INATIVA'}">disabled</c:if>
					type="submit">Excluir</button>
			</div>


		</form:form>
	</div>
</c:forEach>
<div class="d-flex justify-content-start justify-content-lg-end py-3 font-weight-bold">
<div class=" mr-1"> Total:</div>
	<div >R$${conta.saldoDisponivel }</div>
</div>



