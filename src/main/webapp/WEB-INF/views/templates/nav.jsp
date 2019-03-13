<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<a class="navbar-brand" href="${s:mvcUrl('AC#adminOverview').build() }">Controle
		de despesas</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarColor01" aria-controls="navbarColor01"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>


	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">

			<li class="nav-item pl-0"><a class="nav-link text-justify"
				href="${s:mvcUrl('AC#listarUsuarios').build() }">Usuarios
					cadastrados</a></li>
			<li class="nav-item pl-0"><a class="nav-link text-justify"
				href="${s:mvcUrl('AC#listarContas').build() }">Contas
					Cadastradas</a></li>
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

				</div>
			

		</ul>
		<ul class="navbar-nav ml-auto">
		<li class=" nav-item"><a class="nav-link text-justify"
				href="<c:url value="/logout" />">Logout</a></li>
		</ul>

	</div>
</nav>