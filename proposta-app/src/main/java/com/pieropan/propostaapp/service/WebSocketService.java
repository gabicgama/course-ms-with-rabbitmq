package com.pieropan.propostaapp.service;

import com.pieropan.propostaapp.dto.PropostaResponseDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void notificar(PropostaResponseDto propostaResponseDto) {

        simpMessagingTemplate.convertAndSend("/propostas", propostaResponseDto);

    }
}
