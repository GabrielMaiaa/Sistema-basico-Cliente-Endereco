package com.vipautomacao.DTO;

public class ClienteDTO {
	
	private String nome;
	private String cpf;
	private String rg;
	
	private Integer codigoEndereco;
	
	public ClienteDTO(String nome, String cpf, String rg, Integer codigoEndereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.codigoEndereco = codigoEndereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Integer getCodigoEndereco() {
		return codigoEndereco;
	}

	public void setCodigoEndereco(Integer codigoEndereco) {
		this.codigoEndereco = codigoEndereco;
	}
	
	
}
