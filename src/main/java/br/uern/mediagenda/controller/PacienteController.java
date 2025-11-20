package br.uern.mediagenda.controller;

import br.uern.mediagenda.model.Paciente;
import br.uern.mediagenda.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PacienteController {
    
    private final PacienteService pacienteService;
    
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }
    
    @GetMapping("/ativos")
    public ResponseEntity<List<Paciente>> listarAtivos() {
        return ResponseEntity.ok(pacienteService.listarAtivos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }
    
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Paciente> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(pacienteService.buscarPorCpf(cpf));
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Paciente>> buscarPorNomeOuCpf(@RequestParam String termo) {
        return ResponseEntity.ok(pacienteService.buscarPorNomeOuCpf(termo));
    }
    
    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@Valid @RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.salvar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, 
                                               @Valid @RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.atualizar(id, paciente));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable Long id) {
        pacienteService.reativar(id);
        return ResponseEntity.ok().build();
    }
}