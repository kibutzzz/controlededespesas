<%@ tag import="br.inf.safetech.helper.StatusType"%>



<c:if test="${not empty status }">

	<div class="container fixed-top mt-5 px-5" id="alert-remove">
		<c:if test="${status.type == StatusType.SUCESSO }">
			<div class="alert alert-dismissible alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4 class="alert-heading">Sucesso!</h4>
				<p class="mb-0">${status.message }</p>
			</div>
		</c:if>

		<c:if test="${status.type == StatusType.ALERTA }">
			<div class="alert alert-dismissible alert-warning">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4 class="alert-heading">Alerta!</h4>
				<p class="mb-0">${status.message }</p>
			</div>
		</c:if>

		<c:if test="${status.type == StatusType.ERRO }">
			<div class="alert alert-dismissible alert-danger">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4 class="alert-heading">Erro!</h4>
				<p class="mb-0">${status.message }</p>
			</div>
		</c:if>
	</div>

</c:if>

