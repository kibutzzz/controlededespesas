<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Pagina do Colaborador">

	<h1>Página do colaborador</h1>

	<div class="row">

		<div class="col-12 col-md-6">
			<span class="font-weight-bold">Nome completo: </span> <p>${colaborador.nome }</p>
		</div>
		<div class="col-12 col-md-6">
			<span class="font-weight-bold">Login: </span> <p>${colaborador.login}</p>
		</div>
		<div class="col-12">
			 <a href="#">Trocar Senha</a>
		</div>
		

	</div>

</tags:pageTemplate>