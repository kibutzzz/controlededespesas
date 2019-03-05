package br.inf.safetech.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.inf.safetech.daos.RoleDAO;
import br.inf.safetech.model.Cliente;
import br.inf.safetech.model.Role;
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

	public List<Role> criaRole() {
		Role role = new Role();
		role.setNome("ROLE_ADMIN");

		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		return roles;
	}

	public List<Usuario> gerarUsuarios(List<Role> roles) {
		List<Usuario> usuarios = new ArrayList<Usuario>();

		usuarios.add(criaUsuario("leonardo.ramos", "Leonardo Meinerz Ramos", "leonardo123", SituacaoUsuario.ATIVO,
				TipoUsuario.COLABORADOR, roles));
		usuarios.add(criaUsuario("mateus.santos", "Mateus Santos", "senha", SituacaoUsuario.ATIVO,
				TipoUsuario.COLABORADOR, null));
		usuarios.add(criaUsuario("marcia", "marcia", "admin", SituacaoUsuario.ATIVO, TipoUsuario.ADMIN, null));

		usuarios.add(criaUsuario("usuario.inativo", "usuario demitido", "123", SituacaoUsuario.INATIVO,
				TipoUsuario.COLABORADOR, null));
		usuarios.add(criaUsuario("admin.inativo", "admin demitido", "4456", SituacaoUsuario.INATIVO, TipoUsuario.ADMIN,
				null));

		return usuarios;
	}

	private Usuario criaUsuario(String login, String nome, String senha, SituacaoUsuario situacao, TipoUsuario tipo,
			List<Role> roles) {
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setNome(nome);
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario.setSituacao(situacao);
		usuario.setTipo(tipo);
		usuario.setRoles(roles);
		return usuario;
	}

}
