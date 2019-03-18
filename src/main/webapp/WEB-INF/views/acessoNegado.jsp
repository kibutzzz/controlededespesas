<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<c:url var="thisURL" value="/logout"/>

<tags:pageTemplate titulo="Area administrativa">



	<h2>Acesso negado</h2>
	<p>Você não tem autorização para acessar este endereço</p>
	<p>
	<a href="${logout }">Faça login </a> como um usuário que possua autorização ou 
	<a href="${s:mvcUrl('HC#index').build() }">volte para página
		inicial</a>
	</p>



	<!-- 

	<c:if test="${not empty exception}">
		Mensagem: exception ${exception.localizedMessage}
		
		<c:forEach items="${exception.stackTrace}" var="stack">	
		-	${stack}
		</c:forEach>
		
	</c:if>
 -->
</tags:pageTemplate>