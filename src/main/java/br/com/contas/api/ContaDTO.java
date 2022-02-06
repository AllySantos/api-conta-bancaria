package br.com.contas.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {

	private long id;
	private float saldo;
	private String nomeTitular;
}
