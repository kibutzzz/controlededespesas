package br.inf.safetech.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.inf.safetech.model.Role;

@Repository
@Transactional
public class RoleDAO {
	
	@PersistenceContext
	private EntityManager manager;

	
	public void gravar(Role role) {
		System.out.println("Gravando role");
		manager.persist(role);
	}
}
