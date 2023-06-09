package com.algaworks.algalog.domain.services;

import com.algaworks.algalog.api.assembler.ClienteAssembler;
import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.input.ClienteInput;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.repository.ClienteRepository;

@Service
public class CadastroCliente {
    
    @Autowired
    private ClienteAssembler clienteAssembler;

    @Autowired  
    private ClienteRepository clienteRepository;


    @Transactional
    public Cliente pesquisar(Long clienteId){

        return clienteRepository.findById(clienteId).orElseThrow(() -> new NegocioException("Cliente não existe"));
    }

    // CRUD

    @Transactional
    public ResponseEntity<Void> remover(Long clienteId){

        if (!clienteRepository.existsById(clienteId)) {
            
            return ResponseEntity.notFound().build();
            
        }

        clienteRepository.deleteById(clienteId);
        
        return ResponseEntity.noContent().build();
       
    }

    @Transactional
    public List<ClienteModel> listar() {
        
        return clienteAssembler.toCollectionModel(clienteRepository.findAll());
    } 

    @Transactional
    public ResponseEntity<ClienteModel> buscar(Long clienteId) {
 
        return clienteRepository.findById(clienteId).map(cliente -> {
            ClienteModel clienteModel = clienteAssembler.toModel(cliente);
            return ResponseEntity.ok(clienteModel);
        }).orElse(ResponseEntity.notFound().build());

    }

    @Transactional
    public ClienteModel adicionar(ClienteInput clienteInput) {

        Cliente cliente = clienteAssembler.toEntity(clienteInput);

        boolean existEmail = clienteRepository.findByEmail(cliente.getEmail()).isPresent();       
        
        if(existEmail){
           throw new NegocioException("Já existe um cliente cadastrado com esse email");
        }
        
        clienteRepository.save(cliente);
    
        return clienteAssembler.toModel(cliente);
    }

    @Transactional
    public ResponseEntity<ClienteModel> atualizar(Long clienteId,ClienteInput clienteInput) {

        Cliente cliente = clienteAssembler.toEntity(clienteInput);

        if (!clienteRepository.existsById(clienteId)){
            throw new NegocioException("Esse cliente não existe"); 
        }
        if (clienteRepository.findByEmail(cliente.getEmail()).stream().anyMatch(e -> !e.getId().equals(clienteId))){
            throw new NegocioException("Já existe um cliente cadastrado com esse email");
        }
        
        cliente.setId(clienteId);
        ClienteModel clienteAtualizado = clienteAssembler.toModel(clienteRepository.save(cliente));

        return ResponseEntity.ok(clienteAtualizado);
    }

   
}

    

