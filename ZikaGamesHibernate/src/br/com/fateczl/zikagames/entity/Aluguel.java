package br.com.fateczl.zikagames.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "aluguel")
public class Aluguel {
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
	private Jogo jogo;
	
	@Column(name = "dataAluguel")
	private LocalDate dataAluguel;
	
	@Column(name = "dataDevolucao")
	private LocalDate dataDevolucao;
	
	@Column(name = "valor")
	@NotNull
	private double valor;
	
	@Column(name = "atraso")
	@ColumnDefault("0")
	private boolean atraso;
	
	@Column(name = "ativo")
	@ColumnDefault("1")
	private boolean ativo;
	
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
	
	public LocalDate getDataAluguel() {
		return dataAluguel;
	}
	
	public void setDataAluguel(LocalDate dataAluguel) {
		this.dataAluguel = dataAluguel;
	}
	
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}
	
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public boolean isAtraso() {
		return atraso;
	}
	
	public void setAtraso(boolean atraso) {
		this.atraso = atraso;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
