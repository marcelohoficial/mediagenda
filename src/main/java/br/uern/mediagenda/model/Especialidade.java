package br.uern.mediagenda.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "especialidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Especialidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome da especialidade é obrigatório")
    @Size(min = 3, max = 100)
    @Column(nullable = false, unique = true, length = 100)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false)
    private Boolean ativa = true;
    
    @OneToMany(mappedBy = "especialidade")
    private List<Medico> medicos;
}