package br.inf.safetech.formwrapper;

import br.inf.safetech.model.ContaDespesa;
import br.inf.safetech.model.Movimentacao;

public class CadastroMovimentacaoWrapper {

	private Movimentacao movimentacao;
	private ContaDespesa conta;

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public ContaDespesa getConta() {
		return conta;
	}

	public void setConta(ContaDespesa conta) {
		this.conta = conta;
	}

}
