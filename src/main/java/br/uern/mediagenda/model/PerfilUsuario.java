package br.uern.mediagenda.model;

public enum PerfilUsuario {
    ADMINISTRADOR("Administrador"),
    RECEPCIONISTA("Recepcionista"),
    MEDICO("Médico");
    
    private String descricao;
    
    PerfilUsuario(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}