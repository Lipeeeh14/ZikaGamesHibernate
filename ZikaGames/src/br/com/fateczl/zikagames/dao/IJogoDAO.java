package br.com.fateczl.zikagames.dao;

import br.com.fateczl.zikagames.entity.Jogo;

public interface IJogoDAO extends IBaseDAO<Jogo> {
	public Jogo pesquisarPorId(int id);
}
