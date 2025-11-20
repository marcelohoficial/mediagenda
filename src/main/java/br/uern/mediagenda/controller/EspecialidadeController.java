package br.uern.mediagenda.controller;

import br.uern.mediagenda.model.Especialidade;
import br.uern.mediagenda.service.EspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EspecialidadeController {
    
    private final EspecialidadeService especialidadeService;
    
    @GetMapping
    public ResponseEntity<List<Especialidade>> listarTodas() {
        return ResponseEntity.ok(especialidadeService.listarTodas());
    }
    
    @GetMapping("/ativas")
    public ResponseEntity<List<Especialidade>> listarAtivas() {
        return ResponseEntity.ok(especialidadeService.listarAtivas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(especialidadeService.buscarPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<Especialidade> cadastrar(@Valid @RequestBody Especialidade especialidade) {
        Especialidade novaEspecialidade = especialidadeService.salvar(especialidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEspecialidade);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> atualizar(@PathVariable Long id, 
                                                    @Valid @RequestBody Especialidade especialidade) {
        return ResponseEntity.ok(especialidadeService.atualizar(id, especialidade));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        especialidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}