package br.com.fateczl.zikagames.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import br.com.fateczl.zikagames.entity.Aluguel;
import br.com.fateczl.zikagames.util.HibernateUtil;
import jakarta.persistence.EntityManager;

public class AluguelDAO implements IAluguelDAO {
	
	private SessionFactory sf;
	
	public AluguelDAO() {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void adicionar(Aluguel entity) {
		EntityManager entityManager = sf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Aluguel> pesquisar(String column) {
		List<Aluguel> alugueis = new ArrayList<Aluguel>();
		ClienteDAO clienteDao = new ClienteDAO();
		JogoDAO jogoDao = new JogoDAO();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT * FROM aluguel ");
		buffer.append(!column.isEmpty() ? 
				"WHERE clienteId = " + column : "");
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Aluguel aluguel = new Aluguel();
			aluguel.setId(Integer.parseInt(obj[0].toString()));
			aluguel.setAtivo(Boolean.parseBoolean(obj[1].toString()));
			aluguel.setAtraso(Boolean.parseBoolean(obj[2].toString()));
			aluguel.setDataAluguel(LocalDate.parse(obj[3].toString()));
			aluguel.setDataDevolucao(LocalDate.parse(obj[4].toString()));
			aluguel.setValor(Double.parseDouble(obj[5].toString()));
			aluguel.setCliente(clienteDao.pesquisarPorCPF(obj[6].toString()));
			aluguel.setJogo(
					jogoDao.pesquisarPorId(Integer.parseInt(obj[7].toString())));
			
			alugueis.add(aluguel);
		}
		
		return alugueis;
	}

	@Override
	public Aluguel pesquisarPorId(int id) {
		EntityManager entityManager = sf.createEntityManager();
		Aluguel aluguel = entityManager.find(Aluguel.class, id);
		return aluguel;
	}

	@Override
	public void atualizar(Aluguel entity) {
		EntityManager entityManager = sf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void excluir(Aluguel entity) {
		EntityManager entityManager = sf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();		
	}
	
}
