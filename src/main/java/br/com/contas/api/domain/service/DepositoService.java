package br.com.contas.api.domain.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.contas.api.domain.exception.DomainException;
import br.com.contas.api.domain.model.Conta;
import br.com.contas.api.domain.model.Deposito;
import br.com.contas.api.domain.repository.ContaRepository;
import br.com.contas.api.domain.repository.DepositoRepository;
import br.com.contas.api.input.DepositoInput;

@Service
public class DepositoService {

	@Autowired
	ContaRepository contaRepository;
	@Autowired
	DepositoRepository depositoRepository;
	
	@Autowired
	ContaService contaService;
	
	@Transactional
	public Deposito realizarDeposito(DepositoInput input) {
		
		
		Conta conta = contaRepository.findById(input.getIdConta()).orElse(null);
		
		if(conta != null) {
			
			if(input.getValor() <= 2000) {
				Deposito novoDeposito = new Deposito();
				
				novoDeposito.setConta(conta);
				novoDeposito.setValor(input.getValor());
				novoDeposito.setData(LocalDateTime.now());
				
				contaService.atualizarSaldoConta(conta, input.getValor());
				
				return depositoRepository.save(novoDeposito);
			}
			
			throw new DomainException("Valor do depósito deve ser menor ou igual R$ 2000,00");
			
			
		}
		
		throw new DomainException("Conta não existe");
		
	}
}
