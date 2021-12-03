package com.vipautomacao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vipautomacao.domain.Endereco;
import com.vipautomacao.domain.EnderecoViaCEP;
import com.vipautomacao.repositories.EnderecoRepository;
import com.vipautomacao.services.exceptions.DataIntegrityException;
import com.vipautomacao.services.exceptions.ObjectNotFoundException;


@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Endereco find(Integer codigo) {
		Optional<Endereco> obj = enderecoRepository.findById(codigo);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Endereço não encontrado! Id: " + codigo + ", Tipo: " + Endereco.class.getName()));
	}
	
	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}
	
	public Endereco insert(String cep, String numero) {
		EnderecoViaCEP endo = getInfoByCep(cep);
		Endereco newEnd = new Endereco(null, endo.getLogradouro() , numero, endo.getComplemento(), endo.getBairro(), endo.getLocalidade(), endo.getUf());
		return enderecoRepository.save(newEnd);
	}
	
	public Endereco update(Integer codigo, String cep, String numero) {
		find(codigo); 
		EnderecoViaCEP endo = getInfoByCep(cep);
		Endereco updEnd = new Endereco(codigo, endo.getLogradouro() , numero, endo.getComplemento(), endo.getBairro(), endo.getLocalidade(), endo.getUf());
		return enderecoRepository.save(updEnd);
	}
	
	public void delete(Integer codigo) {
		find(codigo);
		try {
			enderecoRepository.deleteById(codigo);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir!");
		}
	}
	
	private EnderecoViaCEP getInfoByCep(String cep) {
	    String uri = String.format("http://viacep.com.br/ws/%s/json/", cep);
        RestTemplate restTemplate = new RestTemplate();
        EnderecoViaCEP infoByCep = restTemplate.getForObject(uri , EnderecoViaCEP.class);
        return infoByCep;
	}
	
	
}
