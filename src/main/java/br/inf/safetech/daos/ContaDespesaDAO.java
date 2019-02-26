package br.inf.safetech.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.inf.safetech.model.ContaDespesa;
import br.inf.safetech.model.Usuario;

@Repository
@Transactional
public class ContaDespesaDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(ContaDespesa conta) {
		manager.persist(conta);
	}

	public List<ContaDespesa> listar() {
		return manager.createQuery("select c from ContaDespesa c", ContaDespesa.class).getResultList();
	}

	public ContaDespesa buscarContaPeloId(Integer id) {

		return manager.createQuery("select c from ContaDespesa c left join fetch c.movimentacoes where c.id = :pId", ContaDespesa.class)
				.setParameter("pId", id).getSingleResult();
	}

	public List<ContaDespesa> listarContaPorUsuario(Usuario usuario) {
		return manager.createQuery("select c from ContaDespesa c where c.usuario = :pUsuario", ContaDespesa.class)
				.setParameter("pUsuario", usuario).getResultList();
	}
	
	public void mesclar(ContaDespesa conta) {
		manager.merge(conta);
	}

}
