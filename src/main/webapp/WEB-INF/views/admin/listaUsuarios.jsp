<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Usuários cadastrados">



	<h1>Lista de Usuários Cadastrados no sistema</h1>
	
	<div class="table-responsive">
		<table class="table table-hover">
			<thead>
				<tr>
					<td>Nome</td>
					<td>Login</td>

					<td>Tipo</td>
					<td>Situação</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${usuarios}" var="usuario">
					<tr>
						<td>${usuario.nome}</td>
						<td>${usuario.login}</td>

						<td>${usuario.tipo}</td>
						<td>${usuario.situacao}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</tags:pageTemplate>