package br.com.fateczl.zikagames.dao;

import br.com.fateczl.zikagames.entity.Cliente;

public interface IClienteDAO extends IBaseDAO<Cliente> {
	public Cliente pesquisarPorCPF(String cpf);
}
