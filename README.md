# MediAgenda - Sistema de Gestão e Agendamento de Consultas Médicas

## 📋 Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Cenário da Empresa Fictícia](#cenário-da-empresa-fictícia)
- [Arquitetura do Sistema](#arquitetura-do-sistema)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Requisitos](#requisitos)
- [Instalação e Configuração](#instalação-e-configuração)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Funcionalidades](#funcionalidades)
- [Casos de Uso](#casos-de-uso)
- [API Endpoints](#api-endpoints)
- [Como Executar](#como-executar)

---

## 🏥 Sobre o Projeto

**MediAgenda** é um sistema corporativo web desenvolvido para gerenciar agendamentos de consultas médicas, cadastro de pacientes, médicos e especialidades. O sistema facilita o controle de agendas médicas, evitando conflitos de horários e proporcionando uma gestão eficiente da clínica.

---

## 🎯 Cenário da Empresa Fictícia

**Empresa:** MediCare Clínicas Médicas  
**Segmento:** Prestação de serviços médicos  
**Problema:** Dificuldade em gerenciar agendamentos, controlar disponibilidade de médicos e manter histórico de consultas.

**Solução Proposta:**  
Desenvolvimento de um sistema web utilizando Java e Spring Boot com arquitetura MVC em três camadas:

1. **Camada de Modelo (Persistência):** Gerenciamento de dados com JPA/Hibernate e banco de dados relacional
2. **Camada de Controle (Negócios):** Lógica de negócio e regras da aplicação
3. **Camada de Visão (Aplicação):** Interface web responsiva com HTML5, CSS3 e JavaScript

---

## 🏗️ Arquitetura do Sistema

### Arquitetura MVC em Três Camadas

```
┌─────────────────────────────────────────┐
│         CAMADA DE VISÃO                 │
│    (Frontend - HTML/CSS/JS)             │
│  - Interface do Usuário                 │
│  - Formulários de Cadastro              │
│  - Visualização de Dados                │
└──────────────┬──────────────────────────┘
               │ HTTP Requests/Responses
┌──────────────▼──────────────────────────┐
│      CAMADA DE CONTROLE                 │
│    (Controllers - Spring MVC)           │
│  - REST Controllers                     │
│  - Validação de Requisições             │
│  - Roteamento                           │
└──────────────┬──────────────────────────┘
               │ Chamadas de Serviço
┌──────────────▼──────────────────────────┐
│      CAMADA DE NEGÓCIOS                 │
│        (Services)                       │
│  - Regras de Negócio                    │
│  - Validações                           │
│  - Transações                           │
└──────────────┬──────────────────────────┘
               │ Operações de Dados
┌──────────────▼──────────────────────────┐
│    CAMADA DE PERSISTÊNCIA               │
│   (Repositories - JPA/Hibernate)        │
│  - Acesso ao Banco de Dados             │
│  - Consultas SQL                        │
│  - Mapeamento Objeto-Relacional         │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│        BANCO DE DADOS                   │
│      (PostgreSQL/MariaDB)               │
└─────────────────────────────────────────┘
```

---

## 🛠️ Tecnologias Utilizadas

### Backend

- **Java** 11+
- **Spring Boot** 2.7+
- **Spring Data JPA**
- **Spring Security**
- **Spring Validation**
- **Maven** (Gerenciamento de dependências)

### Frontend

- **HTML5**
- **CSS3**
- **JavaScript (ES6+)**
- **Bootstrap 5** (Framework CSS)

### Banco de Dados

- **PostgreSQL** ou **MariaDB**

### Servidor

- **Apache Tomcat** (Embarcado no Spring Boot)

### Ferramentas

- **Eclipse IDE** 4.0+
- **Git/GitHub**
- **Postman** (Testes de API)

---

## 📦 Requisitos

### Requisitos de Software

- Java JDK 11 ou superior
- Maven 3.6+
- PostgreSQL 12+ ou MariaDB 10+
- Eclipse IDE 4.0+ (ou IntelliJ IDEA)
- Git

### Requisitos Mínimos do Sistema

✅ **Autenticação e Autorização**  
✅ **Transações da Empresa** (CRUD completo)  
✅ **Persistência de Dados**  
✅ **Consultas e Filtros**  
✅ **Relatórios**

---

## ⚙️ Instalação e Configuração

### 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/mediagenda.git
cd mediagenda
```

### 2. Configure o Banco de Dados

Crie um banco de dados PostgreSQL ou MariaDB:

```sql
CREATE DATABASE mediagenda;
CREATE USER mediagenda_user WITH PASSWORD 'senha123';
GRANT ALL PRIVILEGES ON DATABASE mediagenda TO mediagenda_user;
```

### 3. Configure o application.properties

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Configuração do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/mediagenda
spring.datasource.username=mediagenda_user
spring.datasource.password=senha123
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuração JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Configuração do Servidor
server.port=8080
server.servlet.context-path=/mediagenda

# Configuração de Encoding
spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
```

### 4. Instale as Dependências

```bash
mvn clean install
```

---

## 📁 Estrutura do Projeto

```
mediagenda/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── uern/
│   │   │           └── mediagenda/
│   │   │               ├── MediAgendaApplication.java
│   │   │               ├── config/
│   │   │               │   ├── SecurityConfig.java
│   │   │               │   └── CorsConfig.java
│   │   │               ├── model/
│   │   │               │   ├── Usuario.java
│   │   │               │   ├── Paciente.java
│   │   │               │   ├── Medico.java
│   │   │               │   ├── Especialidade.java
│   │   │               │   ├── Consulta.java
│   │   │               │   └── StatusConsulta.java
│   │   │               ├── repository/
│   │   │               │   ├── UsuarioRepository.java
│   │   │               │   ├── PacienteRepository.java
│   │   │               │   ├── MedicoRepository.java
│   │   │               │   ├── EspecialidadeRepository.java
│   │   │               │   └── ConsultaRepository.java
│   │   │               ├── service/
│   │   │               │   ├── UsuarioService.java
│   │   │               │   ├── PacienteService.java
│   │   │               │   ├── MedicoService.java
│   │   │               │   ├── EspecialidadeService.java
│   │   │               │   ├── ConsultaService.java
│   │   │               │   └── RelatorioService.java
│   │   │               ├── controller/
│   │   │               │   ├── UsuarioController.java
│   │   │               │   ├── PacienteController.java
│   │   │               │   ├── MedicoController.java
│   │   │               │   ├── EspecialidadeController.java
│   │   │               │   ├── ConsultaController.java
│   │   │               │   └── RelatorioController.java
│   │   │               ├── dto/
│   │   │               │   ├── LoginDTO.java
│   │   │               │   ├── PacienteDTO.java
│   │   │               │   ├── MedicoDTO.java
│   │   │               │   └── ConsultaDTO.java
│   │   │               └── exception/
│   │   │                   ├── GlobalExceptionHandler.java
│   │   │                   └── ResourceNotFoundException.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── style.css
│   │       │   ├── js/
│   │       │   │   ├── app.js
│   │       │   │   ├── login.js
│   │       │   │   ├── pacientes.js
│   │       │   │   ├── medicos.js
│   │       │   │   └── consultas.js
│   │       │   └── images/
│   │       └── templates/
│   │           ├── index.html
│   │           ├── login.html
│   │           ├── dashboard.html
│   │           ├── pacientes.html
│   │           ├── medicos.html
│   │           ├── consultas.html
│   │           └── relatorios.html
│   └── test/
│       └── java/
│           └── br/
│               └── uern/
│                   └── mediagenda/
│                       └── MediAgendaApplicationTests.java
├── pom.xml
└── README.md
```

---

## 🚀 Funcionalidades

### 1. Autenticação e Autorização

- Login de usuários (Administrador, Recepcionista, Médico)
- Controle de acesso baseado em perfis
- Sessões seguras com Spring Security

### 2. Gestão de Pacientes

- Cadastro de pacientes
- Edição de informações
- Consulta de histórico
- Exclusão lógica

### 3. Gestão de Médicos

- Cadastro de médicos
- Vinculação com especialidades
- Gerenciamento de horários disponíveis
- Controle de status (ativo/inativo)

### 4. Gestão de Especialidades

- Cadastro de especialidades médicas
- Associação com médicos

### 5. Agendamento de Consultas

- Agendamento com validação de disponibilidade
- Cancelamento de consultas
- Reagendamento
- Confirmação de presença
- Controle de status (Agendada, Confirmada, Realizada, Cancelada)

### 6. Relatórios

- Consultas por período
- Consultas por médico
- Consultas por especialidade
- Taxa de comparecimento
- Relatório de cancelamentos

---

## 📝 Casos de Uso

### Caso de Uso 1: Agendar Consulta

**Ator Principal:** Recepcionista  
**Objetivo:** Agendar uma consulta médica para um paciente

**Pré-condições:**

- Recepcionista está autenticado no sistema
- Paciente está cadastrado
- Médico está disponível

**Fluxo Principal:**

1. Recepcionista acessa a tela de agendamento
2. Sistema exibe formulário de agendamento
3. Recepcionista seleciona o paciente
4. Recepcionista seleciona a especialidade desejada
5. Sistema exibe lista de médicos disponíveis
6. Recepcionista seleciona o médico
7. Sistema exibe horários disponíveis do médico
8. Recepcionista seleciona data e horário
9. Recepcionista informa observações (se necessário)
10. Sistema valida disponibilidade
11. Sistema confirma o agendamento
12. Sistema exibe mensagem de sucesso

**Fluxos Alternativos:**

**FA1 - Horário Indisponível:**

- No passo 10, se o horário não estiver disponível
- Sistema exibe mensagem informando conflito
- Sistema retorna ao passo 7

**FA2 - Paciente Não Cadastrado:**

- No passo 3, se o paciente não estiver cadastrado
- Sistema oferece opção de cadastro rápido
- Após cadastro, continua no passo 4

**Pós-condições:**

- Consulta é registrada no sistema
- Horário é bloqueado na agenda do médico
- Notificação é enviada (se configurado)

---

## 🔌 API Endpoints

### Autenticação

```
POST   /api/auth/login          - Autenticar usuário
POST   /api/auth/logout         - Logout
GET    /api/auth/user           - Obter usuário autenticado
```

### Pacientes

```
GET    /api/pacientes           - Listar todos os pacientes
GET    /api/pacientes/{id}      - Buscar paciente por ID
POST   /api/pacientes           - Cadastrar novo paciente
PUT    /api/pacientes/{id}      - Atualizar paciente
DELETE /api/pacientes/{id}      - Excluir paciente
GET    /api/pacientes/cpf/{cpf} - Buscar por CPF
```

### Médicos

```
GET    /api/medicos             - Listar todos os médicos
GET    /api/medicos/{id}        - Buscar médico por ID
POST   /api/medicos             - Cadastrar novo médico
PUT    /api/medicos/{id}        - Atualizar médico
DELETE /api/medicos/{id}        - Excluir médico
GET    /api/medicos/especialidade/{id} - Listar por especialidade
```

### Especialidades

```
GET    /api/especialidades      - Listar todas as especialidades
GET    /api/especialidades/{id} - Buscar especialidade por ID
POST   /api/especialidades      - Cadastrar nova especialidade
PUT    /api/especialidades/{id} - Atualizar especialidade
DELETE /api/especialidades/{id} - Excluir especialidade
```

### Consultas

```
GET    /api/consultas           - Listar todas as consultas
GET    /api/consultas/{id}      - Buscar consulta por ID
POST   /api/consultas           - Agendar nova consulta
PUT    /api/consultas/{id}      - Atualizar consulta
DELETE /api/consultas/{id}      - Cancelar consulta
GET    /api/consultas/paciente/{id}  - Consultas do paciente
GET    /api/consultas/medico/{id}    - Consultas do médico
GET    /api/consultas/data/{data}    - Consultas por data
PUT    /api/consultas/{id}/confirmar - Confirmar consulta
PUT    /api/consultas/{id}/realizar  - Marcar como realizada
```

### Relatórios

```
GET    /api/relatorios/periodo?inicio={data}&fim={data} - Relatório por período
GET    /api/relatorios/medico/{id}   - Relatório por médico
GET    /api/relatorios/especialidade/{id} - Relatório por especialidade
GET    /api/relatorios/cancelamentos - Relatório de cancelamentos
GET    /api/relatorios/comparecimento - Taxa de comparecimento
```

---

## ▶️ Como Executar

### Opção 1: Via Eclipse IDE

1. Importe o projeto no Eclipse (File > Import > Maven > Existing Maven Projects)
2. Aguarde o Maven baixar as dependências
3. Execute a classe `MediAgendaApplication.java` (Run As > Java Application)
4. Acesse: `http://localhost:8080/mediagenda`

### Opção 2: Via Linha de Comando

```bash
# Compile o projeto
mvn clean package

# Execute o JAR gerado
java -jar target/mediagenda-0.0.1-SNAPSHOT.jar
```

### Opção 3: Via Maven

```bash
mvn spring-boot:run
```

---

## 👥 Usuários Padrão

Após a primeira execução, o sistema cria usuários padrão:

| Login  | Senha     | Perfil        |
| ------ | --------- | ------------- |
| admin  | admin123  | Administrador |
| recep  | recep123  | Recepcionista |
| medico | medico123 | Médico        |

**⚠️ IMPORTANTE:** Altere as senhas padrão em produção!

---

## 📊 Modelo de Dados

### Diagrama ER Simplificado

```
┌─────────────┐       ┌──────────────┐       ┌─────────────┐
│  PACIENTE   │       │   CONSULTA   │       │   MÉDICO    │
├─────────────┤       ├──────────────┤       ├─────────────┤
│ id (PK)     │◄──────┤ id (PK)      │──────►│ id (PK)     │
│ nome        │       │ paciente_id  │       │ nome        │
│ cpf         │       │ medico_id    │       │ crm         │
│ telefone    │       │ data_hora    │       │ telefone    │
│ email       │       │ status       │       │ email       │
│ data_nasc   │       │ observacoes  │       └──────┬──────┘
└─────────────┘       └──────────────┘              │
                                                     │
                                              ┌──────▼──────┐
                                              │ESPECIALIDADE│
                                              ├─────────────┤
                                              │ id (PK)     │
                                              │ nome        │
                                              │ descricao   │
                                              └─────────────┘
```

---

## 🧪 Testes

### Executar Testes Unitários

```bash
mvn test
```

### Executar Testes de Integração

```bash
mvn verify
```

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos como parte da disciplina de Desenvolvimento de Sistemas Corporativos da UERN.

---

## 👨‍💻 Autor

**Nome do Aluno**  
Curso: Sistemas para Internet  
Universidade do Estado do Rio Grande do Norte - UERN  
Campus Avançado de Natal

---

## 📞 Contato

Para dúvidas ou sugestões:

- Email: seuemail@email.com
- GitHub: [@seu-usuario](https://github.com/seu-usuario)

---

**Desenvolvido com ☕ e Java**
