package br.com.fateczl.zikagames.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import br.com.fateczl.zikagames.entity.Jogo;
import br.com.fateczl.zikagames.util.HibernateUtil;
import jakarta.persistence.EntityManager;

public class JogoDAO implements IJogoDAO {
	
	private SessionFactory sf;
	
	public JogoDAO() {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void adicionar(Jogo entity) {
		EntityManager entityManager = sf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jogo> pesquisar(String column) {
		List<Jogo> jogos = new ArrayList<Jogo>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT * FROM jogo ");
		buffer.append(" WHERE nome LIKE '%" + column + "%'");
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Jogo jg = new Jogo();
			jg.setId(Integer.parseInt(obj[0].toString()));
			jg.setClassificacaoInd(obj[1].toString());
			jg.setNome(obj[2].toString());
			jg.setPreco(Double.parseDouble(obj[3].toString()));
			
			jogos.add(jg);
		}
		
		return jogos;
	}

	@Override
	public Jogo pesquisarPorId(int id) {
		EntityManager entityManager = sf.createEntityManager();
		Jogo jogo = entityManager.find(Jogo.class, id);
		return jogo;
	}

	@Override
	public void atualizar(Jogo entity) {
		EntityManager entityManager = sf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void excluir(Jogo entity) {
		EntityManager entityManager = sf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}
}
