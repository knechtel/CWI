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
```diff
-- Após cadastrar todas as pautas reinicie o servidor para disparar os jobs
-- fazendo um GET http://localhost:8080/api/pauta/restart o servidor é reiniciado e os jobs são disparados.
```
● Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo
determinado na chamada de abertura ou 1 minuto por default);

● Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é
identificado por um id único e pode votar apenas uma vez por pauta);

● Contabilizar os votos e dar o resultado da votação na pauta.
Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces
pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que
não infrinja direitos de uso).
É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

```diff
++ Os Votos são contabilizados no console da aplicação.
```

# Tarefa Bônus 1 
■ Integração com sistemas externos

■ Integrar com um sistema que verifique, a partir do CPF do associado, se ele pode votar

  ->// Aqui eu utilizei serviço da Web para Validar CPF
    // https://www.treinaweb.com.br/ferramentas-para-desenvolvedores/validar/
    Para acessar a API externa é usado verbo http POST via retrofit

■ GET https://user-info.herokuapp.com/users/{cpf}

■ Caso o CPF seja inválido, a API retornará o HTTP Status 404 (Not found). Você pode
usar geradores de CPF para gerar CPFs válidos;

■ Caso o CPF seja válido, a API retornará se o usuário pode (ABLE_TO_VOTE) ou não
pode (UNABLE_TO_VOTE) executar a operação


# Tarefa Bônus 2
- Mensageria e filas
Classificação da informação: Uso Interno
○ O resultado da votação precisa ser informado para o restante da plataforma, isso deve ser
feito preferencialmente através de mensageria. Quando a sessão de votação fechar, poste
uma mensagem com o resultado da votação.

 # Tarefa Bônus 3 
 - Performance - 
 
 É disparado jobs de maneira performática usando o mínimo de recurso possível do servidor.
 
 A função da classe DynamicScheduler :
 ```
 public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
 ```
 Retorna  null quando não é necessário monitorar a sessão da Pauta,
 
 linha 65 da classe DynamicScheduler encerra o Job.
  ```
  if (pauta.isPossibleToVote()) {
      pauta.setPossibleToVote(false);
      List<Voto> listVoto = votoDAO.findClosePauta(pauta.getId());
      ...
      //more code
      ...
      return null;
  }
  ```
  É usado uma variável transiente para monitorar se a pauta está disponível para votar.
  
``` 
@Entity(name = "pauta")
public class Pauta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String texto;
    private Integer second;
    private transient boolean possibleToVote = false;
...//more code 
```
 
 
# Tarefa Bônus 4 
- Versionamento da API
○ Como você versionaria a API da sua aplicação? Que estratégia usar?

Para cada funcionalidade é criada um branch feature/0.0.1.
Uma vez que a funcionalidade é validada nos ambientes de Desenvolvimento e integração, 
é feito um merge de feature/0.0.1 sobre branch develop, removendo logo apos a branch feature/0.0.1

Uma vez que develop contenha todas as funcionalidades de um delivery, a branch release/feature_1_2_3 deve ser criada a partir de develop.
No momento em que a branch release/feature_1_2_3 estiver corretamente implantada em produção, um merge de release/feature_1_2_3 na branch master, removendo logo após o branch release/feature_1_2_3.

<img src='https://1.bp.blogspot.com/-KOhx1uXwz-8/XwJUSj1qZ4I/AAAAAAAAJHE/hxQC-VWYvXIpu2naeL24LnKyNPfNcpGLwCLcBGAsYHQ/s1600/Screen%2BShot%2B2020-07-05%2Bat%2B19.28.47.png'/>
