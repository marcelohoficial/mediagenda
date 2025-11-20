package br.uern.mediagenda.repository;

import br.uern.mediagenda.model.Medico;
import br.uern.mediagenda.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
    Optional<Medico> findByCrm(String crm);
    
    List<Medico> findByAtivoTrue();
    
    List<Medico> findByEspecialidade(Especialidade especialidade);
    
    List<Medico> findByEspecialidadeAndAtivoTrue(Especialidade especialidade);
    
    @Query("SELECT m FROM Medico m WHERE m.especialidade.id = :especialidadeId AND m.ativo = true")
    List<Medico> findByEspecialidadeId(@Param("especialidadeId") Long especialidadeId);
    
    List<Medico> findByNomeContainingIgnoreCase(String nome);
    
    boolean existsByCrm(String crm);
}