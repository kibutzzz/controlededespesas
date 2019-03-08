<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de conta | controle de despesas</title>
</head>
<body>

	<h1>Encerramento de conta</h1>

	<span>Informações da conta</span>
	<h2>Saldo restante: ${conta.saldoDisponivel }</h2>

	<form:form action="${s:mvcUrl('AC#encerrarConta').build() }"
		method="POST">
		<c:if test="${ conta.saldoDisponivel != 0}">
			<input type="radio" name="formaDeEncerramento" value="vale">Vale<br>
			<input type="radio" name="formaDeEncerramento" checked value="devolucao">Devolução<br>
		</c:if>

		<input type="hidden" name="conta.id" value="${conta.id }">
		<button type="submit">Encerrar conta</button>
	</form:form>
</body>
</html>