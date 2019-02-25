<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de conta | controle de despesas</title>
</head>
<body>

	<h1>Novo Conta</h1>

	<form action="${s:mvcUrl('AC#cadastroConta').build() }" method="POST">
		<div>
			<label for="usuario">Usuario</label> <select id="usuario"
				name="usuarioId">
				<c:forEach items="${colaboradoresDisponiveis}" var="colaborador">
					<option value="${colaborador.id }">${colaborador.nome }</option>
				</c:forEach>
			</select>
		</div>


		<div>
			<label for="cliente">Cliente</label> <select id="cliente"
				name="clienteId">
				<c:forEach items="${clientesDisponiveis}" var="cliente">
					<option value="${cliente.id }">${cliente.nome }</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for="dataInicio">Data de inicio</label> <input type="date"
				id="dataInicio" name="dataInicio" required />
		</div>
		<!-- 		TODO decidir se data de fim sera inclusa ao criar ou ao finalizar a conta -->

		<button type="submit">Cadastrar Conta</button>

	</form>
</body>
</html>