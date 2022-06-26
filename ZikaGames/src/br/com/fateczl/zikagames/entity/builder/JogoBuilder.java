package br.com.fateczl.zikagames.entity.builder;

import br.com.fateczl.zikagames.entity.Jogo;

public class JogoBuilder {
	private Jogo jogo;
	
	public JogoBuilder() {
		this.jogo = new Jogo();
	}
	
	public static JogoBuilder builder() {
		return new JogoBuilder();
	}
	
	public JogoBuilder addId(int id) {
		jogo.setId(id);
		
		return this;
	}
	
	public JogoBuilder addNome(String nome) {
		jogo.setNome(nome);
		
		return this;
	}
	
	public JogoBuilder addClassificacaoInd(String classificacaoInd) {
		jogo.setClassificacaoInd(classificacaoInd);
		
		return this;
	}
	
	public JogoBuilder addPreco(double preco) {
		jogo.setPreco(preco);
		
		return this;
	}
	
	public Jogo get() {
		return jogo;
	}
}
