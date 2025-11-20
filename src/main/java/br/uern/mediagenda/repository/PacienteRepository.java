package br.uern.mediagenda.repository;

import br.uern.mediagenda.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    Optional<Paciente> findByCpf(String cpf);
    
    List<Paciente> findByAtivoTrue();
    
    List<Paciente> findByNomeContainingIgnoreCase(String nome);
    
    @Query("SELECT p FROM Paciente p WHERE p.ativo = true AND " +
           "(LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "p.cpf LIKE CONCAT('%', :termo, '%'))")
    List<Paciente> buscarPorNomeOuCpf(@Param("termo") String termo);
    
    boolean existsByCpf(String cpf);
}