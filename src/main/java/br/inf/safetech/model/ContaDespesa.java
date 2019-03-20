package br.inf.safetech.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@OneToMany(fetch=FetchType.EAGER)
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
		return (new SimpleDateFormat("dd/MM/yyyy")).format(dataInicio.getTime());
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		if (dataFim == null)
			return null;

		return (new SimpleDateFormat("dd/MM/yyyy")).format(dataFim.getTime());
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

	public void removerMovimentacao(Movimentacao movimentacao) {
		this.movimentacoes = this.movimentacoes.stream().filter(m -> m.getId() != movimentacao.getId())
				.collect(Collectors.toList());
	}

	public BigDecimal getSaldoDisponivel() {
		BigDecimal total = new BigDecimal("0.00");
		
		
		for (Movimentacao movimentacao : this.movimentacoes) {
			if (movimentacao.getTipo() == TipoMovimentacao.CREDITO) {
				total = total.add(movimentacao.getValor());
			} else {
				total = total.subtract(movimentacao.getValor());
			}
		}
		return total;

	}

	public boolean getMovimentacoesEstaoConciliadas() {
		for (Movimentacao movimentacao : this.movimentacoes) {
			if (!movimentacao.estaConciliada()) {
				return false;
			}
		}
		return true;
	}

}
