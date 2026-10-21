package com.pieropan.analisecredito.service;

import com.pieropan.analisecredito.domain.Proposta;
import com.pieropan.analisecredito.exceptions.StrategyException;
import com.pieropan.analisecredito.service.strategy.CalculoPonto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseCreditoService {

    private final List<CalculoPonto> calculoPontoList;

    private final NotificacaoRabbitService notificacaoRabbitService;

    private final String exchangePropostaConcluida;

    public AnaliseCreditoService(List<CalculoPonto> calculoPontoList, NotificacaoRabbitService notificacaoRabbitService,
                                 @Value("${rabbitmq.propostaconcluida.exchange}") String exchangePropostaConcluida) {
        this.calculoPontoList = calculoPontoList;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchangePropostaConcluida = exchangePropostaConcluida;
    }

    public void analisar(Proposta proposta) {
        try {
            int pontos = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
            proposta.setAprovada(pontos > 350);
        } catch (StrategyException e) {
            proposta.setAprovada(false);
            proposta.setObservacao(e.getMessage());
        }
        notificacaoRabbitService.notificar(exchangePropostaConcluida, proposta);
    }
}
