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

import br.com.contas.api.domain.service.TransferenciaService;
import br.com.contas.api.dto.TransferenciaDTO;
import br.com.contas.api.dto.input.TransferenciaInput;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<TransferenciaDTO> realizarTransferencia(@Valid @RequestBody TransferenciaInput input){
		
		TransferenciaDTO transferencia = transferenciaService.realizarTransferencia(input);
		
		return ResponseEntity.ok(transferencia);
	}
}
