package com.pieropan.propostaapp.listener;

import com.pieropan.propostaapp.dto.PropostaResponseDto;
import com.pieropan.propostaapp.entity.Proposta;
import com.pieropan.propostaapp.mapper.PropostaMapper;
import com.pieropan.propostaapp.repository.PropostaRepository;
import com.pieropan.propostaapp.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    private final PropostaRepository propostaRepository;

    private final WebSocketService webSocketService;

    public PropostaConcluidaListener(PropostaRepository propostaRepository, WebSocketService webSocketService) {
        this.propostaRepository = propostaRepository;
        this.webSocketService = webSocketService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        atualizarProposta(proposta);
        PropostaResponseDto propostaResponseDto = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
        webSocketService.notificar(propostaResponseDto);
    }

    private void atualizarProposta(Proposta proposta){
        propostaRepository.atualizarProposta(proposta.getId(), proposta.getAprovada(), proposta.getObservacao());
    }
}
