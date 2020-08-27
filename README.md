# Fullstack Challenge

### [Context](https://bitbucket.org/modclima/challenge.git/README.md)

## Back-End application

A Spring-Boot standard MVC project using Postgres, JWT Authentication and Spring Data

# Intalação e Execução

### Clonando o repositório

 ```
  git clone https://github.com/gabrieeeuu/cyan_challenge_backend
  ```
  
### Executar
  
  Para a execução desta aplicação, há duas formas: através da ide na qual o repositório será aberto, executando o arquivo GabrielApplication.java; ou através do comando `mvn         spring-boot:run` ou `mvnw spring-boot:run` para windows, no root do diretório, onde está o arquivo `mvn`
  
  Por padrão, o Tomcat inicia a aplicação na porta 8080, mas no arquivo `application.propperties` há a possibilidade de se alterar o server.port. É nesse arquivo onde também estão   as confiurações para o banco de dados local. Se o PostgreSql não for uma opção hábil, o projeto possui dependência para H2 sem muitas configurações extras.
  
  Após a execução do `run`, o Maven vai fazer um build do repositório e iniciar um servidor na porta 8080 ou outra especificada.
