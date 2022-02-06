package br.com.contas.api.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
	
	@EqualsAndHashCode.Include

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	@PositiveOrZero
	private float saldo;
	
	@OneToOne
	private Titular titular;
	
	
	public Conta(Titular titular) {
		setSaldo(0);
		setTitular(titular);
	}

}
