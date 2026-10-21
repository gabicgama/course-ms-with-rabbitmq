package com.pieropan.analisecredito.service;

import com.pieropan.analisecredito.domain.Proposta;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoRabbitService {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoRabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notificar(String exchange, Proposta proposta) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
