package com.algaworks.algalog.api.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algalog.api.dto.DestinatarioModel;
import com.algaworks.algalog.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaResponse {

	private Long id;
	private String nomeCliente;
	private BigDecimal taxa;
	private StatusEntrega statusEntrega;
	private DestinatarioModel destinatario;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	
}
