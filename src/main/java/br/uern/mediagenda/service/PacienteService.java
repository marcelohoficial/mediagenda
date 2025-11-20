package br.uern.mediagenda.service;

import br.uern.mediagenda.model.Paciente;
import br.uern.mediagenda.repository.PacienteRepository;
import br.uern.mediagenda.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PacienteService {
    
    private final PacienteRepository pacienteRepository;
    
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
    
    public List<Paciente> listarAtivos() {
        return pacienteRepository.findByAtivoTrue();
    }
    
    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + id));
    }
    
    public Paciente buscarPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com CPF: " + cpf));
    }
    
    public List<Paciente> buscarPorNome(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    public List<Paciente> buscarPorNomeOuCpf(String termo) {
        return pacienteRepository.buscarPorNomeOuCpf(termo);
    }
    
    public Paciente salvar(Paciente paciente) {
        validarPaciente(paciente);
        return pacienteRepository.save(paciente);
    }
    
    public Paciente atualizar(Long id, Paciente paciente) {
        Paciente pacienteExistente = buscarPorId(id);
        
        // Valida se o CPF já existe para outro paciente
        if (!pacienteExistente.getCpf().equals(paciente.getCpf()) &&
            pacienteRepository.existsByCpf(paciente.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado para outro paciente");
        }
        
        paciente.setId(id);
        return pacienteRepository.save(paciente);
    }
    
    public void deletar(Long id) {
        Paciente paciente = buscarPorId(id);
        // Exclusão lógica
        paciente.setAtivo(false);
        pacienteRepository.save(paciente);
    }
    
    public void reativar(Long id) {
        Paciente paciente = buscarPorId(id);
        paciente.setAtivo(true);
        pacienteRepository.save(paciente);
    }
    
    private void validarPaciente(Paciente paciente) {
        if (paciente.getId() == null && pacienteRepository.existsByCpf(paciente.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        
        if (paciente.getNome() == null || paciente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        
        if (paciente.getCpf() == null || !paciente.getCpf().matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }
}