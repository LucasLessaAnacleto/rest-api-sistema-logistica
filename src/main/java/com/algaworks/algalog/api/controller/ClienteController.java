package com.algaworks.algalog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.input.ClienteInput;
import com.algaworks.algalog.domain.services.CadastroCliente;

import java.util.List;

import javax.validation.Valid;

@RequestMapping("/clientes")
@RestController
public class ClienteController {

    @Autowired
    private CadastroCliente cadastroCliente;

    //metodos

    @GetMapping
    public List<ClienteModel> listarCliente() {

        return cadastroCliente.listar();

    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> buscarCliente(@PathVariable Long clienteId) {

       return cadastroCliente.buscar(clienteId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClienteModel adicionarCliente(@Valid @RequestBody ClienteInput cliente) {
        
        return cadastroCliente.adicionar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> atualizarCliente(@Valid @PathVariable Long clienteId,@Valid @RequestBody ClienteInput cliente) {

        return cadastroCliente.atualizar(clienteId,cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long clienteId) {

       return cadastroCliente.remover(clienteId);
    }

}
    