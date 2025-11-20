package br.uern.mediagenda.controller;

import br.uern.mediagenda.model.Consulta;
import br.uern.mediagenda.model.StatusConsulta;
import br.uern.mediagenda.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ConsultaController {
    
    private final ConsultaService consultaService;
    
    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodas() {
        return ResponseEntity.ok(consultaService.listarTodas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }
    
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Consulta>> listarPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaService.listarPorPaciente(pacienteId));
    }
    
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Consulta>> listarPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(consultaService.listarPorMedico(medicoId));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Consulta>> listarPorStatus(@PathVariable StatusConsulta status) {
        return ResponseEntity.ok(consultaService.listarPorStatus(status));
    }
    
    @GetMapping("/periodo")
    public ResponseEntity<List<Consulta>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(consultaService.listarPorPeriodo(inicio, fim));
    }
    
    @GetMapping("/medico/{medicoId}/dia/{data}")
    public ResponseEntity<List<Consulta>> listarConsultasDoDia(
            @PathVariable Long medicoId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(consultaService.listarConsultasDoDia(medicoId, data));
    }
    
    @PostMapping
    public ResponseEntity<Consulta> agendar(@Valid @RequestBody Consulta consulta) {
        Consulta novaConsulta = consultaService.agendar(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConsulta);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, 
                                               @Valid @RequestBody Consulta consulta) {
        return ResponseEntity.ok(consultaService.atualizar(id, consulta));
    }
    
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Consulta> confirmar(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.confirmar(id));
    }
    
    @PutMapping("/{id}/realizar")
    public ResponseEntity<Consulta> realizar(@PathVariable Long id, 
                                              @RequestBody Map<String, String> body) {
        String diagnostico = body.get("diagnostico");
        return ResponseEntity.ok(consultaService.realizar(id, diagnostico));
    }
    
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Consulta> cancelar(@PathVariable Long id, 
                                              @RequestBody Map<String, String> body) {
        String motivo = body.get("motivo");
        return ResponseEntity.ok(consultaService.cancelar(id, motivo));
    }
    
    @PutMapping("/{id}/falta")
    public ResponseEntity<Void> registrarFalta(@PathVariable Long id) {
        consultaService.registrarFalta(id);
        return ResponseEntity.ok().build();
    }
}