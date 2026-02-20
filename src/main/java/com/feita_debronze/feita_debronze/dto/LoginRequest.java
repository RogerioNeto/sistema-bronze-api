package com.feita_debronze.feita_debronze.dto;

public class LoginRequest {
    private String usuario; // Alterado para corresponder ao front-end
    private String senha;   // Alterado para corresponder ao front-end

    // Getters and Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
