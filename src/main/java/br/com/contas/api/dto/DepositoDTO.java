package br.com.contas.api.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositoDTO {

	private ContaDTO conta;
	private float valor;
	private LocalDateTime data;
	
}
