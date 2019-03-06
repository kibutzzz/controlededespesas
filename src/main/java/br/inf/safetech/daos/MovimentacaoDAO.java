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

	public void gravar(Movimentacao movimentacao) throws RuntimeException {
		manager.persist(movimentacao);
	}

	/**
	 * Atualiza os dados da movimentação
	 * 
	 * @param movimentacao movimentação com os novos dados 
	 * @return movimentação com os dados atualizados
	 */
	public Movimentacao mesclar(Movimentacao movimentacao) {
		return manager.merge(movimentacao);
	}

	/**
	 * busca movimentação pelo id passado
	 * 
	 * @param id
	 * @return movimentação selecionada 
	 */
	public Movimentacao buscarMovimentacaoPorId(int id) {
		return manager.createQuery("select m from Movimentacao m where m.id = :pId", Movimentacao.class)
				.setParameter("pId", id).getSingleResult();
	}

}
