package com.example.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.model.Cliente;

public class ClienteDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private BigDecimal juros;
	
	@NotNull(message="O nome do Cliente não pode ser nulo")
	@Length(min=1, max=100, message="O tamanho do nome deve estar em 1 e 100 caracteres")
	private String nome;
	
	@NotNull(message = "O limite de crédito não pode ser vazio")
	private BigDecimal limite;
	
	@NotNull(message = "O tipo não pode ser vazio")
	private String tipo;

	public ClienteDto(Cliente c) {
		this.id = c.getId();
		this.nome = c.getNome();
		this.limite = c.getLimiteCredito();
		this.juros = c.getJuros();
		this.tipo = this.buscarTipo(c.getJuros());
	}
	
	public ClienteDto() {

	}

	private String buscarTipo(BigDecimal juros) {
		
		return juros.compareTo(BigDecimal.ZERO) == 0 ? "A": 
			juros.compareTo(new BigDecimal(0.1)) == 0 ? "B" : "C" ;
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

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getJuros() {
		return juros;
	}

	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}
	
}
