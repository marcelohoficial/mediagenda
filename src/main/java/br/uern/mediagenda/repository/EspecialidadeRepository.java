package br.uern.mediagenda.repository;

import br.uern.mediagenda.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    
    Optional<Especialidade> findByNome(String nome);
    
    List<Especialidade> findByAtivaTrue();
    
    boolean existsByNome(String nome);
}