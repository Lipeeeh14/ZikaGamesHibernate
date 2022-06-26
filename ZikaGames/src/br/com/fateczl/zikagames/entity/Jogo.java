package br.com.fateczl.zikagames.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "jogo")
public class Jogo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@NotNull
	private int id;
	
	@Column(name = "nome", length = 80)
	@NotNull
	private String nome;
	
	@Column(name = "classificacaoInd", length = 10)
	@NotNull
	private String classificacaoInd;
	
	@Column(name = "preco")
	@NotNull
	private double preco;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getClassificacaoInd() {
		return classificacaoInd;
	}
	
	public void setClassificacaoInd(String classificacaoInd) {
		this.classificacaoInd = classificacaoInd;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String toString() {
		return Integer.toString(this.id);
	}
}
