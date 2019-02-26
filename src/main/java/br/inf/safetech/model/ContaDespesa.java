package br.inf.safetech.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ContaDespesa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ_CONTA_DESPESA")
	private int id;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Cliente cliente;
	private SituacaoConta situacao;

	@DateTimeFormat
	private Calendar dataInicio;

	@DateTimeFormat
	private Calendar dataFim;

	@OneToMany
	private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		if (dataInicio == null)
			return null;
		return (new SimpleDateFormat("dd/MM/YYYY")).format(dataInicio.getTime());
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		if (dataFim == null)
			return null;

		return (new SimpleDateFormat("dd/MM/YYYY")).format(dataFim.getTime());
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public void adicionarMovimentacao(Movimentacao movimentacao) {
		this.movimentacoes.add(movimentacao);
	}

	public BigDecimal getSaldoDisponivel() {
		BigDecimal total = new BigDecimal("0.00");
		for (Movimentacao movimentacao : this.movimentacoes) {
			System.out.print("Movimentação R$: " + movimentacao.getValor() + " tipo: " + movimentacao.getTipo());
			if (movimentacao.getTipo() == TipoMovimentacao.CREDITO) {

				total = total.add(movimentacao.getValor());
			} else {
				total = total.subtract(movimentacao.getValor());
			}
			System.out.println("total: " + total);
		}
		System.out.println(total);
		return total;

	}

}
