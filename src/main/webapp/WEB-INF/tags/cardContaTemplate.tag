<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ attribute name="contas" required="true" type="java.util.List"%>
<%@ attribute name="metodoUrl" required="true"%>
<!-- CC#conta -->


<c:forEach items="${contas }" var="conta" varStatus="loop">

		<c:if test="${loop.index % 2 == 0 }">

			<div class="row">
		</c:if>
		<div class="col-12 col-sm-6 mb-3 ">
			<a class="w-100 h-100 "
				href="${s:mvcUrl(metodoUrl).arg(0, conta.id).build() }">
				<div class="w-100 mr-0 card  border-primary  h-100">
					<div class="card-header d-flex justify-content-between">
						<h5 >${conta.cliente.nome}</h5> <span>${conta.situacao }</span>
					</div>
					<div class="card-body">

						<p class="card-text">Data de inicio: ${conta.dataInicio }</p>
						<p class="card-text">Data de fim: ${conta.dataFim }</p>
						<p class="card-text">Saldo: ${conta.saldoDisponivel } </p>
					</div>
				</div>
			</a>
		</div>
		<c:if test="${loop.index % 2 == 1 || loop.last }">

			</div>

		</c:if>


	</c:forEach>