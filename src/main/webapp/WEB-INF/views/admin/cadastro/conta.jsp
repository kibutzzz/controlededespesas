<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Cadastro de conta ">


	<h1>Nova Conta</h1>

	<form:form action="${s:mvcUrl('AC#cadastroConta').build() }"
		method="POST">
		<div>
			<label for="usuario">Usuario</label> <select id="usuario"
				name="usuario.id">
				<c:forEach items="${colaboradoresDisponiveis}" var="colaborador">
					<option value="${colaborador.id }">${colaborador.nome }</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<label for="cliente">Cliente</label> <select id="cliente"
				name="cliente.id">
				<c:forEach items="${clientesDisponiveis}" var="cliente">
					<option value="${cliente.id }">${cliente.nome }</option>
				</c:forEach>
			</select>
		</div>



		<button type="submit">Cadastrar Conta</button>

	</form:form>
</tags:pageTemplate>