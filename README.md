# Avaliação Desenvolvedor Backend Java

## Objetivo:
Avaliar a capacidade do desenvolvedor em construir e evoluir uma API REST robusta, utilizando
Java (versão estável mais recente), Spring Boot, banco de dados PostgreSQL, integrações externas,
boas práticas de arquitetura, organização de código, segurança, Docker e documentação

## Requisitos técnicos:

- Java 25
- Spring Boot
- Docker
- PostgreSQL 16.X
- Flyway
- Spring Security
- Arquitetura em camadas
- WebClient ou RestTemplate
- Tratamento de erros padronizado
- Documentação via SpringDoc (Swagger)

## Estrutura disponibilizada

- Camada de segurança parcialmente pronta
- DTOs da API externa
- User (controller, service, repository)
- Flyway com tabelas user e show
- Classe de paginação (Util.class)

- Classe modelo para chamadas externas (AbstractRequest<T>.class)

## Documentação sobre o desenvolvimento

- Criação do sql `V3__episode.sql`: os campos foram definidos de acordo com a especificação e com base nas outras tabelas do projeto.
Os índices foram criados  visando escalabilidade do sistema, para melhorar a performance das consultas de episódios para um show específico (consulta mais comum).
