package br.com.contas.api.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.contas.api.domain.exception.DomainException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice	
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Problema.Campo> campos = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Problema.Campo(nome, mensagem));
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo("Campos inválidos: ");
		problema.setCampos(campos);
		
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleDomainException(DomainException dm, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(dm.getMessage());
		
		return handleExceptionInternal(dm, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(InvalidStateException.class)
	public ResponseEntity<Object> handleInvalidaException(InvalidStateException ise, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		
			List<Problema.Campo> campos = new ArrayList<>();
		
		for(ValidationMessage error : ise.getInvalidMessages()) {
			String nome = "CPF";
			String mensagem = error.getMessage();
			
			campos.add(new Problema.Campo(nome, mensagem));
		}
		
		problema.setTitulo("CPF invalido: ");
		problema.setCampos(campos);
		
		return handleExceptionInternal(ise, problema, new HttpHeaders(), status, request);
	}
}
