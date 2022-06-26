package br.com.fateczl.zikagames.dao;

import br.com.fateczl.zikagames.entity.Aluguel;

public interface IAluguelDAO extends IBaseDAO<Aluguel> {
	public Aluguel pesquisarPorId(int id);
}
