<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<c:url value="/resources/css/" var="cssPath" />

<tags:pageTemplate titulo="Cadastro de conta ">

	<jsp:attribute name="extraScripts">
	<script
			src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/js/standalone/selectize.js"></script>
	<script>
		$('#usuario, #cliente').selectize({
			create : false,
			sortField : 'text',
			placeholder : "Informe o cliente..."
		});
	</script>
	<link rel="stylesheet" href="${cssPath }selectize.default.css">
	</jsp:attribute>

	<jsp:body>
		<h1>Nova Conta</h1>

		<form:form action="${s:mvcUrl('AC#cadastroConta').build() }"
			method="POST" class="">
			<div class="row">
					<div class="form-group px-2 col-12 col-md-6">
				<label for="usuario">Usuario</label> 
				
				<select id="usuario"
						name="usuario.id"  class="form-control">
					<c:forEach items="${colaboradoresDisponiveis}" var="colaborador">
						<option value="${colaborador.id }">${colaborador.nome }</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group px-2 col-12 col-md-6">
				<label for="cliente">Cliente</label> <select id="cliente"
						name="cliente.id" class="form-control">
					<c:forEach items="${clientesDisponiveis}" var="cliente">
						<option value="${cliente.id }">${cliente.nome }</option>
					</c:forEach>
				</select>
			</div>
			</div>
	
			<button type="submit" class="btn btn-primary">Cadastrar Conta</button>

		</form:form>
	</jsp:body>
</tags:pageTemplate>