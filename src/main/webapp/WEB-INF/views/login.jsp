<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="theme-color" content="#1a1a1a">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/floating-labels.css">

<title>Login</title>


</head>
<body>
	<%@ include file="../views/templates/statusAlertLogin.jsp"%>

	<!-- TODO ver: https://stackoverflow.com/questions/41404076/spring-security-custom-login-error-messages -->
	<form:form servletRelativeAction="/login" method="post"
		cssClass="form-signin">
		<div class="text-center mb-4">
			<img class="mb-4" src="resources/imagens/logo_safetech.png"
				alt="Safetech" style="max-width: 100%; max-height: 150px">
			<h1 class="h3 mb-3 font-weight-normal">Controle de despesas</h1>
			<p>Faça login para pode utilizar o sistema.</p>
		</div>

		<div class="form-label-group">
			<input name="username" type="text" id="username" class="form-control"
				placeholder="Usuario" required autofocus> <label
				for="username">Usuario</label>
		</div>
		<div class="form-label-group">
			<input name="password" type="password" id="password"
				class="form-control" placeholder="Senha" required> <label
				for="password">Senha</label>
		</div>


		<c:if test="${param.error != null}">
			<div class="alert alert-danger">Nome de usuario ou senha estão
				incorretos</div>
		</c:if>
		<c:if test="${param.logSucc == true}">
			<div id="success">
				<div class="alert alert-danger">Você foi deslogado.</div>
			</div>
		</c:if>


		<button class="btn btn-primary btn-block" type="submit">Logar</button>
	</form:form>

</body>
</html>