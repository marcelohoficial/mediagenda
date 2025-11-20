package br.uern.mediagenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

/**
 * Classe principal da aplicação MediAgenda
 * Sistema de Gestão e Agendamento de Consultas Médicas
 * 
 * @author Marcelo Henrique Lima Silva
 * @version 1.0
 * @since 2025
 */
@SpringBootApplication
public class MediAgendaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediAgendaApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("MediAgenda - Sistema Iniciado!");
        System.out.println("Acesse: http://localhost:8080/mediagenda");
        System.out.println("========================================\n");
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}