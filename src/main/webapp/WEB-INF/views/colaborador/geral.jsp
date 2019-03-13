<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Pagina do Colaborador">


	<h1>Pagina do colaborador</h1>

	<!-- 	TODO mostrar todas as contas e informacoes gerais do usuario -->

	<c:forEach items="${contas }" var="conta" varStatus="loop">

		<c:if test="${loop.index % 2 == 0 }">

			<div class="row">
		</c:if>
		<div class="col-12 col-sm-6 mb-3 ">
			<a class="w-100 h-100 " href="${s:mvcUrl('CC#conta').arg(0, conta.id).build() }">
				<div class="w-100 mr-0 card  border-primary  h-100" ">
					<div class="card-header"><h5>${conta.cliente.nome}</h5></div>
					<div class="card-body">
						
						<p class="card-text">Data de inicio: ${conta.dataInicio }</p>
						<p class="card-text">Data de fim: ${conta.dataFim }</p>
					</div>
				</div>
			</a>
		</div>
		<c:if test="${loop.index % 2 == 1 }">

			</div>

		</c:if>


	</c:forEach>
</tags:pageTemplate>