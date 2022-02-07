package br.com.contas.api.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaDTO {

	private ContaDTO contaOrigem;
	
	private ContaDTO contaDestino;
	
	private float valor;
	
	private LocalDateTime data;
}
