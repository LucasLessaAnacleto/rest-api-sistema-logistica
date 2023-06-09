package com.algaworks.algalog.domain.exception;

public class EntidadeNaoEncontradoException extends NegocioException {
    
    private static final long serialVersionUID = 1L;
    public EntidadeNaoEncontradoException(String mensagem){
        super(mensagem);
    }

}
