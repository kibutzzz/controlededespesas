package br.inf.safetech.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.inf.safetech.model.Cliente;
import br.inf.safetech.model.SituacaoUsuario;
import br.inf.safetech.model.TipoUsuario;
import br.inf.safetech.model.Usuario;

public class GeradorDeDados {

	public List<Cliente> gerarClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();

		for (int i = 0; i < 5; i++) {
			clientes.add(new Cliente());
		}

		clientes.get(0).setNome("Mercado Guarani");
		clientes.get(1).setNome("Rissul");
		clientes.get(2).setNome("Xis do Vini");
		clientes.get(3).setNome("Bottero");
		clientes.get(4).setNome("Azaleia");

		return clientes;
	}


	public List<Usuario> gerarUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		usuarios.add(criaUsuario("leonardo.ramos", "Leonardo Meinerz Ramos", "leonardo123", SituacaoUsuario.ATIVO,
				TipoUsuario.COLABORADOR));
		usuarios.add(
				criaUsuario("mateus.santos", "Mateus Santos", "senha", SituacaoUsuario.ATIVO, TipoUsuario.COLABORADOR));
		usuarios.add(criaUsuario("marcia", "marcia", "admin", SituacaoUsuario.ATIVO, TipoUsuario.ADMIN));

		usuarios.add(criaUsuario("usuario.inativo", "usuario demitido", "123", SituacaoUsuario.INATIVO,
				TipoUsuario.COLABORADOR));
		usuarios.add(
				criaUsuario("admin.inativo", "admin demitido", "4456", SituacaoUsuario.INATIVO, TipoUsuario.ADMIN));

		return usuarios;
	}

	private Usuario criaUsuario(String login, String nome, String senha, SituacaoUsuario situacao, TipoUsuario tipo) {
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setNome(nome);
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario.setSituacao(situacao);
		usuario.setTipo(tipo);
		return usuario;
	}

}
