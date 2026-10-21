package com.pieropan.notifacao.listener;

import com.pieropan.notifacao.constante.MensagemConstante;
import com.pieropan.notifacao.domain.Proposta;
import com.pieropan.notifacao.service.NotificacaoSnsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    private final NotificacaoSnsService notificacaoSnsService;

    public PropostaConcluidaListener(NotificacaoSnsService notificacaoSnsService) {
        this.notificacaoSnsService = notificacaoSnsService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        String mensagem;
        if(proposta.getAprovada()){
            mensagem = String.format(MensagemConstante.PROPOSTA_APROVADA, proposta.getUsuario().getNome());
        } else {
            mensagem = String.format(MensagemConstante.PROPOSTA_NEGADA, proposta.getUsuario().getNome(), proposta.getObservacao());
        }
//        notificacaoSnsService.notificar(proposta.getUsuario().getTelefone(),mensagem);
        System.out.println(mensagem);
    }
}
