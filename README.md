# 📑 Sistema de Gerenciamento de Editais e Propostas

### Descrição
Este o repositório para o projeto do Hands On que consiste em um sistema de gerenciamento de editais e propostas desenvolvido com Java Spring Boot, com o objetivo de facilitar a administração de editais e a submissão de propostas por pesquisadores do SIGFAPAC. A aplicação permite que coordenadores criem editais, configurem campos personalizados para cada edital e que pesquisadores submetam suas propostas de forma prática e eficiente.

### Funcionalidades
- **🔍 Gerenciamento de Editais:** Criação, edição e exclusão de editais, com configuração de campos específicos para cada edital.
- **👥 Cadastro de Pesquisadores:** Registro detalhado de pesquisadores, incluindo informações pessoais, endereços e vínculo institucional.
- **📄 Submissão de Propostas:** Pesquisadores podem submeter propostas vinculadas a editais, com suporte para upload de documentos e imagens.
- **📊 Avaliação de Propostas:** Sistema para aprovação ou rejeição de propostas pelos coordenadores com histórico de submissões.
- **📚 Hierarquia de Áreas de Conhecimento:** Cadastro de áreas de conhecimento e suas respectivas subáreas, permitindo uma estrutura organizada para classificação das propostas.

### Tecnologias Utilizadas
- **Backend:** 
- **Frontend:**
- **Banco de Dados:** MySQL
- **Controle de Versão:** Git
- **Ferramentas de Build:** Maven
- **Documentação:** Swagger

### Estrutura do Projeto
O projeto está organizado de acordo com as melhores práticas de desenvolvimento com Spring Boot, usando uma arquitetura baseada em camadas:

- **Model:** Definição das entidades e suas relações.
- **Repository:** Acesso e manipulação dos dados no banco de dados.
- **Service:** Contém a lógica de negócios.
- **Controller:** Endpoints para comunicação com o frontend e APIs RESTful.
- **DTOs e Mappers:** Conversão de entidades para objetos de transferência de dados e vice-versa.
- **Config:** Configurações de segurança e do ambiente.
