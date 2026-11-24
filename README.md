# 📅 Microsserviço Agendador de Tarefas (`agendador-tarefas`)

![Java](https://shields.io)
![Spring Boot](https://shields.io)
![Groovy](https://shields.io)
![MongoDB](https://shields.io)
![Docker](https://shields.io)

Microsserviço responsável pelo CRUD de tarefas agendadas, filtragem por janela de horário e atualização de status de notificação (`PENDENTE` / `NOTIFICADO`) utilizando **MongoDB**.

## 🛡️ Segurança e Arquitetura
* **Persistência NoSQL:** Spring Data MongoDB configurado via `spring.mongodb.uri` (padrão Spring Boot 4).
* **Prevenção contra IDOR:** O e-mail proprietário da tarefa é extraído diretamente do *Subject* do token JWT validado, impedindo manipulação de registros de terceiros.
* **Comunicação Interna:** Valida tokens via OpenFeign junto ao `usuario-service` e responde ao BFF na porta interna `8081`.

## ⚙️ Variáveis de Ambiente Necessárias
* `MONGO_URI`: String de conexão do MongoDB (ex: `mongodb://mongo:27017/db_agendador`)
* `USUARIO_URL`: URL interna do serviço de usuários (`http://usuario:8080`)
* `JWT_SECRET`: Chave secreta compartilhada de 128 caracteres Base64

## 🙏 Créditos e Agradecimentos
Desenvolvido com apoio da **Javanauta Academy** (mentora **Angélica Weiler**), assistência arquitetural e pair programming com **IA (Google)**, e dedicado a **Deus** e à minha família (**esposa, filhos, neto, nora e genro**).
