package br.com.fateczl.zikagames.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import br.com.fateczl.zikagames.entity.Jogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class JogoDAO implements IBaseDAO<Jogo> {
	
	private SessionFactory sf;
	
	public JogoDAO(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public void adicionar(Jogo entity) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entity);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jogo> pesquisar(String column) {
		List<Jogo> jogos = new ArrayList<Jogo>();
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT * FROM jogo ");
		buffer.append(" WHERE nome LIKE '%" + column + "%'");
		EntityManager entityManager = sf.createEntityManager();
		Query query = entityManager.createNativeQuery(buffer.toString());
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Jogo jg = new Jogo();
			jg.setId(Integer.parseInt(obj[0].toString()));
			jg.setNome(obj[1].toString());
			jg.setClassificacaoInd(obj[2].toString());
			jg.setPreco(Double.parseDouble(obj[3].toString()));
			
			jogos.add(jg);
		}
		
		return jogos;
	}

}
