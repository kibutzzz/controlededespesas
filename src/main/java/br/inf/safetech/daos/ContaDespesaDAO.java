package br.inf.safetech.daos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.inf.safetech.formwrapper.FiltroContaWrapper;
import br.inf.safetech.model.Cliente;
import br.inf.safetech.model.ContaDespesa;
import br.inf.safetech.model.SituacaoConta;
import br.inf.safetech.model.Usuario;

@Repository
@Transactional
public class ContaDespesaDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(ContaDespesa conta) throws RuntimeException {
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

	public List<ContaDespesa> buscarContaFiltrada(FiltroContaWrapper wrapper) {
		
		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<ContaDespesa> query = criteriaBuilder.createQuery(ContaDespesa.class);

		Root<ContaDespesa> root = query.from(ContaDespesa.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		Path<String> usuarioPath = root.<Usuario>get("usuario").<String>get("id");
		Path<String> clientePath = root.<Cliente>get("cliente").<String>get("id");
		Path<Calendar> dataInicioPath = root.<Calendar>get("dataInicio");
		Path<Calendar> dataFimPath = root.<Calendar>get("dataFim");
		Path<SituacaoConta> situacaoPath = root.<SituacaoConta>get("situacao");

		if (!(wrapper.getColaborador() == null) && wrapper.getColaborador().getId() != 0) {
			System.out.println("filtrando colaborador " + wrapper.getColaborador());

			Predicate usuarioIgual = criteriaBuilder.equal(usuarioPath, wrapper.getColaborador().getId());
			predicates.add(usuarioIgual);
		}

		if (!(wrapper.getCliente() == null) && wrapper.getCliente().getId() != 0) {
			System.out.println("filtrando cliente " + wrapper.getCliente());
			Predicate clienteIgual = criteriaBuilder.equal(clientePath, wrapper.getCliente().getId());
			predicates.add(clienteIgual);
		}

		if (!(wrapper.getDataInicio() == null) && !(wrapper.getDataInicio() == "")) {
			System.out.println("filtrando dataInicio " + wrapper.getDataInicio());
			Predicate dataInicioMaiorQue = criteriaBuilder.greaterThanOrEqualTo(dataInicioPath,
					wrapper.createCalendar(wrapper.getDataInicio()));
			predicates.add(dataInicioMaiorQue);
		}

		if (!(wrapper.getDataFim() == null) && !(wrapper.getDataFim() == "")) {
			System.out.println("filtrando dataFim " + wrapper.getDataFim());
			Predicate dataFimMaiorQue = criteriaBuilder.greaterThanOrEqualTo(dataFimPath,
					wrapper.createCalendar(wrapper.getDataFim()));
			predicates.add(dataFimMaiorQue);
		}

		if (!(wrapper.getSituacao() == null)) {
			System.out.println("filtrando situação");
			Predicate situacaoIgual = criteriaBuilder.equal(situacaoPath, wrapper.getSituacao());
			predicates.add(situacaoIgual);
		}

		if (predicates.size() > 0) {
			query.where((Predicate[]) predicates.toArray(new Predicate[0]));
		}
		List<ContaDespesa> contas = manager.createQuery(query).getResultList();
		System.out.println(contas.size());
		return contas;
	}

}
