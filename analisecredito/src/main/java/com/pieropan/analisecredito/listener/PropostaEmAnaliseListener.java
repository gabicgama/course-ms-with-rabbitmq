package com.pieropan.analisecredito.listener;

import com.pieropan.analisecredito.domain.Proposta;
import com.pieropan.analisecredito.service.AnaliseCreditoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropostaEmAnaliseListener {

    private final AnaliseCreditoService analiseCreditoService;

    public PropostaEmAnaliseListener(AnaliseCreditoService analiseCreditoService) {
        this.analiseCreditoService = analiseCreditoService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void PropostaEmAnalise(Proposta proposta) {
        analiseCreditoService.analisar(proposta);
    }
}
