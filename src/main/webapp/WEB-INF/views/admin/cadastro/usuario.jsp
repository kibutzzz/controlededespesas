<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:adminPageTemplate titulo="Cadastro de Usuário">

	<h1>Novo Usuario</h1>

	<form:form action="${s:mvcUrl('AC#cadastroUsuario').build() }"
		method="POST">
		<div>
			<label for="nome">nome</label> <input type="text" id="nome"
				name="nome" required />
		</div>
		<div>
			<label for="login">login</label> <input type="text" id="login"
				name="login" required />
		</div>
		<div>
			<label for="senha">senha</label> <input type="text" id="senha"
				name="senha" required />
		</div>
		<div>
			<label for="tipo">tipo de usuario</label> <select id="tipo"
				name="tipo">
				<c:forEach items="${tipos}" var="tipoUsuario">
					<option value="${tipoUsuario }">${tipoUsuario }</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<label for="situacao">Situação do usuario</label> <select
				id="situacao" name="situacao">
				<c:forEach items="${situacoes}" var="situacaoUsuario">
					<option value="${situacaoUsuario }">${situacaoUsuario }</option>
				</c:forEach>
			</select>
		</div>

		<button type="submit">Cadastrar Usuario</button>

	</form:form>
</tags:adminPageTemplate>