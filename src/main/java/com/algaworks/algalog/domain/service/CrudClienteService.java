package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudClienteService {

	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
			.orElseThrow(() -> new NegocioException("O cliente informado não existe"));

	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		if (!clienteRepository.findByEmail(cliente.getEmail()).isEmpty())
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
