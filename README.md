#  Sistema de Estoque Inteligente
 
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005F0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white)
 
O **Sistema de Estoque Inteligente** é uma aplicação completa para controle, gerenciamento e auditoria de inventário de produtos. Desenvolvido com foco em boas práticas de arquitetura de software, o sistema garante a segurança dos dados e mantém um histórico rigoroso de todas as ações realizadas no estoque.
 
---
 
##  Principais Funcionalidades
 
- **Autenticação Segura:** Controle de acesso de usuários baseado em sessões ativas (`HttpSession`), garantindo que apenas funcionários autorizados manipulem o inventário.
- **CRUD Completo de Produtos:** Cadastro, listagem, atualização e deleção de itens com validação de integridade.
- **Trilha de Auditoria Automatizada (Histórico):** Toda e qualquer alteração no sistema gera um registro automático na tabela de movimentações, mapeando o tipo de ação (`CADASTRO`, `ENTRADA`, `SAÍDA`, `ALTERAÇÃO`, `EXCLUSÃO`), o saldo do estoque no exato momento da operação e o usuário responsável.
- **Deleção Lógica (Soft Delete):** Produtos excluídos não são apagados permanentemente do banco de dados para preservar a integridade referencial do histórico de vendas e auditorias.
- **Interface Dinâmica:** Painel de movimentações com filtros avançados via JavaScript (Fetch API) e estilização condicional por tipo de evento.
 
---
 
##  Tecnologias Utilizadas
 
- **Back-end:** Java 17, Spring Boot, Spring Data JPA, Hibernate.
- **Front-end:** HTML5, CSS3, JavaScript Vanilla (Assíncrono com Fetch API).
- **Banco de Dados:** MySQL.
- **Gerenciador de Dependências:** Maven.
 
---
 
##   Como Executar o Projeto
 
### Pré-requisitos
- Java JDK 17 ou superior instalado.
- MySQL Server rodando localmente.
- IDE de sua preferência (VS Code, IntelliJ, Eclipse/NetBeans).
 
### 1. Configuração do Banco de Dados
 
O sistema está preparado para criar o banco de dados automaticamente caso ele não exista. Por padrão e segurança, a aplicação utiliza variáveis de ambiente para proteger dados sensíveis. 

Abra as pastas `src/main/resources`, clique no arquivo `application.properties` e substitua os dados após os dois pontos “ : ” pelas credenciais do seu banco local:
 
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/sistema_estoque?createDatabaseIfNotExist=true}
spring.datasource.username=${DB_USER:root} 
spring.datasource.password=${DB_PASS:sua_senha_aqui}
2. Executando a Aplicação
Clone o repositório para a sua máquina local executando o comando abaixo no seu terminal:

git clone https://github.com/gabriel-c-oliveira946/Sistema-de-Estoque-Inteligente.git
Abra a pasta clonada na sua IDE de preferência e inicie a aplicação através da classe principal SistemaestoqueApplication.java.

O servidor iniciará na porta padrão. Acesse no seu navegador através do link abaixo para ir direto para a tela de autenticação:

http://localhost:8080/login.html

   Desenvolvedores e Contribuidores
Este projeto foi desenvolvido como parte das avaliações acadêmicas do curso de tecnologia, alcançando a Nota Máxima (10) perante a banca avaliadora pela robustez e defesa técnica da arquitetura de software implementada.

Gabriel Costa - Desenvolvimento Back-end, Regras de Negócio e Arquitetura do Banco - GitHub

Caíque - Levantamento de Requisitos e Validação de Escopo

Rafael - Documentação Técnica e Apoio de Negócio
