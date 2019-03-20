<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Pagina do Colaborador">


	<h1>Pagina do colaborador</h1>

	<c:if test="${empty contas }">
		<h5>nenhuma conta cadastrada</h5>
	</c:if>

	<tags:cardContaTemplate contas="${contas }" metodoUrl="CC#conta"></tags:cardContaTemplate>

</tags:pageTemplate>