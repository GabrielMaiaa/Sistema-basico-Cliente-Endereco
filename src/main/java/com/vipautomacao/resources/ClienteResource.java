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

import com.vipautomacao.DTO.ClienteDTO;
import com.vipautomacao.domain.Cliente;
import com.vipautomacao.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll() {

		List<Cliente> list = service.findAll();																									
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer codigo) {

		Cliente obj = service.find(codigo);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Cliente> insert(@RequestBody ClienteDTO objDTO) {
		Cliente obj = service.fromDTO(objDTO, objDTO.getCodigoEndereco());
		obj = service.insert(obj);
																									
		return new ResponseEntity<Cliente>(obj, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ClienteDTO objDto, @PathVariable Integer codigo) {
		Cliente obj = service.fromDTO(objDto, objDto.getCodigoEndereco());
		obj.setCodigo(codigo);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		}
	
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer codigo) {
		service.delete(codigo);
		return ResponseEntity.noContent().build();	
	}
}
