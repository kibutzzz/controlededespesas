package br.inf.safetech.formwrapper;

import br.inf.safetech.model.ContaDespesa;

public class EncerramentoContaWrapper {

	private ContaDespesa conta;
	private String formaDeEncerramento;

	public ContaDespesa getConta() {
		return conta;
	}

	public void setConta(ContaDespesa conta) {
		this.conta = conta;
	}

	public String getFormaDeEncerramento() {
		return formaDeEncerramento;
	}

	public void setFormaDeEncerramento(String formaDeEncerramento) {
		this.formaDeEncerramento = formaDeEncerramento;
	}

}
