package br.inf.safetech.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.inf.safetech.model.SituacaoUsuario;
import br.inf.safetech.model.TipoUsuario;
import br.inf.safetech.model.Usuario;

@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	/**
	 * metodo auxiliar para dev
	 * 
	 * @param usuarios
	 */
	public void gravar(List<Usuario> usuarios) {
		usuarios.forEach((usuario) -> manager.persist(usuario));
	}

	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}

	/**
	 * 
	 * @return lista de usuarios cadastrados no banco
	 */
	public List<Usuario> listar() {
		return manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}

	/**
	 * 
	 * @return lista de usuarios colaboradores ativos cadastrados no banco
	 */
	public List<Usuario> listarColaboradoresAtivos() {

		return manager
				.createQuery("select u from Usuario u where u.situacao = :pSituacao  and  u.tipo = :pTipo",
						Usuario.class)
				.setParameter("pSituacao", SituacaoUsuario.ATIVO).setParameter("pTipo", TipoUsuario.COLABORADOR)
				.getResultList();
	}

	/**
	 * busca um usuario que possua o id passado como parametro
	 * 
	 * @param usuarioId Id passado como argumento
	 * @return usuario com id solicitado
	 */
	public Usuario buscarUsuarioPorId(Integer usuarioId) {

		return manager.createQuery("select u from Usuario u where u.id = :pId", Usuario.class)
				.setParameter("pId", usuarioId).getSingleResult();
	}
	
	/**
	 * metodo utilizado pelo spring security para buscar dados do usuario por login
	 */
	@Override
	public Usuario loadUserByUsername(String login) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.login = :pLogin", Usuario.class)
				.setParameter("pLogin", login).getResultList();
		
		if(usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		
		return usuarios.get(0);
	}

}
