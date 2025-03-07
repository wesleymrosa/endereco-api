
# Endereco API

## Visão Geral

 O Endereco API é um sistema desenvolvido em Java com Spring Boot que permite a consulta e persistência de endereços a partir de um CEP informado. O sistema faz integração com a API do ViaCEP para buscar endereços e armazena os dados em um banco de dados PostgreSQL.

O objetivo principal é fornecer um serviço de consulta de endereço de maneira eficiente, armazenando os dados no banco para evitar consultas repetitivas na API externa.

## Tecnologias Utilizadas

### Back-end:

 - Java 17 - Linguagem de programação principal.

 - Spring Boot 3.2.3 - Framework para desenvolvimento da aplicação.

 - Spring Boot Starter Web - Para exposição de endpoints REST.

 - Spring Boot Starter Data JPA - Para manipulação de dados via ORM Hibernate.

 - Spring Cloud OpenFeign - Para consumo da API do ViaCEP de forma declarativa.

 - Lombok - Para redução de boilerplate no código Java.

 - Spring Boot Actuator - Para monitoramento da aplicação.

 - Dotenv - Para gestão de variáveis de ambiente.

 - Springdoc OpenAPI - Para documentação da API via Swagger.


### Banco de Dados:

 - PostgreSQL - Banco de dados relacional padrão.



### Ferramentas e Gerenciamento:

 - Maven - Para gestão de dependências e build do projeto.

 - Postman - Para testes dos endpoints.

 - Swagger UI - Interface interativa para testar a API.

 - cURL - Testes via linha de comando.

## Competências e Habilidades Utilizadas

### Linguagem e Frameworks

 - Desenvolvimento em Java aplicando boas práticas de programação.

 - Uso do Spring Boot para desenvolvimento de APIs REST.

 - Implementação do Spring Data JPA para manipulação de banco de dados.

 - Consumo de APIs externas utilizando Spring Cloud OpenFeign.

### Banco de Dados e Persistência

 - Modelagem e persistência de dados utilizando PostgreSQL e SQL Server.

 - Configuração e gerenciamento de conexões com banco de dados usando JPA/Hibernate.

 - Aplicação de DDL e DML para gerenciamento do banco.

 ### Gerenciamento de Dependências e Build

 - Uso do Maven para gerenciamento de dependências e ciclo de vida do projeto.

 - Execução de comandos mvn clean install e mvn spring-boot:run para compilação e execução da aplicação.

### Boas Práticas e Arquitetura

 - Aplicação do padrão DTO (Data Transfer Object) para transferência de dados.

 - Implementação de Mapper para conversão entre entidades e DTOs.

 - Organização do código utilizando a estrutura Controller, Service, Repository, DTO e Model.

 - Uso de Lombok para reduzir código boilerplate em classes Java.

 - Monitoramento com Spring Boot Actuator.

 ### Testes e Documentação
 - Testes de endpoints utilizando Postman.

 - Documentação automática da API com Swagger/OpenAPI.

 - Testes de requisições via cURL e navegador.

### Configuração e Ambiente

 - Uso de .env para gerenciamento seguro de variáveis de ambiente.

 - Configuração do IntelliJ IDEA para desenvolvimento eficiente.

 - Integração de logs utilizando SLF4J e Logback.

## Como Configurar e Executar o Projeto

### 1. Clonar o Repositório

Abra o prompt de comando (CMD) no Windows e execute:

```Bash
cd C:\caminho\onde\deseja\clonar

git clone https://github.com/seu-usuario/seu-repositorio.git

cd endereco-api
````

### 2. Abrir no IntelliJ IDEA

a) Abra o IntelliJ IDEA.

b) Clique em File > Open e selecione a pasta endereco-api.

c) Aguarde o IntelliJ carregar o projeto e baixar as dependências do Maven.

d) Verifique se o SDK Java 17 está configurado corretamente (File > Project Structure > SDKs).

### 3. Configurar o Banco de Dados

PostgreSQL:

e) Crie um banco de dados chamado endereco_db.

f) Configure as credenciais no arquivo .env na raiz do projeto e nas variáveis de ambiente do windows:

```Bash
DB_URL=jdbc:postgresql://localhost:5432/endereco_db

DB_USERNAME=seu_usuario

DB_PASSWORD=sua_senha

````
### 4. Compilar e Construir o Projeto

No terminal ou no CMD dentro da pasta do projeto, execute:
```Bash

mvn clean install

````

Isso irá baixar todas as dependências e compilar a aplicação.

### 5. Executar a Aplicação

Após compilar o projeto, execute:
```Bash

mvn spring-boot:run
````

Se tudo estiver correto, a aplicação será iniciada na porta 8080.

## Como Testar a API

### 6. Via Postman

g) Abra o Postman.

h) Crie uma nova requisição GET com a URL:
````

http://localhost:8080/enderecos/01001000
````

Clique em Send e veja a resposta com os dados do endereço.

### 7. Via Swagger UI

i) Acesse a URL no navegador:
````

http://localhost:8080/swagger-ui.html
````

Navegue pelos endpoints e clique em Try it out para testar.

### 8. Via cURL (Prompt de Comando do Windows)

Abra o CMD e execute:
````

curl -X GET http://localhost:8080/enderecos/01001000
````

Isso irá retornar uma resposta JSON com os dados do endereço.

### 9. Via Navegador

Digite a seguinte URL no navegador:
````

http://localhost:8080/enderecos/01001000
````

A resposta será exibida em formato JSON no navegador.

## Estrutura do Projeto endereco-api
Este é um projeto baseado no Spring Boot seguindo a arquitetura tradicional de camadas. Cada diretório tem uma função específica na organização do código.

 ### 10. src/main/java/com/example/endereco
Este diretório contém o código-fonte principal da aplicação.

- controller/
Contém as classes responsáveis por expor endpoints REST.
Os Controllers recebem requisições HTTP, processam chamadas aos serviços e retornam respostas.
Exemplo de um possível arquivo aqui: EnderecoController.java.

- service/
Contém a camada de lógica de negócio.
Aqui ficam as regras e validações da aplicação antes de acessar o banco de dados.
Exemplo de um possível arquivo aqui: EnderecoService.java.

- repository/
Contém as interfaces que fazem o acesso ao banco de dados usando Spring Data JPA.
Responsável por consultas, inserções e atualizações na base de dados.
Exemplo de um possível arquivo aqui: EnderecoRepository.java.

- model/
Contém as entidades do banco de dados, ou seja, classes que representam tabelas.
Normalmente, são anotadas com @Entity para que o Hibernate/JPA gerencie os objetos no banco.
Exemplo de um possível arquivo aqui: Endereco.java.

- dto/
Contém os DTOs (Data Transfer Objects), que são objetos usados para transferir dados entre o backend e o frontend ou outras APIs.
Evitam expor diretamente as entidades do banco.
Exemplo de um possível arquivo aqui: EnderecoDTO.java.

- client/
Contém classes que fazem integração com serviços externos usando Feign Client.
No contexto do projeto, esse diretório provavelmente contém um cliente Feign para consumir a API ViaCEP, que retorna informações sobre endereços a partir de um CEP informado.
Exemplo de um possível arquivo aqui: ViaCepClient.java.

- config/
Contém classes de configuração do projeto.
Pode incluir configurações do Swagger (para documentação da API), Dotenv (para manipular variáveis de ambiente) e outras configurações importantes.
Exemplo de um possível arquivo aqui: SwaggerConfig.java.

- mapper/
Contém classes responsáveis por converter Entidades em DTOs e vice-versa.
Normalmente, utiliza bibliotecas como MapStruct para facilitar essa conversão.
Exemplo de um possível arquivo aqui: EnderecoMapper.java.

### 11. src/main/resources
Este diretório contém arquivos de configuração da aplicação.

- application.properties
Arquivo de configuração principal da aplicação, onde são definidas configurações como:

- URL do banco de dados
Credenciais de acesso
Porta onde a API será executada
Configurações do Spring Boot
  - .env
Arquivo usado para definir variáveis de ambiente.
É útil para armazenar credenciais e configurações sensíveis sem expô-las no código-fonte.

### 12. Arquivos de Configuração no Diretório Raiz
- pom.xml
Arquivo do Maven, responsável por gerenciar dependências do projeto, compilar e executar a aplicação.
Contém as bibliotecas utilizadas no projeto, como:

- Spring Boot Starter Web
- Spring Data JPA
- OpenFeign
- Lombok
- Swagger

-  README.md
Arquivo de documentação do projeto.
Contém informações sobre como instalar, configurar e executar a API.

## Conclusão
Esta estrutura segue uma abordagem modular e organizada, facilitando a manutenção e evolução do projeto. Cada diretório tem um propósito bem definido, tornando o código mais limpo e compreensível.



## Considerações Finais

Este sistema permite consultas rápidas de endereços, armazenando os dados localmente para otimizar desempenho. Ele utiliza padrões modernos de desenvolvimento como DTOs, Feign Clients, e JPA, garantindo uma arquitetura organizada e escalável.

Caso tenha dúvidas ou sugestões, contribua com o projeto ou entre em contato.

## Autor: Wesley
### Email: wesleymrosa@gmail.com
### LinkedIn: www.linkedin.com/in/wesley-martins-rosa-5118aa15a
