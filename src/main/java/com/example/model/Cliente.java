package com.example.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.example.enums.Tipo;

@Entity
public class Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Transient
	private Tipo tipo;
	private BigDecimal limiteCredito;
	private BigDecimal juros;
	
	public Cliente(Long id, String nome, Tipo tipo, BigDecimal limiteCredito) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.limiteCredito = limiteCredito;
		this.calcularJuros();
	}
	public Cliente() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
		this.calcularJuros();
	}
	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public BigDecimal getJuros() {
		return juros;
	}
	
	private void calcularJuros() {
		switch (tipo) {
			case B:
				this.juros = new BigDecimal(0.1);
				break;
			case C:
				this.juros = new BigDecimal(0.2);
				break;
			case A:
			default:
				this.juros = BigDecimal.ZERO;
		}
	}
}
