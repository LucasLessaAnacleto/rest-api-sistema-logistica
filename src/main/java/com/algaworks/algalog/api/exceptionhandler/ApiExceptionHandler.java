package com.algaworks.algalog.api.exceptionhandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradoException;
import com.algaworks.algalog.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
    
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        List<Campo> campos = new ArrayList<>();

        for(ObjectError erro : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) erro).getField();
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());

            campos.add(new Campo(nome, mensagem));
        }

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento corretamente e tente novamente");
        problema.setCampos(campos);
        

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradoException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(value = { SQLIntegrityConstraintViolationException.class })
    protected ResponseEntity<Object> handleSQLIntegrityConstraintViolation( SQLIntegrityConstraintViolationException ex, WebRequest request ) {

        Problema problema = new Problema();
        problema.setStatus(HttpStatus.CONFLICT.value());
        problema.setDataHora(OffsetDateTime.now());
        problema.setTitulo("Violação da chave estrangeira: não é possível excluir esse cliente");

        return handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
