package br.uern.mediagenda.repository;

import br.uern.mediagenda.model.Consulta;
import br.uern.mediagenda.model.Medico;
import br.uern.mediagenda.model.Paciente;
import br.uern.mediagenda.model.StatusConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    
    List<Consulta> findByPaciente(Paciente paciente);
    
    List<Consulta> findByMedico(Medico medico);
    
    List<Consulta> findByStatus(StatusConsulta status);
    
    @Query("SELECT c FROM Consulta c WHERE c.dataHora BETWEEN :inicio AND :fim")
    List<Consulta> findByDataHoraBetween(@Param("inicio") LocalDateTime inicio, 
                                         @Param("fim") LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico = :medico AND " +
           "c.dataHora BETWEEN :inicio AND :fim AND " +
           "c.status NOT IN ('CANCELADA')")
    List<Consulta> findConsultasMedicoNoPeriodo(@Param("medico") Medico medico,
                                                 @Param("inicio") LocalDateTime inicio,
                                                 @Param("fim") LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :pacienteId " +
           "ORDER BY c.dataHora DESC")
    List<Consulta> findByPacienteId(@Param("pacienteId") Long pacienteId);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId " +
           "AND DATE(c.dataHora) = DATE(:data) " +
           "AND c.status NOT IN ('CANCELADA') " +
           "ORDER BY c.dataHora")
    List<Consulta> findConsultasMedicoDia(@Param("medicoId") Long medicoId,
                                           @Param("data") LocalDateTime data);
    
    @Query("SELECT COUNT(c) FROM Consulta c WHERE c.status = :status " +
           "AND c.dataHora BETWEEN :inicio AND :fim")
    Long countByStatusAndDataHoraBetween(@Param("status") StatusConsulta status,
                                         @Param("inicio") LocalDateTime inicio,
                                         @Param("fim") LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico.especialidade.id = :especialidadeId " +
           "AND c.dataHora BETWEEN :inicio AND :fim")
    List<Consulta> findByEspecialidadeAndPeriodo(@Param("especialidadeId") Long especialidadeId,
                                                  @Param("inicio") LocalDateTime inicio,
                                                  @Param("fim") LocalDateTime fim);
    
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Consulta c " +
           "WHERE c.medico = :medico AND c.dataHora = :dataHora " +
           "AND c.status NOT IN ('CANCELADA') AND " +
           "(:consultaId IS NULL OR c.id != :consultaId)")
    boolean existsConflito(@Param("medico") Medico medico,
                          @Param("dataHora") LocalDateTime dataHora,
                          @Param("consultaId") Long consultaId);
}