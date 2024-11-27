# Backlog do Projeto Gestão de Estacionamentos - Java Parkings
Atualizado em 27/11/2024.


### Sprint 1 - Entrega 01

| Tarefa                                                      | Responsável |
| ----------------------------------------------------------- | ----------- |
| 1. Elaborar um diagrama de classes que atenda aos requisitos iniciais do sistema. | João, Carlos, Kaio, Heleno, Victor, Davi |
| 2. Preencher os dados corretamente no template do repositório GitHub do grupo.    |        João     |

----

### Sprint 1 - Entrega 02

| Tarefa                                                      | Responsável |
| ----------------------------------------------------------- | ----------- |
| 1. Atualizar o diagrama de classes considerando as ponderações do professor.     |        João     |
| 2. Desenvolver o código inicial do projeto com base no diagrama atualizado.      |          Victor   |

----

### Sprint 2 - Novos Requisitos

| Tarefa                                                      | Responsável |
| ----------------------------------------------------------- | ----------- |
| 1. Incluir diferentes tipos de vagas no sistema:            |       Victor      |
|    - Regulares (R):                                |      Victor       |
|    - Idosos (I): desconto de 15%.                               |    Victor         |
|    - PCD (E): vagas com desconto de 13%. |        Victor     |
|    - VIP (V): Com valor 20% mais caro que as vagas regulares. |   Victor          |

#### Entregas da Sprint 2

| Tarefa                                                      | Responsável |
| ----------------------------------------------------------- | ----------- |
| 1. Atualizar o diagrama de classes para incluir os novos requisitos.              |         João    |
| 2. Implementar funcionalidades de classes e métodos:                             |     João/Victor        |
|    - Diferenciação entre os tipos de vagas.                                       |     Victor        |
|    - Aplicação das regras específicas para cada tipo.                             |       João/Victor      |
| 3. Implementar pelo menos dois testes unitários por classe.                       |      João       |
| 4. Definir o formato de arquivos para persistência de dados (leitura e escrita).  |         Victor    |
| 5. Implementar métodos de leitura e escrita dos dados em arquivos texto.          |     João/Victor        |
| 6. Preparar e realizar a apresentação do sistema.                                 |       Joao      |

----

### Sprint 3 - Novos Requisitos

### Sprint 3

| Tarefa                                                      | Responsável |
| ----------------------------------------------------------- | ----------- |
| 1. Atualização do diagrama de classes com novos requisitos  | João        |
| 2. Configuração do repositório no GitHub                    | João        |
| 3. Reorganização do código para o padrão MVC                |         |
| 4. Desenvolvimento da interface gráfica (Java Swing)        | João, Victor, Carlos |
| 5. Implementação do histórico de uso do estacionamento para clientes identificados | Carlos |
| 6. Implementação de filtros de histórico por datas          |             |
| 7. Criação dos relatórios financeiros                       | Davi        |
| 8. Implementação de visualização do valor total arrecadado  |             |
| 9. Implementação de relatórios de arrecadação mensal        |             |
| 10. Implementação do ranking de clientes por arrecadação    |             |
| 11. Testes adicionais para funcionalidades de interface gráfica e relatórios |             |


----

### Sprint 4 - Release Final

> Nesta sprint, todo o sistema foi refeito para garantir maior robustez, escalabilidade e alinhamento com os requisitos acumulados de sprints anteriores.

| Tarefa                                                      | Responsável |
| ----------------------------------------------------------- | ----------- |
| 1. Elaborar um diagrama de classes que atenda aos requisitos iniciais do sistema. |        Joao/Victor     |
|    - Diferentes tipos de vagas: Regular (R), Idosos (I), PCD (E), VIP (V).        |        Victor     |
|    - Gestão de histórico de uso e relatórios gerenciais.                          |        -     |
| 2. Desenvolver o código inicial com base no diagrama atualizado.                 |       Joao/Victor      |
| 3. Reorganizar o código para o padrão MVC (Model-View-Controller).                |      Victor       |
| 4. Alterar a persistência de dados para um Banco de Dados Relacional:             |      Victor       |
|    - Escolher e configurar o SGBD ( MySQL).           |        Victor     |
|    - Criar scripts de criação do banco (DDL) e população de dados (INSERTs).      |         Victor    |
|    - Utilizar JDBC para conectar o sistema ao banco de dados.                     |        Victor     |
| 5. Implementar as seguintes funcionalidades:                                      |        -     |
|    - Gestão de vagas, incluindo tipos:                                            |       Joao/Victor      |
|        - **Regular (R):** Sem alterações.                                         |        Joao/Victor     |
|        - **Idosos (I):** 15% de desconto.                                         |        Joao/Victor     |
|        - **PCD (E):** Vagas maiores e 13% de desconto.                            |        Joao/Victor     |
|        - **VIP (V):** Vagas maiores, cobertas, próximas ao portão, com 20% de acréscimo. |   Joao/Victor          |
|    - Histórico de uso:                                                            |             |
|        - Registrar histórico completo de uso do estacionamento.                   |    Victor         |
|        - Permitir filtro por datas de início e fim.                               |     -        |
|    - Relatórios gerenciais:                                                       |             |
|        - Valor total arrecadado.                                                  |    Victor         |
|        - Valor arrecadado em determinado mês.                                     |     Victor        |
|        - Valor médio de cada utilização.                                          |             |
|        - Ranking dos clientes que mais geraram arrecadação em um mês.             |     Victor        |
| 6. Atualizar a interface gráfica (Java Swing) para exibir:                        |             |
|    - Resultados das consultas SQL realizadas.                                     |        Victor     |
|    - Relatórios gerenciais e histórico de uso.                                    |             |
| 7. Implementar consultas SQL avançadas, utilizando:                               |             |
|    - **JOIN** para vincular informações entre tabelas (clientes, vagas, histórico).|             |
|    - **GROUP BY** para agrupar dados (ex.: total arrecadado por mês).             |             |
|    - Funções de agregação (`SUM`, `AVG`, etc.) para cálculos financeiros.          |             |
| 8. Implementar três classes de exceções personalizadas com tratamento adequado.   |             |

---

### Entregas da Sprint

| Tarefa                                                      | Responsável |
| ----------------------------------------------------------- | ----------- |
| 1. Preparar a apresentação final do sistema, abordando:                        |             |
|    - Funcionalidades implementadas (gestão de vagas, histórico, relatórios).    |             |
|    - Arquitetura utilizada (MVC, JDBC, integração com o banco de dados).        |       Victor      |
|    - Consultas SQL avançadas realizadas (ex.: JOIN, GROUP BY).                  |             |
|    - Proposta de valor do sistema e diferenciais implementados.                 |             |
| 2. Produzir o Relatório Técnico em PDF, contendo:                               |             |
|    - Visão geral das funcionalidades implementadas.                             |             |
|    - Consultas SQL detalhadas e seus resultados.                                |             |
|    - Diagramas atualizados (diagrama de classes e entidade-relacionamento).     |             |
|    - Decisões de projeto e justificativas.                                      |             |
|    - Relatos de experiência pessoal dos integrantes.                            |             |
|    - Principais desafios enfrentados e aprendizados obtidos.                    |             |
|    - Sugestões de melhorias futuras para o sistema.                             |             |

