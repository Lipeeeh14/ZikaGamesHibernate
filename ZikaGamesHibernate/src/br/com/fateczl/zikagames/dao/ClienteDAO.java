package br.com.fateczl.zikagames.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import br.com.fateczl.zikagames.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class ClienteDAO implements IBaseDAO<Cliente> {
	
	private SessionFactory sf; 
	
	public ClienteDAO(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void adicionar(Cliente entity) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entity);
		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> pesquisar(String column) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT * FROM cliente ");
		buffer.append(" WHERE nome LIKE '%" + column + "%'");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Cliente cli = new Cliente();
			cli.setId(Integer.parseInt(obj[0].toString()));
			cli.setNome(obj[1].toString());
			cli.setCpf(obj[2].toString());
			cli.setDataNascimento(LocalDate.parse(obj[3].toString()));
			cli.setTelefone(obj[4].toString());
			cli.setEmail(obj[5].toString());
			
			clientes.add(cli);
		}
		
		return clientes;
	}

}
