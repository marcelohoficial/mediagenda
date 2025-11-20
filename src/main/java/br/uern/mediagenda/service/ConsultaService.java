package br.uern.mediagenda.service;

import br.uern.mediagenda.model.*;
import br.uern.mediagenda.repository.ConsultaRepository;
import br.uern.mediagenda.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsultaService {
    
    private final ConsultaRepository consultaRepository;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    
    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }
    
    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + id));
    }
    
    public List<Consulta> listarPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }
    
    public List<Consulta> listarPorMedico(Long medicoId) {
        Medico medico = medicoService.buscarPorId(medicoId);
        return consultaRepository.findByMedico(medico);
    }
    
    public List<Consulta> listarPorStatus(StatusConsulta status) {
        return consultaRepository.findByStatus(status);
    }
    
    public List<Consulta> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.findByDataHoraBetween(inicio, fim);
    }
    
    public List<Consulta> listarConsultasDoDia(Long medicoId, LocalDate data) {
        LocalDateTime dataHora = data.atStartOfDay();
        return consultaRepository.findConsultasMedicoDia(medicoId, dataHora);
    }
    
    public Consulta agendar(Consulta consulta) {
        validarConsulta(consulta);
        verificarDisponibilidade(consulta);
        consulta.setStatus(StatusConsulta.AGENDADA);
        return consultaRepository.save(consulta);
    }
    
    public Consulta atualizar(Long id, Consulta consulta) {
        buscarPorId(id);
        consulta.setId(id);
        verificarDisponibilidade(consulta);
        return consultaRepository.save(consulta);
    }
    
    public Consulta confirmar(Long id) {
        Consulta consulta = buscarPorId(id);
        if (consulta.getStatus() != StatusConsulta.AGENDADA) {
            throw new IllegalStateException("Apenas consultas agendadas podem ser confirmadas");
        }
        consulta.setStatus(StatusConsulta.CONFIRMADA);
        return consultaRepository.save(consulta);
    }
    
    public Consulta realizar(Long id, String diagnostico) {
        Consulta consulta = buscarPorId(id);
        if (consulta.getStatus() == StatusConsulta.CANCELADA) {
            throw new IllegalStateException("Consulta cancelada não pode ser realizada");
        }
        consulta.setStatus(StatusConsulta.REALIZADA);
        consulta.setDiagnostico(diagnostico);
        return consultaRepository.save(consulta);
    }
    
    public Consulta cancelar(Long id, String motivo) {
        Consulta consulta = buscarPorId(id);
        if (consulta.getStatus() == StatusConsulta.REALIZADA) {
            throw new IllegalStateException("Consulta já realizada não pode ser cancelada");
        }
        consulta.setStatus(StatusConsulta.CANCELADA);
        consulta.setObservacoes(motivo);
        return consultaRepository.save(consulta);
    }
    
    public void registrarFalta(Long id) {
        Consulta consulta = buscarPorId(id);
        consulta.setStatus(StatusConsulta.FALTA);
        consultaRepository.save(consulta);
    }
    
    private void validarConsulta(Consulta consulta) {
        if (consulta.getPaciente() == null || consulta.getPaciente().getId() == null) {
            throw new IllegalArgumentException("Paciente é obrigatório");
        }
        
        if (consulta.getMedico() == null || consulta.getMedico().getId() == null) {
            throw new IllegalArgumentException("Médico é obrigatório");
        }
        
        if (consulta.getDataHora() == null) {
            throw new IllegalArgumentException("Data e hora são obrigatórias");
        }
        
        if (consulta.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível agendar consultas para datas passadas");
        }
        
        pacienteService.buscarPorId(consulta.getPaciente().getId());
        
        Medico medico = medicoService.buscarPorId(consulta.getMedico().getId());
        if (!medico.getAtivo()) {
            throw new IllegalArgumentException("Médico não está ativo");
        }
    }
    
    private void verificarDisponibilidade(Consulta consulta) {
        boolean existe = consultaRepository.existsConflito(
            consulta.getMedico(),
            consulta.getDataHora(),
            consulta.getId()
        );
        
        if (existe) {
            throw new IllegalStateException("Já existe uma consulta agendada para este médico neste horário");
        }
    }
}