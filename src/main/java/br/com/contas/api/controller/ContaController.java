package br.com.contas.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.contas.api.domain.model.Conta;
import br.com.contas.api.domain.repository.ContaRepository;
import br.com.contas.api.domain.service.ContaService;
import br.com.contas.api.input.TitularInput;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	ContaService contaService;
	
	//TODO: Usar no desenvolvimento para ver os valores cadastrados , tirar depois!!!
	
	@GetMapping
	public List<Conta> getAllContas() {
		return contaRepository.findAll();
	}
	
	
	@PostMapping("/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Conta> criarConta(@Valid @RequestBody TitularInput input){
		
		Conta conta = contaService.salvarConta(input);
		
		return ResponseEntity.ok(conta);
	}
}
