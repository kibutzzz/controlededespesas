<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Area administrativa</title>
</head>
<body>

	<h1>Area administrativa</h1>
		<c:if test="${not empty status }">${status }</c:if>
	
	
	<a href="${s:mvcUrl('AC#formularioCadastroConta').build() }">cadastro de conta</a>
	<a href="${s:mvcUrl('AC#formularioCadastroUsuario').build() }">cadastro de usuario</a>
	<a href="${s:mvcUrl('CC#usuarioOverview').build() }">Área do Usuário</a>
	<a href="${s:mvcUrl('AC#adminOverview').build() }">Área Administrativa</a>
	<a href="${s:mvcUrl('AC#listarUsuarios').build() }">Usuarios cadastrados</a>
	<a href="${s:mvcUrl('AC#listarContas').build() }">Contas Cadastradas</a>
	
	
<!-- 	TODO desenvolver area administrativa  -->
	
	

</body>
</html>