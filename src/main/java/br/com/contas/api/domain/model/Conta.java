package br.com.contas.api.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.PositiveOrZero;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Conta {
	
	@EqualsAndHashCode.Include
	@SequenceGenerator(name="conta_sequence", initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="conta_sequence")
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
