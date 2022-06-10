package com.algaworks.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algalog.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro.Campo> campos = new ArrayList<>();
		
		ex.getBindingResult().getAllErrors().forEach(erro -> {
			campos.add(new Erro.Campo(((FieldError)erro).getField(), 
					messageSource.getMessage(erro, LocaleContextHolder.getLocale())));
		});
		
		Erro erro = new Erro(status.value(), 
				OffsetDateTime.now(), 
				"Um ou mais campos estão inválidos", 
				campos);
		
		return handleExceptionInternal(ex, erro, headers, status, request);
	}
	
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Erro erro = new Erro(status.value(), 
				OffsetDateTime.now(), 
				ex.getMessage(), 
				null);
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
}
