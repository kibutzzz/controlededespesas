package br.inf.safetech.formwrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.inf.safetech.model.Cliente;
import br.inf.safetech.model.SituacaoConta;
import br.inf.safetech.model.Usuario;

public class FiltroContaWrapper {

	private Usuario colaborador;
	private Cliente cliente;
	private SituacaoConta situacao;
	private String dataInicio;
	private String dataFim;

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

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public Calendar createCalendar(String data) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		try {
			Date date = sdf.parse(data);
			cal.setTime(date);
		} catch (ParseException e) {
			throw new RuntimeException("Formato da data é inadequado");
		}
		

		return cal;
	}

}
