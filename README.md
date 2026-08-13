# Party Manager API 

O **Party Manager** é uma API REST desenvolvida com **Java** e **Spring Boot** para o gerenciamento de eventos, focada na organização e integridade do cadastro de convidados. O projeto aplica padrões de engenharia de software para garantir que a persistência dos dados seja segura e livre de duplicidade.

>  **Status:** Em desenvolvimento ativo. Atualmente focado na robustez da camada de persistência e validações de negócio.

## 🛠️ Tecnologias e Arquitetura
*   **Java 17+ & Spring Boot 3**: Base tecnológica para uma aplicação moderna.
*   **Spring Data JPA & Hibernate**: Camada de persistência com mapeamento objeto-relacional.
*   **Jakarta Bean Validation**: Uso de anotações como `@NotBlank`, `@Email`, `@Size` e `@Pattern` para garantir a qualidade dos dados de entrada.
*   **Lombok**: Utilizado para manter o código limpo e focado na lógica de negócio.
*   **H2 Database**: Banco de dados relacional para agilidade no ciclo de desenvolvimento.

## 🛡️ Camadas de Blindagem e Diferenciais
O projeto se destaca pela aplicação de boas práticas de desenvolvimento backend:
*   **Integridade no Banco de Dados**: Uso de restrições `unique = true` e `nullable = false` diretamente nas colunas de E-mail e Telefone para evitar inconsistências.
*   **Validação de Formato**: Implementação de Regex para garantir que telefones sigam o padrão brasileiro de 11 dígitos.
*   **Tipagem Forte com Enums**: Uso de `EnumType.STRING` para persistir cargos de usuário, garantindo legibilidade e manutenção do banco.
*   **Histórico Organizado**: Uso de Commits Semânticos e fluxo de trabalho baseado em Branches para um desenvolvimento profissional.
