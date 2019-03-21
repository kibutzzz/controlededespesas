<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Encerramento de conta">

	<div class="card-header">
		<h2>Encerramento da conta</h2>

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
				<p class="card-text font-weight-bold">Saldo restante:
					${conta.saldoDisponivel }</p>
				<p class="card-text ">
					<form:form action="${s:mvcUrl('AC#encerrarConta').build() }"
						method="POST">
						<c:if test="${ !conta.saldoDisponivel.equals('0.00')}">

							<div class="form-group">
								<div class="custom-control custom-radio">
									<input type="radio" id="vale" name="formaDeEncerramento"
										class="custom-control-input" value="vale" > <label
										class="custom-control-label" for="vale">Vale</label>
								</div>
								<div class="custom-control custom-radio">
									<input type="radio" id="devolucao" name="formaDeEncerramento"
										class="custom-control-input" checked value="devolucao"> <label
										class="custom-control-label" for="devolucao">Devolução</label>
								</div>
								
							</div>
							
						</c:if>

						<input type="hidden" name="conta.id" value="${conta.id }">
						<button class="btn btn-primary" type="submit">Encerrar conta</button>
					</form:form>
				</p>
			</div>

		</div>
	</div>



</tags:pageTemplate>

