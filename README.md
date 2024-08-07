# every-cent-api
Projeto para o back-end de aplicação voltada para controle de gastos.

### Geral

O projeto usa:
<ul>
    <li>Spring-boot 3.0.3, com java 17</li>
    <li>Flyway para migrations</li>
    <li>Foi desenvolvido com Postgresql</li>
    <li>Swagger para documentação dos Endpoint</li>
    <li>Oauth com Google para login</li>
</ul>

### Subindo aplicação:

Primeiramente é necessario configurar adequadamente o arquivo `application.properties` seguindo os moldes `application.properties-sample`. Para que aplicação faça uso do login com Google é necessario configurar as chaves da api no [console google](https://console.cloud.google.com/)


Usar `infra/docker-compose.yml` para subir banco pgsql.

Para rodar a aplicação local basta rodar o seguinte comando:

```
mvn spring-boot:run
```

### O que estara disponível na API:

![swagger](https://github.com/asreal089/every-cent-api/blob/master/src/main/resources/assets/126BB659-3865-45AA-B367-AE23E6A0959D_1_105_c.jpeg?raw=true)

### STACK:

<img align="center" src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/>
<img align="center" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
<img align="center" src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white"/>

O Frontend da aplicação está disponivel no link: [every-cent-front](https://github.com/asreal089/every-cent-front)