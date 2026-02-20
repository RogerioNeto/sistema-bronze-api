package com.feita_debronze.feita_debronze.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoRequestDTO {
    private String nomeCliente;
    private String telefone;
    private String dataHora;
    private String procedimento;
    private String status;
    private String unidade; // Recebe o NOME da unidade como String
}
