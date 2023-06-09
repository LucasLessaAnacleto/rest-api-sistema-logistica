package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.services.CadastroEntrega;
import com.algaworks.algalog.domain.services.FinalizacaoEntrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
    
    private CadastroEntrega cadastroEntrega;
    private FinalizacaoEntrega finalizacaoEntrega;

    @NotNull
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel cadastraEntrega(@Valid @RequestBody EntregaInput entregaInput){

        return cadastroEntrega.solicitar(entregaInput);
    }

    @GetMapping
    public List<EntregaModel> listaEntregas() {

        return cadastroEntrega.listar();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscaEntrega(@PathVariable Long entregaId){

        return cadastroEntrega.buscar(entregaId);
    }

    @PutMapping("/{entregaId}/finalizacao") 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizaEntrega(@PathVariable Long entregaId){

        finalizacaoEntrega.finalizar(entregaId);
    }
    
}
