package br.inf.safetech.formwrapper;

import br.inf.safetech.model.ContaDespesa;
import br.inf.safetech.model.Movimentacao;

/**
 * Classe utilizada nos formularios de cadastro para trazer informações de uma
 * conta e de uma movimentação
 * 
 * @author Leonardo
 *
 */

//TODO verificar se os Wrappers de cadastro e edição podem ser fundidos em um unico wrapper
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
