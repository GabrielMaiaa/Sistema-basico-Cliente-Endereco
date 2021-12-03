package com.vipautomacao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.vipautomacao.DTO.ClienteDTO;
import com.vipautomacao.domain.Cliente;
import com.vipautomacao.domain.Endereco;
import com.vipautomacao.repositories.ClienteRepository;
import com.vipautomacao.services.exceptions.DataIntegrityException;
import com.vipautomacao.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	public Cliente find(Integer codigo) {
		Optional<Cliente> obj = clienteRepository.findById(codigo);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + codigo + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente insert(Cliente obj) {
		obj.setCodigo(null);
		return clienteRepository.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		Cliente newCli = find(obj.getCodigo()); 
		updateData(newCli, obj);
		return clienteRepository.save(newCli);
	}
	
	public void delete(Integer codigo) {
		find(codigo);
		try {
			clienteRepository.deleteById(codigo);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir!");
		}
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO, Integer codigo) {
		Endereco end = new Endereco();
		end = enderecoService.find(clienteDTO.getCodigoEndereco());
		Cliente cli = new Cliente(codigo, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getRg(), end);
		return cli;
	}
	
	private void updateData(Cliente newCli, Cliente cli) {
		newCli.setNome(cli.getNome());
		newCli.setEndereco(cli.getEndereco());
	}
}
