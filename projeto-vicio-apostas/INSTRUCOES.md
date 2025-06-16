# Instruções de Execução

## Pré-requisitos
- Java 17 ou superior
- Maven 3.6 ou superior

## Como executar

1. **Compilar o projeto:**
   ```bash
   mvn clean compile
   ```

2. **Executar os testes:**
   ```bash
   mvn test
   ```

3. **Executar a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

4. **Gerar o JAR executável:**
   ```bash
   mvn clean package
   java -jar target/vicio-apostas-0.0.1-SNAPSHOT.jar
   ```

## Endpoints disponíveis após execução

- **Aplicação:** http://localhost:8080
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **Console H2:** http://localhost:8080/h2-console
- **SOAP WSDL:** http://localhost:8080/services/apoio?wsdl

## Testando a API

### 1. Registrar um usuário:
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "123456"
  }'
```

### 2. Fazer login:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@email.com",
    "senha": "123456"
  }'
```

### 3. Criar uma sessão de apoio (use o token do login):
```bash
curl -X POST http://localhost:8080/api/sessoes \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_AQUI" \
  -d '{
    "usuario": {"id": 1},
    "descricao": "Primeira consulta",
    "tipo": "CONSULTA_INICIAL",
    "observacoes": "Paciente demonstra interesse em tratamento"
  }'
```

## Estrutura do Banco de Dados

O projeto usa H2 em memória. As tabelas são criadas automaticamente:

- **usuarios**: Armazena dados dos usuários
- **sessoes_apoio**: Registra sessões de apoio/consultas

## Observações

- O banco H2 é reinicializado a cada execução
- Todos os endpoints (exceto auth) requerem autenticação JWT
- O token JWT expira em 24 horas (configurável)
- CORS está habilitado para desenvolvimento

