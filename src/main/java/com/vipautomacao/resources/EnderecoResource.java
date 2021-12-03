package com.vipautomacao.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vipautomacao.DTO.EnderecoDTO;
import com.vipautomacao.domain.Endereco;
import com.vipautomacao.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired 
	private EnderecoService service;
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> find(@PathVariable Integer codigo) {

		Endereco obj = service.find(codigo);
		return ResponseEntity.ok().body(obj);

	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Endereco>> findAll() {

		List<Endereco> list = service.findAll();																									
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Endereco> insert(@RequestBody EnderecoDTO enderecoDTO) {
		
		Endereco enderecoNovo = service.insert(enderecoDTO.getCep(), enderecoDTO.getNumero());																									
		return new ResponseEntity<Endereco>(enderecoNovo, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<Endereco> update(@PathVariable Integer codigo, @RequestBody EnderecoDTO enderecoDTO) {
		
		Endereco endereco = service.update(codigo, enderecoDTO.getCep(), enderecoDTO.getNumero());																									
		return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);

	}

	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer codigo) {
		service.delete(codigo);
		return ResponseEntity.noContent().build();	
	}
	
}
