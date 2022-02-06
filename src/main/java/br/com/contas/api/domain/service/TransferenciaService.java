package br.com.contas.api.domain.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.contas.api.domain.exception.DomainException;
import br.com.contas.api.domain.model.Conta;
import br.com.contas.api.domain.model.Transferencia;
import br.com.contas.api.domain.repository.ContaRepository;
import br.com.contas.api.domain.repository.TransferenciaRepository;
import br.com.contas.api.input.TransferenciaInput;

@Service
public class TransferenciaService {

	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	TransferenciaRepository transferenciaRepository;
	
	@Autowired
	ContaService contaService;
	
	
	
	@Transactional
	public Transferencia realizarTransferencia(TransferenciaInput input) {
	
		if(isContaExistente(input.getIdContaOrigem()) 
		&& isContaExistente(input.getIdContaDestino())) {
			
			Conta contaOrigem = contaRepository.findById(input.getIdContaOrigem()).orElse(null);

			if(contaService.isSaldoDisponivel(contaOrigem.getSaldo(), input.getValor())) {
				
				Transferencia novaTransferencia = new Transferencia();
				
				Conta contaDestino= contaRepository.findById(input.getIdContaDestino()).orElse(null);

				contaService.atualizarSaldoConta(contaOrigem, -input.getValor());
				contaService.atualizarSaldoConta(contaDestino, input.getValor());
				
				novaTransferencia.setContaOrigem(contaOrigem);
				novaTransferencia.setContaDestino(contaDestino);
				novaTransferencia.setData(LocalDateTime.now());
				novaTransferencia.setValor(input.getValor());
				
				return transferenciaRepository.save(novaTransferencia);
				
			}
			
			throw new DomainException("Saldo insuficente para realizar a transferência");
	
		}
		
		throw new DomainException("Conta de origem ou destino não existe");
	}
	
	
	private boolean isContaExistente(long idConta) {
		Conta conta = contaRepository.findById(idConta).orElse(null);
		
		return conta != null;

	}
	
	
}
