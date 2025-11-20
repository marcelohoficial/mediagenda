package br.uern.mediagenda.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String nome;
    
    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Column(nullable = false, length = 20)
    private String telefone;
    
    @Email(message = "Email inválido")
    @Column(length = 100)
    private String email;
    
    @NotNull(message = "Data de nascimento é obrigatória")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Sexo sexo;
    
    @Column(length = 200)
    private String endereco;
    
    @Column(length = 50)
    private String cidade;
    
    @Column(length = 2)
    private String estado;
    
    @Column(length = 8)
    private String cep;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;
    
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
    
    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
    }
}