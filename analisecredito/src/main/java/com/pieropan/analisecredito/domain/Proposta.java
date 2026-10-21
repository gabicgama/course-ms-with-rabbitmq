package com.pieropan.analisecredito.domain;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Proposta {

    private Long id;

    private Double valorSolicitado;

    private int prazoPagamento;

    private Boolean aprovada;

    private boolean integrada;

    private String observacao;

    private Usuario usuario;
}
