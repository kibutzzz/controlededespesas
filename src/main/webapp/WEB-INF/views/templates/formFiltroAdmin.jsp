<form:form action="${s:mvcUrl('AC#contasFiltradas').build() }"
	method="POST">
	<div class="row mb-md-3">


		<div class="col-12 col-md-6 ">
			<select id="colaborador" name="colaborador.id" class="form-control">
				<option disabled selected value>selecione</option>
				<c:forEach items="${colaboradoresDisponiveis}" var="colaborador">
					<option value="${colaborador.id }">${colaborador.nome }</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-12 col-md-6 ">

			<select id="cliente" name="cliente.id" class="form-control">
				<option disabled selected value>selecione</option>

				<c:forEach items="${clientesDisponiveis}" var="cliente">
					<option value="${cliente.id }">${cliente.nome }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="row">
		<div class="col-12 col-md-3 ">
			<!-- 			<input type="text" class="form-control" name="dataInicio" -->
			<!-- 				placeholder="data de inicio" /> -->
		</div>
		<div class="col-12 col-md-3 ">
			<!-- 			<input type="text" class="form-control" name="dataFim" -->
			<!-- 				placeholder="data de fim" /> -->
		</div>
		<div class="col-12 col-md-3 ">
			<select name="situacao" class="form-control">
				<c:forEach items="${situacoes }" var="situacao">
					<option value="${situacao }">${situacao }</option>
				</c:forEach>
			</select>
		</div>

		<div class="col-12 col-md-3">
			<button class="btn btn-block btn-primary">Filtrar</button>
		</div>
	</div>
</form:form>
