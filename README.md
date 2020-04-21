# CWI

*Documentação
```
http://localhost:8080/swagger-ui.html
 ```

Objetivo

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação.
A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação.
Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API
REST:

● Cadastrar uma nova pauta;

● Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo
determinado na chamada de abertura ou 1 minuto por default);

● Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é
identificado por um id único e pode votar apenas uma vez por pauta);

● Contabilizar os votos e dar o resultado da votação na pauta.
Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces
pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que
não infrinja direitos de uso).
É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

● Tarefa Bônus 1 - Integração com sistemas externos
○ Integrar com um sistema que verifique, a partir do CPF do associado, se ele pode votar
■ GET https://user-info.herokuapp.com/users/{cpf}
■ Caso o CPF seja inválido, a API retornará o HTTP Status 404 (Not found). Você pode
usar geradores de CPF para gerar CPFs válidos;
■ Caso o CPF seja válido, a API retornará se o usuário pode (ABLE_TO_VOTE) ou não
pode (UNABLE_TO_VOTE) executar a operação

 Tarefa Bônus 3 - Performance - 
 
 É disparado jobs de maneira performática usando o mínimo de recurso possível do servidor.
 
 A função da classe DynamicScheduler :
 ```
 public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
 ```
 retorna  null quando não é necessário monitorar a sessão da Pauta.
 
 
● Tarefa Bônus 4 - Versionamento da API
○ Como você versionaria a API da sua aplicação? Que estratégia usar?

Para cada funcionalidade é criada uma branch e quando chega em uma versão também é criada uma branch para versão e a cada versão mais recente é feito um merge para master.


