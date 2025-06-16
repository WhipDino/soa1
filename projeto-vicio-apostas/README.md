# Sistema de Apoio ao Vício em Apostas

## Descrição

Este é um projeto acadêmico desenvolvido para ajudar pessoas com vício compulsivo em apostas. O sistema oferece funcionalidades de cadastro, autenticação e acompanhamento de sessões de apoio.

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.2.0**
- **Spring Security** (Autenticação e Autorização)
- **Spring Data JPA** (Persistência de dados)
- **Spring Web Services** (SOAP)
- **H2 Database** (Banco de dados em memória)
- **JWT** (JSON Web Tokens)
- **Swagger/OpenAPI** (Documentação da API)
- **Apache CXF** (Web Services SOAP)
- **Maven** (Gerenciamento de dependências)

## Arquitetura

O projeto segue uma arquitetura orientada a serviços (SOA) com as seguintes camadas:

- **Controller**: Endpoints REST e SOAP
- **Service**: Lógica de negócio
- **Repository**: Acesso aos dados
- **Model**: Entidades do domínio
- **Security**: Configurações de segurança e JWT

## Funcionalidades

### APIs REST

#### Autenticação
- `POST /api/auth/login` - Realizar login
- `POST /api/auth/register` - Registrar novo usuário

#### Usuários
- `GET /api/usuarios` - Listar todos os usuários
- `GET /api/usuarios/{id}` - Buscar usuário por ID
- `POST /api/usuarios` - Criar novo usuário
- `PUT /api/usuarios/{id}` - Atualizar usuário
- `DELETE /api/usuarios/{id}` - Deletar usuário

#### Sessões de Apoio
- `GET /api/sessoes` - Listar todas as sessões
- `GET /api/sessoes/{id}` - Buscar sessão por ID
- `GET /api/sessoes/usuario/{usuarioId}` - Buscar sessões por usuário
- `POST /api/sessoes` - Criar nova sessão
- `PUT /api/sessoes/{id}` - Atualizar sessão
- `DELETE /api/sessoes/{id}` - Deletar sessão

### Web Services SOAP

- `obterInformacoesApoio(tipoApoio)` - Obter informações sobre tipos de apoio
- `verificarDisponibilidade(data)` - Verificar disponibilidade de horários

## Segurança

O sistema implementa:

- **Autenticação JWT**: Tokens para acesso seguro às APIs
- **Spring Security**: Configuração de segurança robusta
- **Criptografia de senhas**: BCrypt para hash das senhas
- **CORS**: Configurado para permitir acesso de diferentes origens

## Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6 ou superior

### Passos

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Execute o comando:
   ```bash
   mvn spring-boot:run
   ```

4. A aplicação estará disponível em: `http://localhost:8080`

## Documentação da API

Após iniciar a aplicação, acesse:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/api-docs`

## Console do Banco H2

Para acessar o console do banco H2:

- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

## Web Services SOAP

O WSDL do serviço SOAP está disponível em:
- `http://localhost:8080/services/apoio?wsdl`

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/faculdade/vicioapostas/
│   │       ├── VicioApostasApplication.java
│   │       ├── config/
│   │       │   ├── SecurityConfig.java
│   │       │   ├── SoapConfig.java
│   │       │   └── SwaggerConfig.java
│   │       ├── controller/
│   │       │   ├── AuthController.java
│   │       │   ├── UsuarioController.java
│   │       │   ├── SessaoApoioController.java
│   │       │   └── ApoioSoapService.java
│   │       ├── model/
│   │       │   ├── Usuario.java
│   │       │   ├── SessaoApoio.java
│   │       │   ├── StatusUsuario.java
│   │       │   └── TipoSessao.java
│   │       ├── repository/
│   │       │   ├── UsuarioRepository.java
│   │       │   └── SessaoApoioRepository.java
│   │       ├── security/
│   │       │   ├── JwtUtil.java
│   │       │   ├── JwtAuthenticationFilter.java
│   │       │   └── CustomUserDetailsService.java
│   │       └── service/
│   │           ├── UsuarioService.java
│   │           └── SessaoApoioService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
```

## Exemplos de Uso

### Registro de Usuário

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "123456"
  }'
```

### Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@email.com",
    "senha": "123456"
  }'
```

### Criar Sessão de Apoio

```bash
curl -X POST http://localhost:8080/api/sessoes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {seu-token-jwt}" \
  -d '{
    "usuario": {"id": 1},
    "descricao": "Primeira consulta",
    "tipo": "CONSULTA_INICIAL",
    "observacoes": "Paciente demonstra interesse em tratamento"
  }'
```

## Considerações de Segurança

- Todas as senhas são criptografadas usando BCrypt
- Tokens JWT têm tempo de expiração configurável
- Endpoints protegidos requerem autenticação
- CORS configurado para desenvolvimento (ajustar para produção)

## Licença

Este projeto é desenvolvido para fins acadêmicos.

