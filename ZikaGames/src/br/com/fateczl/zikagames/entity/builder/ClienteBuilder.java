package br.com.fateczl.zikagames.entity.builder;

import java.time.LocalDate;
import java.util.InputMismatchException;

import br.com.fateczl.zikagames.control.Validations.ClienteValidations;
import br.com.fateczl.zikagames.control.Validations.Validator;
import br.com.fateczl.zikagames.entity.Cliente;

public class ClienteBuilder {
	private Cliente cliente;

	public ClienteBuilder() {
		this.cliente = new Cliente();
	}
	
	public static ClienteBuilder builder() {
		return new ClienteBuilder();
	}
	
	public ClienteBuilder addCpf(String cpf) {
		cliente.setCpf(cpf);
		
		return this;
	}
	
	public ClienteBuilder addNome(String nome) {
		cliente.setNome(nome);
		return this;
	}
	
	public ClienteBuilder addDataNascimento(LocalDate dataNasc) throws Exception {
		Validator valid = ClienteValidations.validarDataNascimento(dataNasc);
		
		if(!valid.isSuccess())
			throw new InputMismatchException(valid.getErrorMessage());
			
		cliente.setDataNascimento(dataNasc);
		
		return this;
	}
	
	public ClienteBuilder addTelefone(String telefone) {
		cliente.setTelefone(telefone);
		
		return this;
	}
	
	public ClienteBuilder addEmail(String email) {
		cliente.setEmail(email);
		
		return this;
	}
	
	public Cliente get() {
		return this.cliente;
	}
}
