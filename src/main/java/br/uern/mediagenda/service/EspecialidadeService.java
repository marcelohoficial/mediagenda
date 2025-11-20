package br.uern.mediagenda.service;

import br.uern.mediagenda.model.Especialidade;
import br.uern.mediagenda.repository.EspecialidadeRepository;
import br.uern.mediagenda.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EspecialidadeService {
    
    private final EspecialidadeRepository especialidadeRepository;
    
    public List<Especialidade> listarTodas() {
        return especialidadeRepository.findAll();
    }
    
    public List<Especialidade> listarAtivas() {
        return especialidadeRepository.findByAtivaTrue();
    }
    
    public Especialidade buscarPorId(Long id) {
        return especialidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada com ID: " + id));
    }
    
    public Especialidade salvar(Especialidade especialidade) {
        if (especialidadeRepository.existsByNome(especialidade.getNome())) {
            throw new IllegalArgumentException("Especialidade já cadastrada com este nome");
        }
        return especialidadeRepository.save(especialidade);
    }
    
    public Especialidade atualizar(Long id, Especialidade especialidade) {
        buscarPorId(id);
        especialidade.setId(id);
        return especialidadeRepository.save(especialidade);
    }
    
    public void deletar(Long id) {
        Especialidade especialidade = buscarPorId(id);
        especialidade.setAtiva(false);
        especialidadeRepository.save(especialidade);
    }
}