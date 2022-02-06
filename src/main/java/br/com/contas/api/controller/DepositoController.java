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

import br.com.contas.api.domain.model.Deposito;
import br.com.contas.api.domain.service.DepositoService;
import br.com.contas.api.input.DepositoInput;

@RestController
@RequestMapping("/depositos")
public class DepositoController {

	@Autowired
	DepositoService depositoService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Deposito> realizarDeposito(@Valid @RequestBody DepositoInput input) {
		
		Deposito deposito = depositoService.realizarDeposito(input);
		
		return ResponseEntity.ok(deposito);
	}
}