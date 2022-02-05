package br.com.contas.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TitularInput {

	@NotBlank
	private String nome;	
	
	@NotBlank
	@Size(max = 14)
	private String cpf;
}
