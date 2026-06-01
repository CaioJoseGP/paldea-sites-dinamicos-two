# Mapeamento Das Entregas

## Entrega 1 - pagina com condicao

- Status: atendido
- Onde aparece: `src/main/resources/templates/catalogo.html`
- Como foi implementado: o catalogo alterna o destaque promocional com `th:if` e `th:unless`, mudando o bloco visivel conforme o status da campanha.

## Entrega 1 - pagina com repeticao

- Status: atendido
- Onde aparece: `src/main/resources/templates/catalogo.html`
- Como foi implementado: a lista usa `th:each` para repetir a estrutura dos cards e simular dados que futuramente viriam de banco.

## Entrega 1 - CRUD visual

- Status: atendido
- Onde aparece: `src/main/resources/templates/plantas.html`
- Como foi implementado: a tela de gestao concentra inserir, atualizar, buscar e apagar em um formulario unico com envio HTML para os endpoints server side.

## Entrega 2 - servidor web real com Spring MVC

- Status: atendido
- Onde aparece: controladores em `src/main/java/com/jardim/paldea/controller`, formularios em `src/main/java/com/jardim/paldea/model` e helpers em `src/main/java/com/jardim/paldea/support`
- Como foi implementado: o projeto roda com Spring Boot e processa as paginas no servidor usando controllers MVC simples, formularios Java e Thymeleaf.

## Entrega 2 - codigos HTTP pela regra de negocio

- Status: atendido
- Onde aparece: `src/main/java/com/jardim/paldea/controller/LoginController.java` e `src/main/java/com/jardim/paldea/controller/PlantController.java`
- Como foi implementado: operacoes validas retornam `200`, erros de entrada retornam `400` e registros inexistentes retornam `404`.


## Entrega 3 - requisicoes GET e POST por formularios

- Status: atendido
- Onde aparece: `src/main/resources/templates/login.html`, `src/main/resources/templates/catalogo.html`, `src/main/resources/templates/plantas.html`, `src/main/resources/static/app.js`, `src/main/java/com/jardim/paldea/controller/LoginController.java`, `src/main/java/com/jardim/paldea/controller/ShowcaseController.java` e `src/main/java/com/jardim/paldea/controller/PlantController.java`
- Como foi implementado: acesso de paginas, buscas e filtros usam `GET`, deixando parametros visiveis na URL (`/login`, `/catalogo?promocaoStatus=...`, `/plantas` e `/plantas/buscar?id=...`). Login e operacoes de cadastro usam `POST`, enviando dados pelo corpo da requisicao (`/login`, `/plantas/inserir`, `/plantas/atualizar` e `/plantas/apagar`).

## Entrega 3 - persistencia com SESSION

- Status: atendido
- Onde aparece: `src/main/java/com/jardim/paldea/support/SessionHelper.java`, `src/main/java/com/jardim/paldea/controller/LoginController.java`, `src/main/java/com/jardim/paldea/controller/ShowcaseController.java` e `src/main/java/com/jardim/paldea/controller/PlantController.java`
- Como foi implementado: apos login valido, o nome do usuario fica salvo na `HttpSession` com a chave `usuario`. As paginas de catalogo e gestao verificam essa sessao antes de carregar; sem sessao ativa, o usuario volta para `/login`.

## Entrega 3 - persistencia com COOKIE

- Status: atendido
- Onde aparece: `src/main/java/com/jardim/paldea/support/VisitCookie.java`, `src/main/resources/templates/login.html`, `src/main/resources/templates/catalogo.html` e `src/main/resources/templates/plantas.html`
- Como foi implementado: a cada login valido, o servidor incrementa o cookie `totalVisitas` e grava o novo valor no navegador com validade de 30 dias. Esse valor e exibido na navegacao das paginas.
- Como validar: fazer login, abrir DevTools em Application > Cookies e conferir o cookie `totalVisitas`. Ao fechar e reabrir o navegador, o contador permanece salvo.

## Entrega 4 - persistencia no banco com ORM

- Status: atendido
- Onde aparece: `application.properties`, `Plant.java`, `PlantRepository.java` e `PlantCatalog.java`.
- Como foi implementado: a lista em memoria foi substituida pelo Spring Data JPA e Hibernate. A classe `Plant` virou uma `@Entity` e as operacoes de CRUD agora sao feitas por uma interface `PlantRepository` conectada a um banco MySQL local.
- Como validar: iniciar o MySQL pelo XAMPP, iniciar o projeto, adicionar dados, desligar e religar a aplicacao para confirmar que os dados nao foram perdidos.
