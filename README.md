
# 💸 PicPay Simplificado - Backend Challenge

Projeto desenvolvido como solução para o desafio **PicPay Simplificado**, implementando funcionalidades de transferência entre usuários, seguindo regras de negócio estritas como:
* Autorização externa de transações.
* Notificações simuladas pós-transação.
* Restrição de envio de transferências para lojistas.
* Utilização de cache com **Redis** para otimização de consultas de carteiras.


## Tecnologias e Ferramentas

Este projeto de backend utiliza o ecossistema Java/Spring Boot e segue uma arquitetura baseada em microserviços.

| Categoria | Tecnologia | Versão/Detalhe |
| :--- | :--- | :--- |
| **Linguagem** | Java | 17 |
| **Framework** | Spring Boot | 3+ |
| **Persistência** | Spring Data JPA | - |
| **Banco de Dados** | PostgreSQL | - |
| **Cache** | Spring Cache (Redis) | - |
| **Migrações** | Flyway | - |
| **Outros** | Lombok, Docker | - |

## Configuração do Ambiente
### Banco de Dados (PostgreSQL)

O projeto espera um container de banco de dados **PostgreSQL** rodando localmente na porta `5432`. A aplicação está configurada para conectar usando as seguintes credenciais padrão:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/picpay_challenge
    username: postgres
    password: postgres
```


### 🧠 Cache (Redis)

O Redis é usado como camada de cache para reduzir a latência e a carga no banco de dados, especialmente na consulta de saldos e carteiras.

Estratégia: O annotation @Cacheable(value = "wallets", key = "#idUser") é usado nas buscas para armazenar a carteira.

Evicção de Cache: O @CacheEvict(value = "wallets", key = "#wallet.user.id") é acionado após qualquer atualização de saldo (como após uma transferência) para garantir que a próxima consulta traga o valor atualizado.

TTL (Time-To-Live): O tempo de vida do cache é configurado para 60 segundos (spring.cache.redis.time-to-live).



## 🚀 Rodando o Projeto

Para colocar o projeto no ar, siga os passos abaixo. Certifique-se de que o Docker e o Maven (./mvnw) estejam instalados.

1. Subir Serviços de Infraestrutura
Utilize o docker run para iniciar o PostgreSQL e o Redis em modo detached (-d). Seus containers já devem estar rodando, mas se precisar reiniciá-los ou criá-los:

a) Iniciar PostgreSQL:

```
docker run -d \
  --name picpay-postgres \
  -e POSTGRES_DB=picpay_challenge \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:latest

```

b) Iniciar Redis:

```
docker run -d \
  --name picpay-redis \
  -p 6379:6379 \
  redis:latest

```

2. Rodar a Aplicação Spring Boot

Execute o comando Maven para iniciar o backend:

```
./mvnw spring-boot:run

```

A API estará disponível em: http://localhost:8080
## Endpoints Principais 
```
Método	Endpoint	Descrição
POST	/users	Cria um novo usuário (CUSTOMER ou ADMIN).
POST	/wallets	Cria a carteira inicial para um usuário recém-criado.
POST	/transfer	Realiza uma transferência de valor entre dois usuários.
GET	/wallets/{idUser}	Consulta a carteira e o saldo de um usuário específico.

```
## Regras de Negócio e Serviços Externos

O core do projeto está na implementação das seguintes regras de negócio:

Restrição de Lojista: Usuários com o tipo ADMIN (Lojistas) não podem enviar transferências.

Autorização Externa: Antes de efetuar qualquer transação, é feita uma chamada de validação com o serviço externo para garantir a segurança: 🔗 https://util.devi.tools/api/v2/authorize

Notificação Externa: Em caso de transação bem-sucedida, uma notificação é simulada ao usuário recebedor através do serviço: 🔗 https://util.devi.tools/api/v1/notify

Reversão de Transação: Em caso de falha nos serviços externos (autorização/notificação), a transação é revertida (rollback) para manter a consistência dos dados.
## Observabilidade e Tratamento de Erros

* Todas as exceções da aplicação são centralizadas e tratadas por um ControllerAdvice.

* As respostas de erro são padronizadas, retornando um objeto JSON claro com o código de status HTTP apropriado.
## Estrutura de Pastas 

```
com.picpay.picpay_challenge
 ├── client              # Clientes HTTP para serviços externos (Authorizer, Notifier)
 ├── config              # Classes de configuração (Ex: RedisConfig)
 ├── controller          # Camada de entrada e tratamento de requisições
 │    ├── request        # DTOs de entrada
 │    └── response       # DTOs de saída
 ├── entity              # Entidades JPA (User, Wallet, Transaction)
 ├── exceptions          # Classes de exceções customizadas
 ├── mapper              # Mapeamento entre Entidades e DTOs (opcional)
 ├── repository          # Repositórios de dados (Spring Data JPA)
 └── service             # Regras de negócio, lógica e orquestração

 ```
