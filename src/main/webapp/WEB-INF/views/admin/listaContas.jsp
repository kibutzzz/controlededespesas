<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Contas Cadastradas ">

	<h1>Contas Cadastradas</h1>
	
	<%@ include file="../templates/formFiltroAdmin.jsp" %>
	<tags:cardContaTemplate contas="${contas }" metodoUrl="AC#detalheConta"></tags:cardContaTemplate>
	
</tags:pageTemplate>