<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>


	<form action="${s:mvcUrl('HC#autenticar').build()}" method="post">

		<label for="login">Login</label> 
		<input id="login" name="login" type=text /> 
		
		<label for="senha">Senha</label> 
		<input id="senha" name="senha" type=text />

		<button type="submit">Logar</button>
	</form>

</body>
</html>