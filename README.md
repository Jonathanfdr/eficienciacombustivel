# Calculadora de Eficiência de Combústivel

Aplicação desenvolvida a partir do desafio proposto no processo seletivo da empresa [Totvs S.A](https://www.totvs.com/).

O objetivo do desafio é dado um cadastro de veículos, com seu respectivo desempenho dentro da cidade e em rodovias, rankear estes veículos em termos de consumo de combústivel.

A descrição completa do desafio pode ser encontrada [aqui](https://drive.google.com/file/d/1SpoF1dtjdl2xni4XPECxVDae5J9JMjoT/view) e o resultado final pode ser observado no [endpoint](#endpoints) [deste exemplo](#rankear)

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
  - [Utilização via Postman](#utilização-via-postman)
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
- [Eclipse IDE para Java EE](https://download.eclipse.org/eclipse/downloads/) (qualquer versão lançada após 2018)
- [Lombok](https://projectlombok.org/)

Dentro da IDE eclipse, deve-se importar o projeto Maven disponibilizado neste repositório através do caminho ``File -> Import -> Existing Maven Projects``.

Em seguida, clicar com o botão direito na raiz do projeto importado e no menu de contexto acessar: ``Run As -> Java Application``.

Caso não ocorra nenhum erro, basta acessar o endereço ``http://localhost:8080/veiculo`` no navegador. Se o resultado for um JSON com alguns veículos listados, a aplicação já está rodando.

Se alguma aplicação já estiver utilizando a porta 8080, basta que o arquivo [application.properties](https://github.com/Jonathanfdr/eficienciacombustivel/blob/main/src/main/resources/application.properties) seja alterado e a linha abaixo seja adicionada, substituindo ``PORTA_DISPONIVEL`` por uma porta disponível na máquina local:

```
server.port=PORTA_DISPONIVEL
```

### Endpoints

O caminho completo das primitivas irá variar de acordo com o ambiente escolhido. Deste ponto em diante o domínio e/ou a porta da aplicação serão resumidos a ``URL_PADRAO``. Para execução utilizando o serviço no Heroku:
```
URL_PADRAO=https://eficienciacombustivel.herokuapp.com/
```
Para execução local (onde porta é a porta escolhida no [tópico anterior](#ambiente-local)):
```
URL_PADRAO=https://localhost:PORTA
```

Devido a utilização da tecnologia Swagger-Ui, todos os endpoints, payloads e possíveis respostas, são documentados no próprio serviço acessando o endereço.
```
URL_PADRAO/swagger-ui.html
```
*Imagem do swagger*

Entretanto, serão listadas aqui descrições de todos os endpoints da aplicação:

|   *MÉTODO*   | *PRIMITIVA*                          | *DESCRIÇÃO*                                                                           |
|--------------|--------------------------------------|---------------------------------------------------------------------------------------|
| GET          | URL_PADRAO/marca                     | Lista todas as marcas de veículos cadastradas                                         |
| POST         | URL_PADRAO/marca                     | Cadastra uma nova marca                                                               |
| GET          | URL_PADRAO/marca/{id}                | Detalha uma marca de id={id}                                                          |
| PUT          | URL_PADRAO/marca/{id}                | Altera uma marca de id={id}                                                           |
| DELETE       | URL_PADRAO/marca/{id}                | Deleta uma marca de id={id}                                                           |
| GET          | URL_PADRAO/modelo                    | Lista todos os modelos de veículos cadastrados                                        |
| POST         | URL_PADRAO/modelo                    | Cadastra um novo modelo                                                               |
| GET          | URL_PADRAO/modelo/{id}               | Detalha um modelo de id={id}                                                          |
| PUT          | URL_PADRAO/modelo/{id}               | Altera um modelo de id={id}                                                           |
| DELETE       | URL_PADRAO/modelo/{id}               | Deleta um modelo de id={id}                                                           |
| GET          | URL_PADRAO/veiculo                   | Lista todos os veículos cadastrados                                                   |
| POST         | URL_PADRAO/veiculo                   | Cadastra um novo veículo                                                              |
| GET          | URL_PADRAO/veiculo/{id}              | Detalha um veículo de id={id}                                                         |
| PUT          | URL_PADRAO/veiculo/{id}              | Altera um veículo de id={id}                                                          |
| DELETE       | URL_PADRAO/veiculo/{id}              | Deleta um veículo de id={id}                                                          |
| GET          | URL_PADRAO/veiculo/rankingEficiencia | Lista um ranking dos veículos que tiverem melhor desempenho na circunstância apontada |


### Interface Banco de Dados H2

Outra tecnologia utilizada para o desafio foi o banco de dados relacional H2. Para visualizar os registros e montar Querys deste banco de dados foi disponibilizado sua interface gráfica no endereço
```
URL_PADRAO/h2-console
```
Os dados para acesso ao banco de dados H2 estão apontados no arquivo [application.properties](https://github.com/Jonathanfdr/eficienciacombustivel/blob/main/src/main/resources/application.properties).
Note que em ambiente local, sempre que o serviço é reiniciado, os cadastros também são, se esta for uma configuração indesejada, basta alterar o arquivo [application.properties](https://github.com/Jonathanfdr/eficienciacombustivel/blob/main/src/main/resources/application.properties) na linha onde se lê:

```
spring.jpa.hibernate.ddl-auto=create
```
para
```
spring.jpa.hibernate.ddl-auto=update
```

### Utilização via Postman

Na sequência, será utilizado o Postman para exemplificar requisições a alguns endpoints. As instruções de instalação do Postman podem ser encontradas [aqui](https://www.postman.com/).

Dentro do Postman, para realizar alguma requisição, deve-se seguir os seguintes passos:
- Acessar o menu ``File -> New -> Request``;
- Alterar o [método](#endpoints) da requisição para o adequado;
- Informar a URL da requisição (``URL_PADRAO``+``ENDPOINT``);
- Caso seja necessário algum payload na requisição, este deve ser informado na aba ``Body``, com o tipo ``Raw`` e texto ``JSON``;
- Ao clicar em ``Send``, o resultado retornado será exibido no painel ``Response``.

### Exemplos

Abaixo serão exibidos exemplos baseados na entidade ``veiculo`` utilizando o Postman.

#### Listar

Listagem de todos os veículos cadastrados:

*imagem*

#### Detalhar

Detalhando o veículo de ``id=2``:

#### Criar

Criando um novo veículo:

#### Excluir

Excluindo o veículo de ``id=2``:

#### Rankear

Ranking por eficiência de combustível, onde foram percorridos 10Km na cidade, 30Km em rodivias e o preço do combustível é R$6,50.

Obs: O cálculo da quantidade total de combustível e o preço total se dá pelo seguinte algoritmo:

```
litrosCidade = distanciaPercorridaCidade / consumoCidade;
litrosRodovia = distanciaPercorridaRodovia / consumoRodovia;
quantidadeTotalCombustivel = litrosCidade + litrosRodovia;
precoTotalCombustivel = quantidadeTotalCombustivel * precoCombustivel;
```
