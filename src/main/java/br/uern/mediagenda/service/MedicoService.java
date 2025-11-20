package br.uern.mediagenda.service;

import br.uern.mediagenda.model.Medico;
import br.uern.mediagenda.model.Especialidade;
import br.uern.mediagenda.repository.MedicoRepository;
import br.uern.mediagenda.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicoService {
    
    private final MedicoRepository medicoRepository;
    private final EspecialidadeService especialidadeService;
    
    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }
    
    public List<Medico> listarAtivos() {
        return medicoRepository.findByAtivoTrue();
    }
    
    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com ID: " + id));
    }
    
    public Medico buscarPorCrm(String crm) {
        return medicoRepository.findByCrm(crm)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com CRM: " + crm));
    }
    
    public List<Medico> listarPorEspecialidade(Long especialidadeId) {
        Especialidade especialidade = especialidadeService.buscarPorId(especialidadeId);
        return medicoRepository.findByEspecialidadeAndAtivoTrue(especialidade);
    }
    
    public Medico salvar(Medico medico) {
        validarMedico(medico);
        return medicoRepository.save(medico);
    }
    
    public Medico atualizar(Long id, Medico medico) {
        Medico medicoExistente = buscarPorId(id);
        
        if (!medicoExistente.getCrm().equals(medico.getCrm()) &&
            medicoRepository.existsByCrm(medico.getCrm())) {
            throw new IllegalArgumentException("CRM já cadastrado para outro médico");
        }
        
        medico.setId(id);
        return medicoRepository.save(medico);
    }
    
    public void deletar(Long id) {
        Medico medico = buscarPorId(id);
        medico.setAtivo(false);
        medicoRepository.save(medico);
    }
    
    private void validarMedico(Medico medico) {
        if (medico.getId() == null && medicoRepository.existsByCrm(medico.getCrm())) {
            throw new IllegalArgumentException("CRM já cadastrado");
        }
        
        if (medico.getEspecialidade() == null || medico.getEspecialidade().getId() == null) {
            throw new IllegalArgumentException("Especialidade é obrigatória");
        }
    }
}