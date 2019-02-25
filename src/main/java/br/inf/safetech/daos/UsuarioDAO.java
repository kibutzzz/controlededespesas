package br.inf.safetech.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.inf.safetech.model.SituacaoUsuario;
import br.inf.safetech.model.TipoUsuario;
import br.inf.safetech.model.Usuario;

@Repository
@Transactional
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager manager;
	
	/**
	 *	metodo auxiliar para dev
	 * @param usuarios
	 */
	public void gravar(List<Usuario> usuarios) {
		usuarios.forEach((usuario) -> manager.persist(usuario));
	}

	public void gravar(Usuario usuario) {

		manager.persist(usuario);
	}

	public List<Usuario> listar() {
		
		return manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}

	public List<Usuario> listarColaboradoresAtivos() {

		return manager
				.createQuery("select u from Usuario u where u.situacao = :pSituacao  and  u.tipo = :pTipo",
						Usuario.class)
				.setParameter("pSituacao", SituacaoUsuario.ATIVO).setParameter("pTipo", TipoUsuario.COLABORADOR)
				.getResultList();
	}

	public Usuario buscarUsuarioPorId(Integer usuarioId) {
		
		return manager
				.createQuery("select u from Usuario u where u.id = :pId", Usuario.class)
				.setParameter("pId", usuarioId).getSingleResult();
	}

}
