# Calculadora de Eficiência de Combústivel

Aplicação desenvolvida a partir do desafio proposto no processo seletivo da empresa Totvs S.A.

O objetivo do desafio é, dado um cadastro de veículos com seu respectivo desempenho dentro da cidade e em rodovias, rankear estes veículos em termos de consumo de combústivel.

A descrição completa do desafio pode ser encontrada [aqui](https://drive.google.com/file/d/1SpoF1dtjdl2xni4XPECxVDae5J9JMjoT/view), e o resultado final pode ser observado no [endpoint](#endpoints) [deste exemplo](#rankear)

## Menu
- [Propósito](#calculadora-de-eficiência-de-combústivel)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Modelagem](#modelagem)
- [Como Utilizar](#como-utilizar)
  - [Executando a Aplicação](#executando-a-aplicação)
    - [Utilizando Serviço no Heroku](#utilizando-serviço-no-heroku)
    - [Ambiente Local](#ambiente-local)
  - [Endpoints](#endpoints)
  - [Interface Banco de Dados H2](#interface-banco-de-dados-h2)
  - [Utilização via Postamn](#utilização-via-postman)
  - [Exemplos](#exemplos)
    - [Listar](#listar)
    - [Detalhar](#detalhar)
    - [Criar](#criar)
    - [Alterar](#alterar)
    - [Excluir](#excluir)
    - [Rankear](#rankear)

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white)
![JUnit](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Heroku](https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white)
![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

## Modelagem

Para o desenvolvimento da solução, foi elaborado um esquema relacional das entidades a serem utilizadas, resultando no modelo abaixo:

*colocar modelo*

## Como Utilizar

### Executando a Aplicação

A aplicação pode ser executada por diversos métodos, entretanto, serão listadas aqui apenas 2. 

#### Utilizando Serviço no Heroku

A API já se encontra disponível em um container na plataforma [Heroku](http://heroku.com/). Portanto, se for optado por utilizar a aplicação sem a necessidade desta ser local, basta utilizar o endereço "ht<span>tps://</span>eficienciacombustivel.herokuapp.com" nos próximos passos.

#### Ambiente Local

Para a utilização em ambiente local, alguns pré-requisitos são necessários.
- [JDK 8 ou superior](https://developers.redhat.com/products/openjdk/download)
- [Eclipse IDE para Java EE (qualquer versão lançada após 2018)](https://download.eclipse.org/eclipse/downloads/)
- [Lombok](https://projectlombok.org/)

Dentro da IDE eclipse, deve-se importar o projeto maven disponibilizado neste repositório através do caminho File -> Import -> Existing Maven Projects.

Em seguida, clicar com o botão direito na raiz do projeto importado e no menu de contexto acessar Run As -> Java Application.

Caso não ocorra nenhum erro, basta acessar o endereço http://localhost:8080 no 

### Endpoints

Documentar endpoints

### Interface Banco de Dados H2

Detalhar a possíbilidade de acessar o H2

### Utilização via Postamn

Detalhar como utilizar via postman

### Exemplos

#### Listar

#### Detalhar

#### Criar

#### Excluir

#### Rankear
