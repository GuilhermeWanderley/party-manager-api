# Party Manager API

API REST para gerenciamento de clientes e reservas de espaço para festas, desenvolvida com Spring Boot.

## Sobre o projeto

O sistema permite cadastrar clientes e alocar reservas de datas para eventos, com regras de negócio para evitar conflitos de agendamento e dados duplicados. Foi desenvolvido como projeto de estudo, com foco em boas práticas de arquitetura em camadas, tratamento de concorrência e design de API REST.

## Tecnologias utilizadas

- Java 17+  
- Spring Boot  
- Spring Data JPA (Hibernate)  
- Bean Validation (Jakarta Validation)  
- Lombok  
- H2 Database  
- Maven

## Arquitetura

O projeto segue uma arquitetura em camadas, separando responsabilidades:

com.example.party\_manager

├── controller/     → recebe requisições HTTP e delega ao service

├── service/        → contém as regras de negócio

├── repository/     → acesso a dados via Spring Data JPA

├── entity/         → mapeamento das tabelas do banco

├── dto/            → objetos de entrada e saída da API (nunca expõe as entidades)

└── exception/      → exceções customizadas e tratamento global de erros

### Decisões de design

- **DTOs em toda fronteira da API**: entidades JPA nunca são expostas diretamente, evitando vazamento de dados internos e loops de serialização em relacionamentos bidirecionais.  
- **Injeção de dependência via construtor**: em vez de `@Autowired` em campo, garantindo dependências imutáveis e facilitando testes.  
- **Validação em três camadas**:  
  1. Bean Validation nos DTOs (`@Valid`, `@NotBlank`, `@Email`, etc.) — valida formato antes de qualquer lógica rodar.  
  2. Regras de negócio no Service (ex: não permitir reserva de data retroativa).  
  3. Constraints de integridade no banco de dados \+ tratamento de exceção — garante consistência mesmo sob requisições concorrentes (ex: duas reservas simultâneas para a mesma data, ou dois clientes com o mesmo e-mail).  
- **Tratamento de erro centralizado**: um `@ControllerAdvice` (`GlobalExceptionHandler`) mapeia cada exceção de negócio para o status HTTP correto, evitando `try/catch` repetido em cada controller.

## Endpoints

### Clientes — `/clients`

| Método | Rota | Descrição |
| :---- | :---- | :---- |
| `POST` | `/clients` | Cadastra um novo cliente |
| `GET` | `/clients` | Lista todos os clientes |

**Exemplo de request — `POST /clients`**

{

  "name": "Maria Silva",

  "email": "maria@email.com",

  "phoneNumber": "11987654321",

  "userRole": "USER"

}

### Reservas — `/reservation`

| Método | Rota | Descrição |
| :---- | :---- | :---- |
| `POST` | `/reservation` | Cria uma nova reserva |
| `GET` | `/reservation` | Lista todas as reservas |

**Exemplo de request — `POST /reservation`**

{

  "clientId": 1,

  "desiredDate": "2026-12-20"

}

## Tratamento de erros

Respostas de erro seguem o formato:

{

  "error": "Date already reserved: 2026-12-20"

}

| Status | Quando ocorre |
| :---- | :---- |
| `400 Bad Request` | Dados inválidos (formato, campo obrigatório ausente, data retroativa) |
| `404 Not Found` | Cliente não encontrado |
| `409 Conflict` | Data já reservada ou e-mail/telefone já cadastrado |

## Como rodar o projeto

\# clone o repositório

git clone https://github.com/GuilhermeWanderley/party-manager-api.git

cd party-manager

\# rode com Maven

./mvnw spring-boot:run

Não é necessária nenhuma configuração adicional — o projeto usa H2 em memória, então o banco é criado automaticamente ao iniciar a aplicação.

A API estará disponível em `http://localhost:8080`.

## Próximos passos

- [ ] Adicionar testes unitários e de integração  
- [ ] Adicionar paginação na listagem de reservas e clientes  
- [ ] Implementar endpoints de busca por id (`GET /clients/{id}`, `GET /reservation/{id}`)  
- [ ] Documentação interativa via Swagger/OpenAPI

## Autor

Desenvolvido por Guilherme Wanderley como projeto de estudo em Spring Boot.  
