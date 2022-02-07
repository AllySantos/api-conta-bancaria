package br.com.contas.api.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.contas.api.domain.exception.DomainException;
import br.com.contas.api.domain.model.Titular;
import br.com.contas.api.domain.repository.TitularRepository;
import br.com.contas.api.dto.input.TitularInput;

@Service
public class TitularService {

	CPFValidator cpfValidator;

	@Autowired
	TitularRepository titularRepository;
	
	@Transactional
	public Titular salvarTitular(TitularInput input) {
		
		if(isNomeCompleto(input.getNome())) {
			if(isCPFValido(input.getCpf())) {
				
				String regexCpf = "[^0-9]";
				
				String cpf = input.getCpf().replaceAll(regexCpf, "");
				
				Titular titularExiste = titularRepository.findByCpf(cpf).orElse(null);
						
				
				if(titularExiste == null) {
					Titular novoTitular = new Titular(input.getNome(), cpf);

					return titularRepository.save(novoTitular);
				}
			
				
				throw new DomainException("Já existe uma conta com este CPF");	
			}
			
			
			throw new DomainException("CPF digitado foi invalido");
		}
		
		throw new DomainException("É necessário o nome completo para abrir uma conta");

		
	}
	

	private boolean isNomeCompleto(String nome) {
		return nome.contains(" ");
	}
	
	
	private boolean isCPFValido(String cpf) {
		
		cpfValidator = new CPFValidator();
		cpfValidator.assertValid(cpf);
		
		List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf); 
		
		if(erros.size() > 0) {
			
			throw new DomainException(listaErros(erros));
		}

		return true;
	}
	
	private String listaErros(List<ValidationMessage> lista) {
		String erros = null;
		
		for(ValidationMessage erro : lista) {
			erros += erro.getMessage() + "\n";
		}
		
		return erros;
	}
}
