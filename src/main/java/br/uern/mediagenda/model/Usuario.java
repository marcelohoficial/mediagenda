package br.uern.mediagenda.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String nome;
    
    @NotBlank(message = "Login é obrigatório")
    @Size(min = 4, max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String login;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6)
    @Column(nullable = false)
    private String senha;
    
    @Email(message = "Email inválido")
    @Column(unique = true, length = 100)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PerfilUsuario perfil;
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;
    
    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        ultimaAtualizacao = LocalDateTime.now();
    }
}