<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Area administrativa">



	<h2>Algo deu errado</h2>
	<p>
		<a href="${s:mvcUrl('HC#index').build() }">Voltar para página inicial</a>
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