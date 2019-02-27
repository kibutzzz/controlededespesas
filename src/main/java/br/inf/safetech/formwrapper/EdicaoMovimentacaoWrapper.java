package br.inf.safetech.formwrapper;

import br.inf.safetech.model.Movimentacao;

public class EdicaoMovimentacaoWrapper {

	private String contaId;
	private Movimentacao movimentacao;

	public String getContaId() {
		return contaId;
	}

	public void setContaId(String contaId) {
		this.contaId = contaId;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

}
