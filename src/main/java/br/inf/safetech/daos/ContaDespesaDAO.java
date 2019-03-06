package br.inf.safetech.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.inf.safetech.model.ContaDespesa;
import br.inf.safetech.model.Usuario;

@Repository
@Transactional
public class ContaDespesaDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(ContaDespesa conta) throws RuntimeException{
		manager.persist(conta);
	}

	/**
	 * busca todas as contas cadastradas no banco
	 * 
	 * @return lista com todas as contas cadastradas
	 */
	public List<ContaDespesa> listar() {
		return manager.createQuery("select c from ContaDespesa c", ContaDespesa.class).getResultList();
	}

	/**
	 * busca uma conta especifica pelo id
	 * 
	 * @param id
	 * @return a conta com o id passado
	 */
	public ContaDespesa buscarContaPeloId(Integer id) throws PersistenceException {

		return manager.createQuery("select c from ContaDespesa c left join fetch c.movimentacoes where c.id = :pId",
				ContaDespesa.class).setParameter("pId", id).getSingleResult();
	}

	
	/**
	 * busca todas as conta associadas ao usuario passado como argumento
	 * 
	 * @param usuario 
	 * @return lista de contas associada ao usuario
	 */
	public List<ContaDespesa> listarContaPorUsuario(Usuario usuario) {
		return manager.createQuery("select c from ContaDespesa c where c.usuario = :pUsuario", ContaDespesa.class)
				.setParameter("pUsuario", usuario).getResultList();
	}

	/**
	 * atualiza a conta que for passada como argumento
	 * 
	 * @param conta
	 */
	public void mesclar(ContaDespesa conta) throws RuntimeException {
		manager.merge(conta);
	}

}
