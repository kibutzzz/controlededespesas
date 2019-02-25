package br.inf.safetech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Usuario {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ_USUARIO")
	private int id;
	private String nome;
	private String login;
	private String senha;
	private TipoUsuario tipo;
	private SituacaoUsuario situacao;		
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	public SituacaoUsuario getSituacao() {
		return situacao;
	}
	public void setSituacao(SituacaoUsuario situacao) {
		this.situacao = situacao;
	}

//	@Override
//	public String toString() {
//		return "Usuario [id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", tipo=" + tipo
//				+ ", situacao=" + situacao + "]";
//	}
//	
	
	
}
