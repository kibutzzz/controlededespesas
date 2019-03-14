<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro de Usuário">

	<h1>Novo Usuário</h1>

	<form:form action="${s:mvcUrl('AC#cadastroUsuario').build() }"
		method="POST">
		<div class="row mt-2">
			<div class="col-12 col-md-6">
				<label for="nome">Nome completo</label> <input type="text" id="nome"
					name="nome" class="form-control" required />
			</div>
			<div class="col-12 col-md-6">
				<label for="login">Login</label> <input type="text" id="login"
					name="login" class="form-control" required />
			</div>

		</div>

		<div class="row mt-2" >
			<div class="col-12 col-md-6">
				<label for="senha">Senha</label> <input type="password" id="senha"
					name="senha" class="form-control" required />
			</div>
			<div class="col-12 col-md-6">
				<label for="confirmaSenha">Confirme a senha</label> <input
					type="password" id="confirmaSenha" name="confirmaSenha"
					class="form-control" required />
			</div>
		</div>

		<div class="row mt-2">
			<div class="col-12 col-sm-6 col-md-4 ">
				<label for="tipo">tipo de usuario</label> <select id="tipo"
					name="tipo" class="form-control">
					<c:forEach items="${tipos}" var="tipoUsuario">
						<option value="${tipoUsuario }">${tipoUsuario }</option>
					</c:forEach>
				</select>
			</div>

			<div class=" col-12 col-sm-6 col-md-4 ">
				<label for="situacao">Situação do usuario</label> <select
					id="situacao" name="situacao" class="form-control ">
					<c:forEach items="${situacoes}" var="situacaoUsuario">
						<option value="${situacaoUsuario }">${situacaoUsuario }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-12  col-md-4 ">
			<label ><pre></pre> </label> 
				<button type="submit" class="btn btn-primary btn-block align-bottom">Cadastrar Usuario</button>
			</div>
		</div>


	</form:form>
</tags:pageTemplate>