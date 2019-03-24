<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/resources/css/" var="cssPath" />

<tags:pageTemplate titulo="Contas Cadastradas ">
	<jsp:attribute name="extraScripts">
		<script
				src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/js/standalone/selectize.js"></script>
		<script>
			$('#colaborador, #cliente').selectize({
				create : false,
				sortField : 'text',
				placeholder : "Selecione"
			});
	
			$('.date-mask').mask('00/00/0000');
		</script>
		<link rel="stylesheet" href="${cssPath }selectize.default.css">
	</jsp:attribute>
	<jsp:body>
		<h1>Contas Cadastradas</h1>
	
		<%@ include file="../templates/formFiltroAdmin.jsp"%>
		<tags:cardContaTemplate contas="${contas }" metodoUrl="AC#detalheConta"></tags:cardContaTemplate>
	</jsp:body>
</tags:pageTemplate>