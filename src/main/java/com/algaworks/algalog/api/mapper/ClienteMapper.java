package com.algaworks.algalog.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.dto.request.ClienteRequest;
import com.algaworks.algalog.api.dto.response.ClienteResponse;
import com.algaworks.algalog.domain.model.Cliente;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteMapper {

	private ModelMapper modelMapper;
	
	public ClienteResponse toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteResponse.class);
	}
	
	public List<ClienteResponse> toCollectionmodel(List<Cliente> clientes) {
		return clientes.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Cliente toEntity(ClienteRequest clienteRequest) {
		return modelMapper.map(clienteRequest, Cliente.class);
	}
	
}
