package br.uern.mediagenda.config;

import br.uern.mediagenda.model.*;
import br.uern.mediagenda.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final UsuarioRepository usuarioRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final ConsultaRepository consultaRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            inicializarDados();
        }
    }
    
    private void inicializarDados() {
        System.out.println("Inicializando dados de exemplo...");
        
        // Criar usuários
        Usuario admin = Usuario.builder()
                .nome("Administrador")
                .login("admin")
                .senha(passwordEncoder.encode("admin123"))
                .email("admin@mediagenda.com")
                .perfil(PerfilUsuario.ADMINISTRADOR)
                .ativo(true)
                .build();
        
        Usuario recepcionista = Usuario.builder()
                .nome("Maria Recepcionista")
                .login("recep")
                .senha(passwordEncoder.encode("recep123"))
                .email("recep@mediagenda.com")
                .perfil(PerfilUsuario.RECEPCIONISTA)
                .ativo(true)
                .build();
        
        usuarioRepository.saveAll(Arrays.asList(admin, recepcionista));
        
        // Criar especialidades
        Especialidade cardiologia = Especialidade.builder()
                .nome("Cardiologia")
                .descricao("Especialidade médica que se dedica ao diagnóstico e tratamento das doenças do coração")
                .ativa(true)
                .build();
        
        Especialidade ortopedia = Especialidade.builder()
                .nome("Ortopedia")
                .descricao("Especialidade médica que cuida do sistema locomotor")
                .ativa(true)
                .build();
        
        Especialidade pediatria = Especialidade.builder()
                .nome("Pediatria")
                .descricao("Especialidade médica dedicada à assistência da criança e adolescente")
                .ativa(true)
                .build();
        
        especialidadeRepository.saveAll(Arrays.asList(cardiologia, ortopedia, pediatria));
        
        // Criar médicos
        Medico medico1 = Medico.builder()
                .nome("Dr. João Silva")
                .crm("12345-SP")
                .telefone("(11) 98765-4321")
                .email("joao.silva@mediagenda.com")
                .especialidade(cardiologia)
                .horaInicio("08:00")
                .horaFim("17:00")
                .duracaoConsulta(30)
                .ativo(true)
                .build();
        
        Medico medico2 = Medico.builder()
                .nome("Dra. Ana Santos")
                .crm("67890-SP")
                .telefone("(11) 98765-1234")
                .email("ana.santos@mediagenda.com")
                .especialidade(pediatria)
                .horaInicio("09:00")
                .horaFim("18:00")
                .duracaoConsulta(30)
                .ativo(true)
                .build();
        
        medicoRepository.saveAll(Arrays.asList(medico1, medico2));
        
        // Criar pacientes
        Paciente paciente1 = Paciente.builder()
                .nome("Carlos Eduardo Oliveira")
                .cpf("12345678901")
                .telefone("(11) 91234-5678")
                .email("carlos@email.com")
                .dataNascimento(LocalDate.of(1980, 5, 15))
                .sexo(Sexo.MASCULINO)
                .endereco("Rua das Flores, 123")
                .cidade("São Paulo")
                .estado("SP")
                .cep("01234567")
                .ativo(true)
                .build();
        
        Paciente paciente2 = Paciente.builder()
                .nome("Maria Fernanda Costa")
                .cpf("98765432109")
                .telefone("(11) 99876-5432")
                .email("maria@email.com")
                .dataNascimento(LocalDate.of(1990, 8, 20))
                .sexo(Sexo.FEMININO)
                .endereco("Av. Paulista, 456")
                .cidade("São Paulo")
                .estado("SP")
                .cep("01310000")
                .ativo(true)
                .build();
        
        pacienteRepository.saveAll(Arrays.asList(paciente1, paciente2));
        
        // Criar consultas de exemplo
        Consulta consulta1 = Consulta.builder()
                .paciente(paciente1)
                .medico(medico1)
                .dataHora(LocalDateTime.now().plusDays(2).withHour(10).withMinute(0))
                .status(StatusConsulta.AGENDADA)
                .observacoes("Primeira consulta")
                .build();
        
        Consulta consulta2 = Consulta.builder()
                .paciente(paciente2)
                .medico(medico2)
                .dataHora(LocalDateTime.now().plusDays(3).withHour(14).withMinute(30))
                .status(StatusConsulta.CONFIRMADA)
                .observacoes("Consulta de rotina")
                .build();
        
        consultaRepository.saveAll(Arrays.asList(consulta1, consulta2));
        
        System.out.println("Dados de exemplo criados com sucesso!");
        System.out.println("Usuário Admin - Login: admin | Senha: admin123");
        System.out.println("Usuário Recepcionista - Login: recep | Senha: recep123");
    }
}