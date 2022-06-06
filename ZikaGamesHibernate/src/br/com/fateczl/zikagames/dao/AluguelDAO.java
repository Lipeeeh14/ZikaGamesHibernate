package br.com.fateczl.zikagames.dao;

import org.hibernate.SessionFactory;

public class AluguelDAO {
	
	private SessionFactory sf;
	
	public AluguelDAO(SessionFactory sf) {
		this.sf = sf;
	}
}
