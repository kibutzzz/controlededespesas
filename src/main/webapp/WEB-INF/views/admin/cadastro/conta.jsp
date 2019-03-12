<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/" var="contextPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de conta | controle de despesas</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="${contextPath }resources/css/bootstrap.min.css">

</head>
<body>
	
	<%@ include file="/WEB-INF/views/admin/templates/nav.jsp" %>
	<h1>Novo Conta</h1>

	<form:form action="${s:mvcUrl('AC#cadastroConta').build() }" method="POST">
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

	</form:form >
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="${contextPath }resources/js/bootstrap.min.js"></script>

</html>