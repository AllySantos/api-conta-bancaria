package br.com.contas.api.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Titular {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(max = 100)
	private String nome;
	
	@NotBlank
	@Size(max = 11)
	private String cpf;
	
	
	public Titular(String nome, String cpf) {
		setNome(nome);
		setCpf(cpf);
	}
}
