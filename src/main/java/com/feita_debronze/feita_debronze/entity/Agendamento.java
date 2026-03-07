package com.feita_debronze.feita_debronze.entity;

import jakarta.persistence.*;

@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente nomeCliente;

    private String dataHora;
    private String status;
    private String procedimento;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;

    // Campos transientes para facilitar a resposta JSON
    //@Transient
  //  private String nomeCliente;
    
    @Transient
    private String telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return nomeCliente;
    }

    public void setCliente(Cliente cliente) {
        this.nomeCliente = cliente;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    // Getters para os campos transientes que buscam do objeto Cliente
    public String getNomeCliente() {
        return nomeCliente != null ? nomeCliente.getNome() : null;
    }

    public String getTelefone() {
        return telefone != null ? telefone : null;
    }

    // Setters opcionais, caso precise popular manualmente (geralmente não necessário com @Transient)
    public void setNomeCliente (Cliente nomeCliente) { this.nomeCliente = nomeCliente; }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
