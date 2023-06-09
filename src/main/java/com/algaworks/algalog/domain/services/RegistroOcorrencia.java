package com.algaworks.algalog.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrencia {
    
    private CadastroEntrega cadastroEntrega;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {

        Entrega entrega = cadastroEntrega.pesquisaEntrega(entregaId);

        if (entrega.naoPodeSerFinalizada()) {
            throw new NegocioException("Entrega já foi finalizada");
        }

        return entrega.adicionarOcorrencia(descricao);

    }

    @Transactional
    public List<Ocorrencia> listar(Long entregaId){

        Entrega entrega = cadastroEntrega.pesquisaEntrega(entregaId);

        return entrega.getOcorrencias();
    }
}
