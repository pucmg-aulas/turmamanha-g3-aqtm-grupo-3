# Instruções de Uso - Gestão de Estacionamentos Java Parkings

Este documento fornece as instruções de uso do sistema **Gestão de Estacionamentos - Java Parkings**. Siga estas etapas para acessar e utilizar todas as funcionalidades do sistema.

## 1. Acesso ao Sistema

1. **Instalação**: Certifique-se de que o projeto e o banco de dados estão corretamente instalados e configurados em sua máquina. Para executar a aplicação:
   - Abra o projeto no seu ambiente de desenvolvimento Java (preferencialmente no IntelliJ IDEA, MySQL).
   - Compile e execute o sistema.
   
2. **Login**: Ao iniciar o projeto, selecione o estacionamento que deseja gerenciar.

## 2. Menu Inicial

1. **Estacionamento**:
   - Visualização de todas as vagas do estacionamento.
   - As vagas são identificadas pelos tipos (IDO, PCD, R, VIP) seguidos por uma numeração (1...4), ex.: IDO1, PCD1, PCD2.
   - **Vagas verdes** estão disponíveis. Ao clicar em uma vaga verde, será disponibilizada a opção para estacionar pela placa do veículo. Caso a placa não seja encontrada, o sistema solicitará o cadastro do carro com o ID do cliente e a placa.
   - **Vagas vermelhas** indicam ocupação. Ao clicar em uma vaga vermelha, será disponibilizada a opção para registrar a saída e calcular o valor a ser pago.

2. **Clientes**:
   - **Cadastrar Cliente**: Cadastre o cliente com ID e nome. Clientes podem ser registrados como "Anônimos".
   - **Listar Clientes**: Exibe uma tabela com todos os clientes e seus IDs.
   - **Veículos**: Listagem de todos os veículos registrados, com a possibilidade de editar e visualizar detalhes de cada veículo associado aos clientes.

3. **Faturamento**:
   - Selecione **Faturamento** para visualizar relatórios financeiros.
   - Use os filtros para selecionar um mês específico ou visualizar o valor total arrecadado.
   - **Top 5 Clientes por Mês**: Exibe o ranking dos cinco clientes que mais geraram receita em um mês selecionado.


## 3. Controle de Ocupação de Vagas

1. **Selecionar e Ocupar Vaga**:
   - Acesse **Estacionamento**, no menu inicial.
   - Escolha uma vaga verde disponível e associe-a a um cliente e veículo.
   - Confirme a ocupação.

2. **Desocupar Vaga**:
   - Acesse **Estacionamento**, no menu inicial. e localize a vaga que deseja desocupar.
   - Confirme para liberar a vaga e registrar a saída.

3. **Cobrança e Tarifação**:
   - O sistema calcula automaticamente o valor a ser cobrado (R$4 a cada 15 minutos, com valor máximo de R$50).
   - Caso o cliente ocupe uma vaga com desconto (Idoso ou PCD) ou adicional (VIP), o sistema aplicará as tarifas corretas automaticamente.

## 4. Relatórios Financeiros

1. **Faturamento**:
   - Acesse a seção **Faturamento**.
   - Visualize o valor total faturado.

2. **Relatório Mensal**:
   - Em **Faturamento**, selecione o mês desejado.
   - O sistema exibirá o valor arrecadado no período.
   -  O sistema exibirá o ranking arrecadado dos clientes que mais geraram receita em um determinado mês TOP 5


## 5. Manutenção e Persistência de Dados

1. **Persistência em Banco de Dados MySQL**:
   - Todos os dados do sistema são salvos no banco de dados MySQL, garantindo a persistência das informações de maneira segura e organizada.
   - Você pode realizar backup e restauração dos dados através da seção **Manutenção** no sistema.
   - Certifique-se de que o MySQL está corretamente configurado e que as credenciais de acesso estão atualizadas no sistema.

2. **Configuração de Conexão com o Banco de Dados**:
   - Na versão atual, o sistema utiliza MySQL para a persistência dos dados.

## 7. Interface Gráfica e Navegação

- A interface gráfica, construída com **Java Swing**, permite uma navegação intuitiva pelas seções do sistema. 

## 8. Tratamento de Exceções

O sistema exibe mensagens de erro claras caso ocorra algum problema, como:
- ~~Exceções de Conexão: caso o sistema não consiga se conectar ao banco de dados.~~
- ~~Exceções de Ocupação de Vagas: se tentar ocupar uma vaga já ocupada.~~
- ~~Exceções de Dados Inválidos: caso sejam inseridos dados incorretos nos campos obrigatórios.~~
