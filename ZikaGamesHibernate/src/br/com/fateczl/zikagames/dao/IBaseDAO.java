package br.com.fateczl.zikagames.dao;

import java.util.List;

public interface IBaseDAO<T> {
	public void adicionar(T entity);
	public List<T> pesquisar(String column);
}
