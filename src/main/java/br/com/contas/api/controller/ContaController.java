package br.com.contas.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.contas.api.domain.model.Titular;
import br.com.contas.api.domain.service.TitularService;
import br.com.contas.api.dto.input.TitularInput;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	TitularService titularService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Titular> criarConta(@Valid @RequestBody TitularInput input){
		
		Titular titular = titularService.salvarTitular(input);
		
		return ResponseEntity.ok(titular);
	}
}
