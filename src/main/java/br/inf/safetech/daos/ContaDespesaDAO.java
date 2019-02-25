package br.inf.safetech.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.inf.safetech.model.ContaDespesa;

@Repository
@Transactional
public class ContaDespesaDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(ContaDespesa conta) {
		manager.persist(conta);
	}
	
	public List<ContaDespesa> listar(){
		return manager.createQuery("select c from ContaDespesa c", ContaDespesa.class).getResultList();
	}
	
}
