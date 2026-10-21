package com.pieropan.analisecredito.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Usuario {

    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private Double renda;
}
