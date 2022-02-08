
# API Conta Bancária

Essa API serve para fazer gerenciamento de contas em um banco. Com ela você pode **criar uma conta, realizar depósitos e transferências**

Ela foi desenvolvida com Spring Boot e Postgres.

## Documentação da API

Você pode encontrar a documentação completa, acessando esse [link](https://conta-bancaria-api.herokuapp.com/swagger-ui/index.html)


## Utilização

Para utilizar os recursos da API, basta utilizar a url base 
 
```bash
https://conta-bancaria-api.herokuapp.com/
```
seguida da rota desejada, sempre de acordo com a documentação.

## Download e execução do código

Para utilizar o código em sua máquina local, é necessário possuir o **Postgres e o Spring Tool Suite** instalados. 
<br>
### Importando projeto
Após o download/clone do repositório, no SpringToolSuite (STS) vá em

**Import > Select > Maven > Existing Maven Project**

Na propriedade **Root Directory** clique em **Browser** e selecione a pasta onde está o projeto.

Depois de selecionado, o programa já o identifica como projeto Maven e localiza o arquivo pom.xml

<br>

### Criação de Banco dentro do Postgres

Da forma que você preferir, não se esqueça de criar o banco de dados no Postgres antes de executar o projeto.

_Se você utiliza o pgAdmin, clique com o botão direito em "Databases" > Create > Database_

<br>

### Mudança de arquivo application.properties

Após a importação e antes da execução, é necessário mudar o arquivo `application.properties` pois ele está configurado para o ambiente de produção. 

Ele está localizado dentro do projeto em:

**src > main > resources**

Apague todo o conteúdo do arquivo e o substitua pelo código abaixo. 

❗ Não se esqueça de mudar o **username**, **password**  e o **nome do banco**

```
## PostgresSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/[seuBancoDeDados]
spring.datasource.username=[seuUsername]
spring.datasource.password=[suaSenha]

## Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=update

```
<br>
Após essas modificações, é só executar o projeto :)
