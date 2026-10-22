## Tecnologias e dependências

### Linguagem e framework

- **Java 17:** linguagem utilizada no desenvolvimento dos microsserviços.
- **Spring Boot 3.2.2:** configuração e desenvolvimento das aplicações.
- **Apache Maven:** gerenciamento de dependências e ciclo de build.

### Mensageria e comunicação

- **RabbitMQ:** broker de mensagens utilizado na comunicação assíncrona entre os microsserviços.
- **Spring AMQP:** integração das aplicações Spring com o RabbitMQ.
- **WebSocket:** comunicação em tempo real entre a aplicação e a interface web.
- **Amazon SNS:** serviço AWS utilizado para o envio de notificações por SMS.

### Persistência de dados

- **PostgreSQL:** banco de dados relacional.
- **Spring Data JPA:** abstração para persistência e acesso aos dados.
- **Hibernate:** implementação JPA utilizada pelo ecossistema Spring Boot.

### Bibliotecas auxiliares

- **Lombok:** redução de código repetitivo, como getters, setters e construtores.
- **MapStruct 1.5.5.Final:** mapeamento entre objetos Java, como entidades e DTOs.

### Principais dependências Maven

O projeto `proposta-app` utiliza, entre outras, as seguintes dependências:

| Dependência | Finalidade |
|---|---|
| `spring-boot-starter-amqp` | Integração com RabbitMQ. |
| `spring-boot-starter-data-jpa` | Persistência com JPA. |
| `spring-boot-starter-web` | Desenvolvimento de APIs e endpoints HTTP. |
| `spring-boot-starter-websocket` | Comunicação via WebSocket. |
| `postgresql` | Driver JDBC do PostgreSQL. |
| `lombok` | Redução de código repetitivo. |
| `mapstruct` | Mapeamento de objetos. |

> A configuração exata pode variar entre os três microsserviços. A tabela acima resume as dependências apresentadas no `pom.xml` do `proposta-app`.

## Conteúdos e conceitos abordados

Durante o desenvolvimento do projeto, foram trabalhados os seguintes conceitos:

- Arquitetura de microsserviços e separação de responsabilidades.
- Comunicação assíncrona e troca de mensagens.
- Configuração de exchanges, filas, bindings e Dead Letter Queues (DLQ) no RabbitMQ.
- Publicação e consumo de mensagens com Spring AMQP.
- Integração com serviços da AWS por meio do Amazon SNS.
- Envio de notificações por SMS.
- Desenvolvimento de APIs REST com Spring Boot.
- Persistência de dados com Spring Data JPA e PostgreSQL.
- Comunicação em tempo real com WebSocket.
- Aplicação do padrão de projeto Strategy.
- Tratamento de exceções e implementação de regras de negócio.

## Estrutura do projeto

O repositório está organizado em três diretórios principais, correspondentes aos microsserviços, além de um arquivo Docker Compose na raiz para iniciar a infraestrutura e a interface web disponibilizada pelo instrutor.

```text
.
├── docker-compose.yml
├── proposta-app/
│   └── pom.xml
├── notificacao/
│   └── pom.xml
└── analisecredito/
    └── pom.xml
```

- **`proposta-app/`**: cadastro e persistência de propostas, publicação de mensagens e atualização dos status.
- **`notificacao/`**: envio de notificações por SMS utilizando Amazon SNS.
- **`analisecredito/`**: análise das propostas e aplicação das regras de negócio.
- **`docker-compose.yml`**: inicialização dos containers da interface web disponibilizada pelo instrutor, PostgreSQL e RabbitMQ.

## Execução do projeto

Para executar os microsserviços, é necessário iniciar primeiro os containers da infraestrutura e da interface web. O arquivo `docker-compose.yml`, localizado na raiz do projeto, já contém a configuração desses serviços.

### Pré-requisitos

- Java 17.
- Apache Maven.
- Docker Desktop com o Docker Compose disponível.
- Conta AWS configurada com as permissões necessárias para utilizar o Amazon SNS.

### 1. Iniciar os containers

Na raiz do projeto, onde está localizado o arquivo `docker-compose.yml`, execute:

```bash
docker compose up -d
```

Esse comando inicia os containers configurados no Compose:

- **Interface web:** imagem disponibilizada pelo instrutor do curso, utilizada para interagir com a aplicação.
- **PostgreSQL:** banco de dados utilizado para persistir as propostas.
- **RabbitMQ:** broker responsável pela comunicação assíncrona entre os microsserviços.

A interface web é fornecida pelo instrutor e não foi desenvolvida como parte deste projeto.

Para verificar se os containers estão em execução, utilize:

```bash
docker compose ps
```

Aguarde os serviços estarem disponíveis antes de iniciar os microsserviços Java.

### 2. Configurar as aplicações

Antes de executar os microsserviços, confira os arquivos de configuração e ajuste, conforme necessário:

- A conexão com o PostgreSQL, incluindo endereço, porta, banco de dados e credenciais.
- A conexão com o RabbitMQ, incluindo endereço, porta e credenciais.
- As credenciais e a região da AWS utilizadas pelo Amazon SNS.

Utilize variáveis de ambiente ou configurações locais para fornecer credenciais, evitando incluí-las diretamente no código-fonte ou no repositório.

### 3. Compilar os microsserviços

Em cada diretório de microsserviço, execute:

```bash
mvn clean package
```

Repita o comando nos diretórios `proposta-app`, `notificacao` e `analisecredito`.

### 4. Executar os microsserviços

Em um terminal separado para cada aplicação, entre no respectivo diretório e execute:

```bash
mvn spring-boot:run
```

Faça isso para os três microsserviços, respeitando as portas e as configurações definidas em cada aplicação.

### 5. Testar o fluxo da aplicação pela interface web

Com os containers e os microsserviços em execução:

1. Acesse a interface web disponibilizada pelo instrutor.
2. Cadastre uma proposta.
3. Acompanhe o processamento realizado pelo microsserviço de análise de crédito.
4. Verifique o envio das notificações por SMS, conforme a configuração do Amazon SNS.
5. Consulte o painel administrativo do RabbitMQ para visualizar as filas, exchanges, bindings e mensagens.

**Importante:** o envio de SMS depende de a conta AWS e o serviço de mensagens estarem habilitados e configurados corretamente. A execução local dos containers, por si só, não garante que as notificações sejam enviadas.

---

## Aprendizados

Este projeto proporcionou experiência prática com o desenvolvimento de aplicações distribuídas utilizando Java e Spring Boot, explorando a comunicação assíncrona por mensageria, a integração com serviços de nuvem e a separação de responsabilidades entre microsserviços.

O desenvolvimento permitiu compreender como exchanges, filas e consumidores trabalham em conjunto para coordenar um fluxo de processamento que envolve persistência, análise de crédito e notificações externas.

---

**Projeto desenvolvido para fins educacionais**, com foco no aprendizado de microsserviços, mensageria com RabbitMQ e integração com serviços AWS.