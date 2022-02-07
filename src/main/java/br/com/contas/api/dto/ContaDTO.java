package br.com.contas.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {

	private long id;
	private float saldo;
	private String nomeTitular;
}
