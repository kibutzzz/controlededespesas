
<div class="container mb-1 d-flex justify-content-between align-items-center">
<h3 class="d-inline-block">filtro</h3>
	<button class="btn btn-sm btn-primary" type="button"
		data-toggle="collapse" data-target="#collapse-filtro"
		aria-expanded="false" aria-controls="collapseExample" style="font-size: 1.3em;"><span class="oi oi-elevator"></span>
		
	</button>
</div>
<div class="collapse" id="collapse-filtro">
	<form:form cssClass="mb-4"
		action="${s:mvcUrl('AC#contasFiltradas').build() }" method="POST">
		<div class="row mb-md-3">


			<div class="col-12 col-md-6 ">
				<label>Colaborador</label> <select id="colaborador"
					name="colaborador.id" class="form-control">
					<option disabled selected value="0">selecione</option>
					<c:forEach items="${colaboradoresDisponiveis}" var="colaborador">
						<option value="${colaborador.id }">${colaborador.nome }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-12 col-md-6 ">
				<label>CLiente</label> <select id="cliente" name="cliente.id"
					class="form-control">
					<option disabled selected value="0">selecione</option>

					<c:forEach items="${clientesDisponiveis}" var="cliente">
						<option value="${cliente.id }">${cliente.nome }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">

			<div class="col-12 col-md-3 ">
				<label>Data de inicio</label> <input type="text"
					class="form-control date-mask" name="dataInicio" placeholder="data de inicio" />
			</div>
			<div class="col-12 col-md-3 ">
				<label>Data de fim</label> <input type="text" class="form-control date-mask"
					name="dataFim" placeholder="data de fim" />
			</div>
			<div class="col-12 col-md-3 ">
				<label>Situação da conta</label> <select name="situacao"
					class="form-control">
					<c:forEach items="${situacoes }" var="situacao">
						<option value="${situacao }">${situacao }</option>
					</c:forEach>
				</select>
			</div>

			<div class="col-12 col-md-3 mt-auto">
				<button class="btn btn-block btn-primary">Filtrar</button>
			</div>
		</div>
	</form:form>
</div>