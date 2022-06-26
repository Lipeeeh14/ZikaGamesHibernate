package br.com.fateczl.zikagames.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import br.com.fateczl.zikagames.entity.Cliente;
import br.com.fateczl.zikagames.util.HibernateUtil;
import jakarta.persistence.EntityManager;

public class ClienteDAO implements IClienteDAO{
	
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
		List<Cliente> clientes = new ArrayList<Cliente>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT * FROM cliente WHERE nome LIKE ");
		buffer.append("'%" + column + "%'");
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Cliente cliente = new Cliente();
			cliente.setCpf(obj[0].toString());
			cliente.setDataNascimento(LocalDate.parse(obj[1].toString()));
			cliente.setEmail(obj[2].toString());
			cliente.setNome(obj[3].toString());
			cliente.setTelefone(obj[4].toString());
			
			clientes.add(cliente);
		}
		
		return clientes;
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

	@Override
	public Cliente pesquisarPorCPF(String cpf) {
		EntityManager entityManager = sf.createEntityManager();
		Cliente cliente = entityManager.find(Cliente.class, cpf);
		return cliente;
	}

}
