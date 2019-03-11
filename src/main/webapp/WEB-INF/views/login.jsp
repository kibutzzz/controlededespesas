<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>


	<!-- TODO ver: https://stackoverflow.com/questions/41404076/spring-security-custom-login-error-messages -->
		<h1>Login Controle de despesas</h1>
	<form:form servletRelativeAction="/login" method="post">

		<label for="login">Usuario</label> 
		<input id="login" name="username" type=text /> 
		
		<label for="senha">Senha</label> 
		<input id="senha" name="password" type=password />

		<button type="submit">Logar</button>
	</form:form>

</body>
</html>