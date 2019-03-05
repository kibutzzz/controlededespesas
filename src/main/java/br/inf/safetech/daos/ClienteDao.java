package br.inf.safetech.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.inf.safetech.model.Cliente;

@Repository
@Transactional
public class ClienteDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(List<Cliente> clientes) {
		clientes.forEach((cliente) -> manager.persist(cliente));
	}

	public List<Cliente> listar() {
		return manager.createQuery("select c from Cliente c", Cliente.class).getResultList();
	}

	public Cliente buscarClientePorId(Integer clienteId) {

		return manager.createQuery("select c from Cliente c where c.id = :pId", Cliente.class)
				.setParameter("pId", clienteId).getSingleResult();
	}
}
