package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.dto.request.ClienteRequest;
import com.algaworks.algalog.api.dto.response.ClienteResponse;
import com.algaworks.algalog.api.mapper.ClienteMapper;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CrudClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CrudClienteService crudClienteService;
	private ClienteMapper clienteMapper;
	
	@GetMapping
	public List<ClienteResponse> listar() {
		return clienteMapper.toCollectionmodel(clienteRepository.findAll());
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteResponse> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(clienteMapper.toModel(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteResponse adicionar(@Valid @RequestBody ClienteRequest cliente) {
		return clienteMapper.toModel(crudClienteService.salvar(clienteMapper.toEntity(cliente)));
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteResponse> alterar(@Valid @PathVariable Long clienteId, 
			@RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		
		cliente.setId(clienteId);
		cliente = crudClienteService.salvar(cliente);
		
		return ResponseEntity.ok(clienteMapper.toModel(cliente));
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		
		crudClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	
}
