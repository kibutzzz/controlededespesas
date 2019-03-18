<div class="card-header">
	<h2>Conta ${conta.cliente.nome }</h2>

</div>

<div class="card-body">
	<h4 class="card-title">Informações da conta</h4>
	<div class="row">
		<div class="col-12 col-lg-6">
			<p class="card-text">Cliente: ${conta.cliente.nome }</p>
			<p class="card-text">Colaborador: ${conta.usuario.nome }</p>
			<p class="card-text">Situação: ${conta.situacao }</p>
		</div>
		<div class="col-12 col-lg-6">
			<p class="card-text">Data de Inicio: ${conta.dataInicio }</p>
			<p class="card-text">
				Data de encerramento:
				<c:if test="${not empty conta.dataFim}">${conta.dataFim }</c:if>
			</p>
		</div>

	</div>
</div>