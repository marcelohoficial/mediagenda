package br.uern.mediagenda.model;

public enum StatusConsulta {
    AGENDADA("Agendada"),
    CONFIRMADA("Confirmada"),
    REALIZADA("Realizada"),
    CANCELADA("Cancelada"),
    FALTA("Falta do Paciente");
    
    private String descricao;
    
    StatusConsulta(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}