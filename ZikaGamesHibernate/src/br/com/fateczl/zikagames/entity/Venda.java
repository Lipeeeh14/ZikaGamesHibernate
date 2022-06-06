package br.com.fateczl.zikagames.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@NotNull
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "clienteId")
	@NotNull
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "jogoId")
	@NotNull
	private Jogo jogo;
	
	@Column(name = "valor")
	@NotNull
	private double valor;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
