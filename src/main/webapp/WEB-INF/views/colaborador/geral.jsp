<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina do Colaborador | controle de despesas</title>
</head>
<body>

	<h1>Pagina do colaborador</h1>
	
<!-- 	TODO mostrar todas as contas e informacoes gerais do usuario -->

	<c:forEach items="${contas }" var="conta">
		<div>
			<a href="${s:mvcUrl('CC#conta').arg(0, conta.id).build() }">
				<h2>${conta.cliente.nome }</h2>
				<p>Data de inicio: ${conta.dataInicio }</p>
				<p>Data de fim: ${conta.dataFim }</p>
			</a>
		</div>

	</c:forEach>

</body>
</html>