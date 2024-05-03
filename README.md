# Sistema de Gerenciamento de Agendamentos


Este é um sistema de gerenciamento de agendamentos desenvolvido em Java com interface gráfica usando Swing. Ele oferece funcionalidades abrangentes para cadastrar, buscar, remover e atualizar clientes, profissionais, serviços e agendamentos. O sistema utiliza uma conexão JDBC (Java Database Connectivity) para interagir com um banco de dados MySQL. Além das operações básicas de CRUD (Create, Read, Update, Delete), o sistema também oferece recursos avançados, como busca por diferentes critérios, exibição de listas de clientes, profissionais, serviços e agendamentos cadastrados, proporcionando uma visão abrangente do estado atual do sistema. 

## Requisitos

- Java Development Kit (JDK) versão 21 ou superior
- MySQL Server
- MySQL Connector/J (Driver JDBC)

## Configuração do Banco de Dados

1. Instale o MySQL Server em seu sistema, se ainda não estiver instalado.
2. Crie um banco de dados chamado `gerenciamento_agendamentos`.
3. Execute o script `script_banco.sql` fornecido neste repositório para criar as tabelas necessárias.

## Configuração do Projeto

1. Clone este repositório em sua máquina local.
2. Certifique-se de ter o JDK instalado e configurado corretamente em sua máquina.
3. Importe o projeto em sua IDE preferida.

## Configuração da Conexão JDBC

Para configurar a conexão JDBC com o banco de dados:

1. Abra o arquivo `conexao.properties` localizado no projeto.
2. Insira as informações de conexão JDBC conforme o seu ambiente:

## Executando o Projeto

1. Abra o projeto em sua IDE.
2. Execute a classe `Program.java` para iniciar o sistema.

## Funcionalidades

- **Clientes:** Cadastro, busca, remoção, atualização e exibição de clientes cadastrados.
- **Profissionais:** Cadastro, busca, remoção, atualização e exibição de profissionais cadastrados.
- **Serviços:** Cadastro, busca, remoção, atualização e exibição de serviços cadastrados.
- **Agendamentos:** Cadastro, busca, remoção, atualização e exibição de agendamentos.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests com melhorias, correções de bugs ou novas funcionalidades.