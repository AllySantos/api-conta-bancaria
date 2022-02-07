package br.com.contas.api.domain.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.contas.api.domain.exception.DomainException;
import br.com.contas.api.domain.model.Conta;
import br.com.contas.api.domain.model.Transferencia;
import br.com.contas.api.domain.repository.ContaRepository;
import br.com.contas.api.domain.repository.TransferenciaRepository;
import br.com.contas.api.dto.TransferenciaDTO;
import br.com.contas.api.dto.input.TransferenciaInput;

@Service
public class TransferenciaService {

	@Autowired
	ContaRepository contaRepository;

	@Autowired
	TransferenciaRepository transferenciaRepository;

	@Autowired
	ContaService contaService;

	@Autowired
	ModelMapper modelMapper;

	@Transactional
	public TransferenciaDTO realizarTransferencia(TransferenciaInput input) {

		Conta contaOrigem = isTransferenciaValida(input);

		Conta contaDestino = contaRepository.findById(input.getIdContaDestino()).orElse(null);

		// Atualiza saldo nas contas
		contaService.atualizarSaldoConta(contaOrigem, -input.getValor());
		contaService.atualizarSaldoConta(contaDestino, input.getValor());

		Transferencia novaTransferencia = new Transferencia();

		novaTransferencia.setContaOrigem(contaOrigem);
		novaTransferencia.setContaDestino(contaDestino);
		novaTransferencia.setData(LocalDateTime.now().withNano(0));
		novaTransferencia.setValor(input.getValor());

		transferenciaRepository.save(novaTransferencia);

		return modelMapper.map(novaTransferencia, TransferenciaDTO.class);

	}

	private Conta isTransferenciaValida(TransferenciaInput input) {
		if (input.getIdContaOrigem() != input.getIdContaDestino()) {

			if (isContaExistente(input.getIdContaOrigem()) && isContaExistente(input.getIdContaDestino())) {
				
				Conta contaOrigem = contaRepository.findById(input.getIdContaOrigem()).orElse(null);

				if (contaService.isSaldoDisponivel(contaOrigem.getSaldo(), input.getValor())) {
					return contaOrigem;
				}
				throw new DomainException("Saldo insuficente para realizar a transferência");
			}
			throw new DomainException("Conta de origem ou destino não existe");
		}
		throw new DomainException("Conta de origem e destino iguais");
	}

	private boolean isContaExistente(long idConta) {
		Conta conta = contaRepository.findById(idConta).orElse(null);

		return conta != null;

	}

}
