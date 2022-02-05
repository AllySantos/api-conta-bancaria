package br.com.contas.api.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.contas.api.domain.model.Conta;
import br.com.contas.api.domain.model.Titular;
import br.com.contas.api.domain.repository.ContaRepository;
import br.com.contas.api.dto.input.TitularInput;

@Service
public class ContaService {

	@Autowired
	TitularService titularService;
	
	@Autowired
	ContaRepository contaRepository;
	
	@Transactional
	public Conta salvarConta(TitularInput input) {
		
		Titular titular = titularService.salvarTitular(input);
		
		Conta novaConta = new Conta(titular);
		
		return contaRepository.save(novaConta);
		
	}
}
