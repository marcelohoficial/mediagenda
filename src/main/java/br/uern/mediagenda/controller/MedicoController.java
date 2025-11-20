package br.uern.mediagenda.controller;

import br.uern.mediagenda.model.Medico;
import br.uern.mediagenda.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MedicoController {
    
    private final MedicoService medicoService;
    
    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }
    
    @GetMapping("/ativos")
    public ResponseEntity<List<Medico>> listarAtivos() {
        return ResponseEntity.ok(medicoService.listarAtivos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }
    
    @GetMapping("/especialidade/{especialidadeId}")
    public ResponseEntity<List<Medico>> listarPorEspecialidade(@PathVariable Long especialidadeId) {
        return ResponseEntity.ok(medicoService.listarPorEspecialidade(especialidadeId));
    }
    
    @PostMapping
    public ResponseEntity<Medico> cadastrar(@Valid @RequestBody Medico medico) {
        Medico novoMedico = medicoService.salvar(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMedico);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, 
                                            @Valid @RequestBody Medico medico) {
        return ResponseEntity.ok(medicoService.atualizar(id, medico));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}