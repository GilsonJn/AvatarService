# Avatar Service - Care Plus Challenge (G3)

Microserviço responsável pela gestão da gamificação e "Personificação Digital" (Avatar) do usuário para o Challenge da Care Plus. Este projeto foca na prevenção e promoção de hábitos saudáveis através de um sistema de níveis e pontuação, sem utilizar dados de diagnóstico clínico.

## 📋 O Desafio & A Solução

### 1. A Dor da Care Plus (Contexto)
A Care Plus busca expandir seus serviços digitais com foco em **prevenção e bem-estar**, alinhada ao propósito de "ajudar as pessoas a viverem vidas mais longas, saudáveis e felizes".

O grande desafio proposto foi criar uma solução que engajasse os usuários em hábitos saudáveis **sem entrar em diagnósticos clínicos ou telemedicina** (restrições explícitas do projeto). A empresa precisava de uma forma inovadora de manter o usuário ativo no app, focando na *jornada* de saúde, e não na doença.

### 2. Nossa Solução: "Meu Avatar Preventivo"
Para resolver isso, criamos uma plataforma de **Gamificação baseada em Ações Preventivas**.
O usuário possui uma **Personificação Digital (Avatar)** que reflete seus esforços de autocuidado.

* **A Inovação:** O avatar não "adoece" com diagnósticos médicos. Ele fica "mais forte" e sobe de nível conforme o usuário realiza ações preventivas (ex: beber água, dormir 8h, agendar check-up).
* **O Objetivo:** Transformar a prevenção (que muitas vezes é tedirosa) em uma experiência lúdica e recompensadora.

### 3. O Papel Deste Microsserviço (Java)
O **Avatar Service** é o "motor" dessa gamificação. Ele não é apenas um CRUD de usuários; ele é o sistema inteligente que:
1.  **Gerencia a evolução:** Calcula XP e Níveis com base nas regras de negócio.
2.  **Centraliza o Engajamento:** Recebe os inputs de hábitos saudáveis (vindas do App Mobile ou outros microsserviços) e os converte em recompensas digitais.
3.  **Protege os Dados:** Garante a integridade do perfil do usuário e permite a exclusão lógica (respeitando a vontade do usuário de sair da plataforma).

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 
* **Banco de Dados:** MySQL
* **Persistência:** Spring Data JPA
* **Migração de Dados:** Flyway
* **Documentação:** SpringDoc OpenAPI (Swagger)
* **Build:** Maven

## ⚙️ Configuração e Execução

### Pré-requisitos
* Java JDK 17 instalado.
* MySQL instalado e rodando.
* Maven (opcional, pois o projeto possui o wrapper `./mvnw`).

### Passo 1: Configurar o Banco de Dados
Certifique-se de que o banco de dados `avatar_service` existe no seu MySQL ou altere o arquivo `src/main/resources/application.properties` conforme suas credenciais:

```properties
spring.datasource.url=jdbc:mysql://localhost/avatar_service
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## 📖 Documentação da API (Swagger)

Com a aplicação rodando, acesse a documentação interativa e teste os endpoints diretamente pelo navegador:

🔗 http://localhost:8080/swagger-ui.html

## 🔌 Endpoints Principais

| Método | Endpoint | Descrição | Exemplo de Body (JSON) |
| :--- | :--- | :--- | :--- |
| **GET** | `/usuarios` | Lista usuários (paginado). | `N/A` |
| **GET** | `/usuarios/{id}` | Detalhes de um avatar. | `N/A` |
| **POST** | `/usuarios` | Cria um novo usuário/avatar. | `{"nome": "João", "email": "joao@email.com"}` |
| **PUT** | `/usuarios/{id}/pontos` | Gamificação: Adiciona pontos ao avatar. | `{"pontos": 100}` |
| **PUT** | `/usuarios` | Atualiza dados cadastrais. | `{"id": 1, "nome": "João Silva", "email": "novo@email.com"}` |
| **DELETE** | `/usuarios/{id}` | Realiza a exclusão lógica (desativa). | `N/A` |
| **GET** | `/NewCare` | Health Check da aplicação. | `N/A` |

## 🎮 Regras de Negócio (Gamificação)

* Início: Todo avatar começa no Nível 1 com 0 XP.
* Evolução: Ao chamar o endpoint de /pontos, o sistema soma a experiência.
* Level Up: A cada 100 pontos, o avatar sobe automaticamente de nível.

## 👨‍💻 Autores (Grupo G3)
* Integrantes:
  * Gilson Dias Ramos Junior – RM552345
  * Gustavo Bezerra Assumção - RM553076
  * Jeferson Gabriel de Mendonça - RM553149
  * Larissa Estella Gonçalves dos Santos - RM552695
 
----

Projeto desenvolvido para o Challenge FIAP x Care Plus - 2025.
