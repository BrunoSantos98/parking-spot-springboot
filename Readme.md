# workshop-springboot3-jpa

> ℹ️ Detalhes: Aplicação Back-end feita em java + Spring boot + sql, onde se simula uma API de CRUD de um condominio com vagas de estacionamento de veículo demarcadas por apartamento. O objetivo desta API é de aprender Spring, notações JPA e organização de projeto.

- 🚧 melhorias a serem feitas 🚧 :

  - [x]  Criar uma entidade carro e separar da entidade parkingSpot.
  - [x]  Fazer a relação 1:1 entre as entidades carro e parkingSpot.
  - [x]  inserir parâmetros e filtros na busca por metodo GET(all).
  - [x]  Criar excessões e tratar possíveis erros da aplicação.
  - [x]  Criar metodos especificos de validação retirando os if-elses dos metodos do controller.
  - [x]  colocar metodo patch no ParkingSpot para poder atualizar o carro(quem tem) para a vaga do morador.
  - [x]  Adicionar interfaces entre services e controller.
  - [x]  Adicionar entidade dependente e relacionamento com parkingSpot.
  - [x]  Adicionar metodos de autorização e Autenticação.
  - [ ]  Adicionar metodos de adição, alteração e remoção de roles do usuario.
  - [ ]  Corrigir os tratamentos de Erro que não estão tratando erros corretamente

## 💻 Pré Requisitos 💻:

- Sistema operacional: Qualquer Distribuição Linux, Windows e Mac OS

- Instalação: é necessario ter o java 18(jre e sdk) instalado em sua maquina para testar e qualquer IDE java ou um editor de sua preferencia que consiga
  compilar java. O banco de dados utilizado é o H2, então caso queira popular um banco e fazer com seus casos de teste você deve atualizar as informações de propriedades do banco de dados. Você tambem deve ter o Maven instalado na sua maquina.

## 📃 Como usar:

1. Faça o clone do projeto

2. abra na sua IDE ou editor de preferencia

3. abra o arquivo "pom.xml" e verifique se falta instalar alguma depencia, caso seja necessario faça o Download das dependencias faltantes para o bom funcionamento do projeto

4. Teste como quiser.
