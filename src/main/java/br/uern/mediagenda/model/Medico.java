package br.uern.mediagenda.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "medicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String nome;
    
    @NotBlank(message = "CRM é obrigatório")
    @Column(nullable = false, unique = true, length = 20)
    private String crm;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Column(nullable = false, length = 20)
    private String telefone;
    
    @Email(message = "Email inválido")
    @Column(length = 100)
    private String email;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "especialidade_id", nullable = false)
    private Especialidade especialidade;
    
    @Column(name = "hora_inicio", length = 5)
    private String horaInicio; // Formato: HH:mm
    
    @Column(name = "hora_fim", length = 5)
    private String horaFim; // Formato: HH:mm
    
    @Column(name = "duracao_consulta")
    private Integer duracaoConsulta = 30; // em minutos
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;
    
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
    
    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
    }
}