package br.com.fateczl.zikagames.entity.builder;

import java.time.LocalDate;
import java.util.InputMismatchException;

import br.com.fateczl.zikagames.control.Validations.AluguelValidations;
import br.com.fateczl.zikagames.control.Validations.Validator;
import br.com.fateczl.zikagames.entity.Aluguel;
import br.com.fateczl.zikagames.entity.Cliente;
import br.com.fateczl.zikagames.entity.Jogo;

public class AluguelBuilder {
	private Aluguel aluguel;
	
	public AluguelBuilder() {
		this.aluguel = new Aluguel();
	}
	
	public static AluguelBuilder builder() {
		return new AluguelBuilder();
	}
	
	public AluguelBuilder addId(int id) {
		this.aluguel.setId(id);
		
		return this;
	}
	
	public AluguelBuilder addCliente(Cliente cliente) {
		this.aluguel.setCliente(cliente);
		
		return this;
	}
	
	public AluguelBuilder addJogo(Jogo jogo) {
		this.aluguel.setJogo(jogo);
		
		return this;
	}
	
	public AluguelBuilder addDataAluguel() {
		aluguel.setDataAluguel(LocalDate.now());
		
		return this;
	}
	
	public AluguelBuilder addDataDevolucao(LocalDate dataDevolucao) throws Exception {
		Validator valid = AluguelValidations.validarDataVencimento(dataDevolucao);
		
		if (!valid.isSuccess())
			throw new InputMismatchException(valid.getErrorMessage());
		
		aluguel.setDataDevolucao(dataDevolucao);
		
		return this;
	}
	
	public AluguelBuilder addAtivo(boolean ativo) {
		aluguel.setAtivo(ativo);
		
		return this;
	}
	
	public AluguelBuilder addValor(double valor) {
		aluguel.setValor(valor);
		
		return this;
	}
	
	public Aluguel get() {
		return this.aluguel;
	}
}
