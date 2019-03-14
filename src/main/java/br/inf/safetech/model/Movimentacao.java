package br.inf.safetech.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ_MOVIMENTACAO")
	private int id;

	private TipoMovimentacao tipo;
	private EstadoConciliacao conciliada;
	private BigDecimal valor;
	private String descricao;
	private CategoriaMovimentacao categoria;
	private TipoUsuario cadastradoPor;

	public TipoUsuario getCadastradoPor() {
		return cadastradoPor;
	}

	public void setCadastradoPor(TipoUsuario cadastradoPor) {
		this.cadastradoPor = cadastradoPor;
	}

	public CategoriaMovimentacao getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaMovimentacao categoria) {
		this.categoria = categoria;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EstadoConciliacao getConciliada() {
		return conciliada;
	}

	public void setConciliada(EstadoConciliacao conciliada) {
		this.conciliada = conciliada;
	}

	public BigDecimal getValor() {
		return valor.setScale(2);
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean estaConciliada() {
		if (this.conciliada.equals(EstadoConciliacao.CONCILIADA)) {
			return true;
		}
		return false;
	}

}
