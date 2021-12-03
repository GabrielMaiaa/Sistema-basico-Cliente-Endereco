package com.vipautomacao.DTO;

public class EnderecoDTO {

	private String cep;
	private String numero;
	
	public EnderecoDTO() {
	}

	public EnderecoDTO(String cep, String numero) {
		super();
		this.cep = cep;
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	
}
