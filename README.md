# Sistema de Estoque Inteligente
 
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Render](https://img.shields.io/badge/Render-%2346E3B7.svg?style=for-the-badge&logo=render&logoColor=white)
 
O **Sistema de Estoque Inteligente** é uma aplicação completa para controle, gerenciamento e auditoria de inventário de produtos. Desenvolvido com foco em boas práticas de arquitetura de software, o sistema garante a segurança dos dados e mantém um histórico rigoroso de todas as ações realizadas no estoque.
 
---
 
##  Aplicação em Produção

O sistema está hospedado na nuvem e pode ser acessado publicamente através do link abaixo:

👉 **[Acessar o Sistema de Estoque Inteligente](https://sistema-de-estoque-inteligente-lcn4.onrender.com/login.html)**

> ⚠️ **Nota sobre o acesso:** A aplicação está hospedada na camada gratuita do Render (Serverless). Caso o sistema fique sem acessos por mais de 15 minutos, o servidor entra em modo de hibernação (*spin-down*) para economizar recursos. **O primeiro acesso após a hibernação pode levar cerca de 50 segundos para "acordar" o sistema.** Os acessos subsequentes serão instantâneos.

---

##  Contexto Acadêmico e Reconhecimento

Este projeto foi desenvolvido como parte das avaliações práticas do curso técnico em **Desenvolvimento de Sistemas** no **SENAI Dendezeiros**. 

A solução foi apresentada à banca avaliadora e recebeu **Nota Máxima (10)**, com destaque especial para a robustez da arquitetura de software implementada, a segurança da trilha de auditoria e a defesa técnica do projeto.

---

##  Arquitetura e Infraestrutura Cloud

Este projeto evoluiu de uma aplicação local para uma arquitetura moderna baseada em nuvem, demonstrando domínio de DevOps e conteinerização:

* **Conteinerização:** Aplicação empacotada utilizando **Docker** com *multi-stage builds* (Maven para build, Eclipse Temurin JDK 17 para execução), garantindo uma imagem leve e segura.
* **Hospedagem Back-end:** Deploy automatizado e contínuo (CI/CD) via **Render** integrado diretamente à branch principal do GitHub.
* **Banco de Dados Gerenciado:** Banco de dados **MySQL** hospedado na infraestrutura de nuvem do **Aiven**, garantindo isolamento e conexão segura via protocolo SSL.

---

##  Principais Funcionalidades
 
* **Autenticação Segura:** Controle de acesso de usuários baseado em sessões ativas (`HttpSession`), garantindo que apenas funcionários autorizados manipulem o inventário.
* **CRUD Completo de Produtos:** Cadastro, listagem, atualização e deleção de itens com validação de integridade.
* **Trilha de Auditoria Automatizada (Histórico):** Toda e qualquer alteração no sistema gera um registro automático na tabela de movimentações, mapeando o tipo de ação (`CADASTRO`, `ENTRADA`, `SAÍDA`, `ALTERAÇÃO`, `EXCLUSÃO`), o saldo do estoque no exato momento da operação e o usuário responsável.
* **Deleção Lógica (Soft Delete):** Produtos excluídos não são apagados permanentemente do banco de dados para preservar a integridade referencial do histórico de vendas e auditorias.
* **Interface Dinâmica:** Painel de movimentações interativo e reativo utilizando JavaScript Vanilla (Fetch API) consumindo os endpoints RESTful do back-end.
 
---

## 👥 Desenvolvedores e Contribuidores

- **[Gabriel Costa de Oliveira](https://github.com/gabriel-c-oliveira946)** - Arquitetura de Nuvem (Docker/Render/Aiven), Desenvolvimento Back-end/Front-end, Regras de Negócio e Controle de Versão e Hospedagem.
- **[Caíque André Brandão](https://github.com/caique-brandao09)** - Levantamento de Requisitos e Validação de Escopo.
- **Rafael Gramosa** - Documentação Técnica e Apoio de Negócio.
