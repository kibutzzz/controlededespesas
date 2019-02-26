package br.inf.safetech.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.inf.safetech.model.Movimentacao;

@Repository
@Transactional
public class MovimentacaoDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Movimentacao movimentacao) {
		manager.persist(movimentacao);
	}
}
