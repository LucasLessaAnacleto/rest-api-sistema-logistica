package com.algaworks.algalog.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntrega {
    
    private CadastroEntrega cadastroEntrega;

    @Transactional
    public void finalizar(Long entregaId) {

        Entrega entrega = cadastroEntrega.pesquisaEntrega(entregaId);

        entrega.statusFinalizar();

        cadastroEntrega.salvar(entrega);
    }
}
