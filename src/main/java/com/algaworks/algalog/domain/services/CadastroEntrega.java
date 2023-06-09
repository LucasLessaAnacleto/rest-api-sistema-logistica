package com.algaworks.algalog.domain.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradoException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroEntrega {
    
    private CadastroCliente cadastroCliente;
    private EntregaRepository entregaRepository;
    private EntregaAssembler entregaAssembler;

    // SOLICITAR
    @Transactional
    public EntregaModel solicitar(EntregaInput entregaInput){

        Entrega entrega = entregaAssembler.toEntity(entregaInput);
        Cliente cliente = cadastroCliente.pesquisar(entrega.getCliente().getId());
        
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        entrega.iniciaEntrega();

        Entrega entregaSolicitada = entregaRepository.save(entrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }

    // LISTAR
    @Transactional
    public List<EntregaModel> listar(){
        
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    // BUSCAR
    @Transactional
    public ResponseEntity<EntregaModel> buscar(Long entregaId) {

        return entregaRepository.findById(entregaId)
                    .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                    .orElse(ResponseEntity.notFound().build());
    }

    // PESQUISA ENTREGA
    public Entrega pesquisaEntrega(Long entregaId) {
        
        return entregaRepository.findById(entregaId).orElseThrow(() -> new EntidadeNaoEncontradoException("Entrega não encontrada"));
    }

    // SAVE DA ENTREGA
    public Entrega salvar(Entrega entrega) {
        return entregaRepository.save(entrega);
    }
}