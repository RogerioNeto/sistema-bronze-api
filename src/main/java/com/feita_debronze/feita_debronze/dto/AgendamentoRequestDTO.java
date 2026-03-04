package com.feita_debronze.feita_debronze.dto;

public class AgendamentoRequestDTO {
    private String nomeCliente;
    private String telefone;
    private String dataHora;
    private String procedimento;
    private String status;
    private Long unidadeId;

    // Getters
    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public String getStatus() {
        return status;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    // Setters
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }
}
