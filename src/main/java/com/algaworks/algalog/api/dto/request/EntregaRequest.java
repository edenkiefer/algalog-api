package com.algaworks.algalog.api.dto.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.algaworks.algalog.api.dto.DestinatarioModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaRequest {

	@NotNull
	private Long clienteId;
	
	@Valid
	@NotNull
	private DestinatarioModel destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
}
