package br.com.fateczl.zikagames.control;

public interface IBaseControl<T> {
	public void adicionar() throws Exception;
	public void pesquisar();
	public void atualizar() throws Exception;
	public void excluir();
}
