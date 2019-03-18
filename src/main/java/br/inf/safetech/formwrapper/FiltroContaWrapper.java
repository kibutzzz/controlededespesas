package br.inf.safetech.formwrapper;

import java.util.Calendar;

import br.inf.safetech.model.Cliente;
import br.inf.safetech.model.ContaDespesa;
import br.inf.safetech.model.SituacaoConta;
import br.inf.safetech.model.Usuario;

public class FiltroContaWrapper {

	private Usuario colaborador;
	private Cliente cliente;
	private SituacaoConta situacao;
	private Calendar dataInicio;
	private Calendar dataFim;

	public Usuario getColaborador() {
		return colaborador;
	}

	public void setColaborador(Usuario colaborador) {
		this.colaborador = colaborador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public SituacaoConta getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoConta situacao) {
		this.situacao = situacao;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

}
