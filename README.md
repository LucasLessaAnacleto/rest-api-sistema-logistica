# Sistema de Logística - API Spring REST

Este projeto é uma API Spring REST que simula um sistema de logística simples, que faz cadastro, alteração, exclusão e pesquisa de clientes, entregas, destinatários e ocorrências relacionadas às entregas.

## ⚙️ Ambiente de Desenvolvimento

Toda a inicialização e o desenvolvimento do projeto eu fiz com o [Visual Studio Code](https://code.visualstudio.com/). Eu utilizei a extensão<br> 
[Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack) que oferece as mesmas opções de configuração para inicializar o projeto que o<br>
Spring Initializr, além de configurar dependencias adicionais durante o desenvolvimento, quase que fazendo toda a parte chata de configuração sozinho.

## 📦 Implantação

Para implantar e executar este projeto, siga as etapas abaixo:

1. #### Pré-Requisitos:
  + ter instalado no mínimo o jdk-11
  + ter um banco como MySQL ou PostgreSQL
  + O Maven instalado

2. Clone este repositório para o seu ambiente local:
```
git clone https://github.com/LucasLessaAnacleto/rest-api-sistema-logistica.git
```
3. Navegue até o diretório clonado e crie o arquivo *application.properties*<br>
na pasta "src\main\resources\" do projeto, adicionando as linhas abaixo:
```
//No lugar de mysql bote o nome do seu servidor de banco de dados
//Ponhe de 3306 coloque a porta que esta rodando o banco
//No lugar de algalog se quiser mudar o nome do seu banco...
spring.datasource.url=jdbc:mysql://localhost:3306/algalog?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=usuario
spring.datasource.password=senha
spring.jpa.show-sql=true              //para mostrar no console os comandos sql gerados pelo jpa
```
4. Mude as migrations para o nome do seu banco de dados ou use o mesmo **algalog**

5. Instale as dependecias do pom.xml:
```
mvn clean install
```

## 🛠️ Construído com

* [Spring Boot](https://spring.io/projects/spring-boot) - simplifica a inicialização de projetos, gerenciamento de dependências e configuração do ambiente de execução.
* [Maven](https://maven.apache.org/) - Ele simplifica o processo de compilação, teste e empacotamento do código-fonte, facilitando o gerenciamento de dependências e a criação de projetos Java de forma eficiente.
* [Spring JPA](https://spring.io/projects/spring-data-jpa) - Ele oferece um modelo de programação simplificado para realizar operações de CRUD e consultas em bancos de dados, eliminando a necessidade de escrever consultas SQL.
* [Spring WEB](https://spring.io/projects/spring-framework) - Ele inclui suporte para criação de controladores, gerenciamento de rotas, manipulação de solicitações e respostas HTTP, conversão de dados, validação de entrada, entre outros.
* [Lombok](https://projectlombok.org/) - É uma biblioteca que oferece recursos para simplificar o desenvolvimento de software, reduzindo a quantidade de código boilerplate (código repetitivo).
* [ModelMapper](https://modelmapper.org/getting-started/) - Biblioteca para mapeamento entre objetos em diferentes camadas de um aplicativo, como objetos de transferência de dados (DTOs) para entidades de banco de dados, ou vice-versa.
* [MySQL](https://www.mysql.com/) - Foi o banco de dados utilizado para gerencias os produtos do projeto, juntamente com o *Workbench* que foi a ferramenta de modelagem e administração do banco de dados.
* [PostMan](https://www.postman.com/downloads/) - Foi utilizado para fazer as requisições HTTP e testar minha API.
* [FlyWay](https://documentation.red-gate.com/fd?_ga=2.51697385.1956902338.1686408276-767283719.1684673522) - É uma ferramenta de migração de banco de dados que permite gerenciar e versionar as alterações de esquema do banco de dados de forma fácil e automatizada.
## 📌 Versão

* Utilizei o Spring Boot 2.7.12
* O JDK 17

## ✒️ Créditos

* Esse projeto é o resultado da semana de intensivão do Mergulho Spring Rest da AlgaWorks
* Todo esse projeto foi construído acompanhando as aulas dessa semana gratuita
