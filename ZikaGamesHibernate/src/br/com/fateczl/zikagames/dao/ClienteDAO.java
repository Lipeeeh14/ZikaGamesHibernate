package br.com.fateczl.zikagames.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import br.com.fateczl.zikagames.entity.Cliente;
import br.com.fateczl.zikagames.util.HibernateUtil;
import jakarta.persistence.EntityManager;

public class ClienteDAO implements IBaseDAO<Cliente>{
	
	private SessionFactory sf;
	
	public ClienteDAO() {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void adicionar(Cliente entity) {
		EntityManager entityManager = sf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}
	
	@Override
	public List<Cliente> pesquisar(String column) {
		EntityManager entityManager = sf.createEntityManager();
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM cliente LIKE ");
		query.append("'%" + column + "%'");
		
		return entityManager.createQuery(query.toString(), Cliente.class).getResultList();
	}

	@Override
	public Cliente pesquisarPorId(int id) {
		EntityManager entityManager = sf.createEntityManager();
		Cliente cliente = entityManager.find(Cliente.class, id);
		return cliente;
	}

	@Override
	public void atualizar(Cliente entity) {
		EntityManager entityManager = sf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void excluir(Cliente entity) {
		EntityManager entityManager = sf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

}
