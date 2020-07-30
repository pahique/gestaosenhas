# Projeto de Gestão de Senhas de Atendimento

Sistema simples de controle de senhas para atendimento de clientes por ordem de chegada, incluindo o atendimento prioritário para clientes preferenciais.

## Instalação do servidor

`mvn spring-boot:run`

para subir o servidor diretamente via SpringBoot com Tomcat 

ou 

`mvn clean package`

para gerar o arquivo .war 


## Execução do Front-end

* Iniciar o processo

```
cd client
	
npm start
```

* Abrir o browser nas seguintes URLs:

`http://localhost:3000/` para perfil público (cliente)

`http://localhost:3000/gerente/` para perfil de gerente


## Observações sobre o funcionamento

* Foram utilizados contadores separados para senhas preferenciais e para senhas normais.

* Quando o gerente zera os contadores, as senhas que já foram emitidas continuam na fila para serem chamadas. Somente as novas senhas que forem emitidas levarão em conta os novos contadores.

* Ambos os perfis conseguem visualizar o painel com a última senha chamada. Quando o gerente chama a próxima senha, o painel é atualizado em todas as janelas abertas.

* Foi criada uma segurança na API do gerente, tornando necessário usuário/senha. Usar 'gerente/password' nesse caso.












