package br.com.contas.api.domain.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.contas.api.domain.exception.DomainException;
import br.com.contas.api.domain.model.Conta;
import br.com.contas.api.domain.model.Deposito;
import br.com.contas.api.domain.repository.ContaRepository;
import br.com.contas.api.domain.repository.DepositoRepository;
import br.com.contas.api.dto.DepositoDTO;
import br.com.contas.api.dto.input.DepositoInput;

@Service
public class DepositoService {

	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	DepositoRepository depositoRepository;
	
	@Autowired
	ContaService contaService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Transactional
	public DepositoDTO realizarDeposito(DepositoInput input) {
		
		
		Conta conta = contaRepository.findById(input.getIdConta()).orElse(null);
		
		if(conta != null) {
			
			if(input.getValor() <= 2000) {
				Deposito novoDeposito = new Deposito();
				
				novoDeposito.setConta(conta);
				novoDeposito.setValor(input.getValor());
				novoDeposito.setData(LocalDateTime.now().withNano(0));
				
				contaService.atualizarSaldoConta(conta, input.getValor());
				
				depositoRepository.save(novoDeposito);
				
				return modelMapper.map(novoDeposito, DepositoDTO.class);
			}
			
			throw new DomainException("Valor do depósito deve ser menor ou igual R$ 2000,00");
			
			
		}
		
		throw new DomainException("Conta não existe");
		
	}
}
