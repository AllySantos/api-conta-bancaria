package br.com.contas.api.input;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferenciaInput {

	@NotNull
	private long idContaOrigem;
	
	@NotNull
	private long idContaDestino;
	
	@NotNull
	private float valor;
}
