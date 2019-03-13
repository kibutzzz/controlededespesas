<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:pageTemplate titulo="Contas Cadastradas ">

	<h1>Lista de Contas cadastradas no sistema</h1>
	
	<div class="table-responsive">
		<table class="table table-hover">
			<thead>
				<tr>
					<td>Cliente</td>
					<td>Colaborador</td>
					<td>Data de Inicio</td>
					<td>Situação</td>
					<td>detalhes</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${contas}" var="conta">
					<tr>
						<td>${conta.cliente.nome}</td>
						<td>${conta.usuario.nome}</td>
						<td>${conta.dataInicio}</td>
						<td>${conta.situacao}</td>
						<td><a
							href="${s:mvcUrl('AC#detalheConta').arg(0, conta.id).build() }">detalhes</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</tags:pageTemplate>