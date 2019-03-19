<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-5">
	<a class="navbar-brand py-0" href="${s:mvcUrl('HC#index').build() }" ><img
		class="" src="<c:url value='/resources/imagens/logo_safe_pequeno.png'/>" alt="Safetech"
		style="max-width: 100%; max-height: 40px; filter: brightness(0) invert(100%)"></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarColor01" aria-controls="navbarColor01"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>


	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">

			<security:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-haspopup="true" aria-expanded="false">Dados
						Cadastrados</a>
					<div class="dropdown-menu" x-placement="bottom-start"
						style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 37px, 0px);">
						<a class="dropdown-item"
							href="${s:mvcUrl('AC#listarUsuarios').build() }">Usuarios
							cadastrados</a> <a class="dropdown-item"
							href="${s:mvcUrl('AC#listarContas').build() }">Contas
							Cadastradas</a>

					</div></li>


				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-haspopup="true" aria-expanded="false">Cadastro</a>
					<div class="dropdown-menu" x-placement="bottom-start"
						style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 37px, 0px);">
						<a class="dropdown-item"
							href="${s:mvcUrl('AC#formularioCadastroConta').build() }">
							cadastro de conta </a> <a class="dropdown-item"
							href="${s:mvcUrl('AC#formularioCadastroUsuario').build() }">cadastro
							de usuario</a>

					</div></li>
			</security:authorize>


		</ul>
		<ul class="navbar-nav ml-auto">
			<security:authorize access="hasRole('ROLE_ADMIN')">

				<li class=" nav-item"><a class="nav-link text-justify" href="#"><security:authentication
							property="principal" var="usuario" />Olá, ${usuario.nome } </a></li>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_COLABORADOR')">

				<li class=" nav-item"><a class="nav-link text-justify"
					href="<c:url value="/colaborador/detalhes"/>"><security:authentication
							property="principal" var="usuario" />Olá, ${usuario.nome } </a></li>
			</security:authorize>
			<li class=" nav-item"><a class="nav-link text-justify"
				href="<c:url value="/logout" />">Logout</a></li>
		</ul>

	</div>
</nav>