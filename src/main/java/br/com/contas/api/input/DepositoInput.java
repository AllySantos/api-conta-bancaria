package br.com.contas.api.input;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositoInput {


	@NotNull
	private long idConta;
	

	@NotNull
	private float valor;
}
