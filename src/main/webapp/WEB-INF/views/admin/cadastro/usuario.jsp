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
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
<link rel="stylesheet" href="${contextPath }resources/css/bootstrap.min.css">

<title>Cadastro de usuario | controle de despesas</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/templates/nav.jsp" %>
	
	<h1>Novo Usuario</h1>

	<form:form action="${s:mvcUrl('AC#cadastroUsuario').build() }"
		method="POST">
		<div>
			<label for="nome">nome</label> <input type="text" id="nome"
				name="nome" required />
		</div>
		<div>
			<label for="login">login</label> <input type="text" id="login"
				name="login" required />
		</div>
		<div>
			<label for="senha">senha</label> <input type="text" id="senha"
				name="senha" required />
		</div>
		<div>
			<label for="tipo">tipo de usuario</label> <select id="tipo"
				name="tipo">
				<c:forEach items="${tipos}" var="tipoUsuario">
					<option value="${tipoUsuario }">${tipoUsuario }</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<label for="situacao">Situação do usuario</label> <select
				id="situacao" name="situacao">
				<c:forEach items="${situacoes}" var="situacaoUsuario">
					<option value="${situacaoUsuario }">${situacaoUsuario }</option>
				</c:forEach>
			</select>
		</div>

		<button type="submit">Cadastrar Usuario</button>

	</form:form>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="${contextPath }resources/js/bootstrap.min.js"></script>

</html>