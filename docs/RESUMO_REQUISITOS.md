# Resumo Dos Requisitos

## Banco de Dados e ORM

- Como foi implementado: o sistema utiliza o Spring Data JPA (Hibernate) conectado a um banco de dados relacional MySQL. A entidade `Plant` foi mapeada como tabela usando `@Entity`.
- Por que foi implementado dessa forma: permite a persistencia real e definitiva dos dados de catalogo entre inicializacoes do servidor, substituindo as listas em memoria.
- Onde esta: `application.properties` (configuracoes), `Plant.java` (entidade), `PlantRepository.java` (interface JPA) e `PlantCatalog.java` (camada de servico que consome o repositorio).

## Cookie

- Como foi implementado: o sistema usa o cookie `totalVisitas` para guardar no navegador quantos logins validos ja foram feitos. A cada `POST /login` valido, o servidor incrementa esse valor e envia o cookie novamente na resposta.
- Por que foi implementado dessa forma: e uma forma simples de demonstrar persistencia no navegador sem banco de dados e sem aumentar a complexidade do projeto.
- Onde esta: `src/main/java/com/jardim/paldea/support/VisitCookie.java`, `src/main/java/com/jardim/paldea/controller/LoginController.java`, `src/main/resources/templates/login.html`, `src/main/resources/templates/catalogo.html` e `src/main/resources/templates/plantas.html`.

## GET e POST

- Como foi implementado: as telas usam `GET` para abrir paginas, filtros e buscas com parametros visiveis na URL. O sistema usa `POST` para login e alteracoes no catalogo.
- Por que foi implementado dessa forma: `GET` combina com consulta e navegacao, porque deixa os parametros na URL. `POST` combina com envio de dados e operacoes que alteram informacoes.
- Onde esta: `LoginController`, `ShowcaseController`, `PlantController`, `login.html`, `catalogo.html`, `plantas.html` e `app.js`.

## Session

- Como foi implementado: depois de um login valido, o nome do usuario e salvo na `HttpSession` com a chave `usuario`. As paginas de catalogo e gestao verificam essa sessao antes de renderizar.
- Por que foi implementado dessa forma: a sessao demonstra persistencia no servidor e permite manter o acesso ativo enquanto o usuario navega entre paginas.
- Onde esta: `src/main/java/com/jardim/paldea/support/SessionHelper.java`, `src/main/java/com/jardim/paldea/controller/LoginController.java`, `src/main/java/com/jardim/paldea/controller/ShowcaseController.java` e `src/main/java/com/jardim/paldea/controller/PlantController.java`.

## Notacoes

- `@SpringBootApplication`: marca a classe principal que inicia a aplicacao Spring Boot.
- `@Controller`: marca uma classe que recebe requisicoes e devolve paginas MVC.
- `@GetMapping`: mapeia uma rota acessada por `GET`.
- `@PostMapping`: mapeia uma rota acessada por `POST`.
- `@RequestParam`: recebe parametros vindos da URL ou do formulario.
- `@Component`: permite que o Spring crie e injete uma classe automaticamente.
- `th:href`: monta links com Thymeleaf.
- `th:src`: monta o caminho de arquivos estaticos com Thymeleaf.
- `th:action`: define a rota para onde o formulario sera enviado.
- `th:object`: associa um formulario a um objeto Java.
- `th:field`: liga um campo HTML a um atributo do objeto Java.
- `th:text`: escreve um valor vindo do servidor no HTML.
- `th:if`: exibe um bloco somente se a condicao for verdadeira.
- `th:unless`: exibe um bloco somente se a condicao for falsa.
- `th:each`: repete um bloco para cada item de uma lista.
- `th:classappend`: adiciona uma classe CSS de forma condicional.
- `th:selected`: marca uma opcao do `select` como selecionada.
- `method="get"`: envia o formulario pela URL.
- `method="post"`: envia o formulario no corpo da requisicao.

## Arquivos

- `PaldeaApplication.java`: inicia a aplicacao.
- `LoginController.java`: controla a tela de login e cria a sessao.
- `ShowcaseController.java`: controla a pagina de catalogo.
- `PlantController.java`: controla a tela de gestao e o CRUD de plantas.
- `LoginForm.java`: guarda e valida os dados do login.
- `PlantForm.java`: guarda, valida e converte os dados do formulario de plantas.
- `Plant.java`: representa uma planta do catalogo, mapeada como Entidade JPA.
- `PlantRepository.java`: interface do Spring Data JPA para operacoes de CRUD direto no banco MySQL.
- `PlantCatalog.java`: servico que se comunica com o repositorio e gerencia as logicas de negocio das plantas.
- `SessionHelper.java`: centraliza o uso da `HttpSession`.
- `VisitCookie.java`: centraliza leitura e escrita do cookie `totalVisitas`.
- `login.html`: tela de acesso.
- `catalogo.html`: vitrine com condicao e repeticao.
- `plantas.html`: tela visual do CRUD.
- `app.js`: troca o modo do formulario de gestao.
- `style.css`: estilos visuais da aplicacao.
- `application.properties`: configuracoes basicas do Spring e conexao com o banco de dados.
- `MAPEAMENTO_ENTREGAS.md`: mapeia os requisitos da entrega.
- `PREMISSA_APLICACAO.md`: descreve a proposta e a arquitetura da aplicacao.

## Funcoes

- `main`: inicia o Spring Boot.
- `showLogin`: abre a tela de login ou redireciona para o catalogo se ja houver sessao.
- `login`: valida credenciais, cria sessao, incrementa cookie e redireciona.
- `buildLoginPage`: monta a pagina de login com status e mensagem.
- `showCatalog`: valida sessao e monta a pagina de catalogo.
- `showCrudPage`: valida sessao e abre a tela de gestao.
- `search`: busca uma planta por ID e retorna `200`, `400` ou `404`.
- `create`: cadastra uma planta.
- `update`: atualiza uma planta existente.
- `delete`: remove uma planta existente.
- `buildCrudPage`: monta a tela de gestao com lista, mensagem e contador.
- `validateAccess`: valida e-mail e senha do login.
- `displayName`: gera o nome exibivel a partir do e-mail.
- `validatePlantData`: valida os dados do formulario de planta.
- `parseId`: converte um texto para ID numerico valido.
- `idAsLong`: converte o ID do formulario.
- `priceAsDouble`: converte o preco informado.
- `toPlant`: cria uma `Plant` a partir do formulario.
- `findAll`: retorna a lista de plantas.
- `findById`: busca uma planta pelo ID.
- `create`: adiciona uma planta ao catalogo em memoria.
- `update`: altera uma planta do catalogo em memoria.
- `delete`: remove uma planta do catalogo em memoria.
- `hasUser`: verifica se a sessao tem usuario.
- `SessionHelper.login`: salva o usuario na sessao.
- `VisitCookie.increment`: incrementa o cookie de visitas.
- `VisitCookie.current`: le o valor atual do cookie.
- `parseTotal`: trata o valor numerico do cookie.
- `applyMode`: no JavaScript, troca o formulario entre criar, atualizar, buscar e apagar.
- `setFieldState`: no JavaScript, mostra ou esconde campos conforme o modo escolhido.
- `applyButtonTone`: no JavaScript, ajusta o estilo do botao da acao.
