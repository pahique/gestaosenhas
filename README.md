# Projeto de Gestão de Senhas de Atendimento

Sistema simples de controle de senhas para atendimento de clientes por ordem de chegada, incluindo o atendimento prioritário para clientes preferenciais.

## Instalação do servidor

`mvn spring-boot:run`

para subir o servidor diretamente via SpringBoot com Tomcat 

ou 

`mvn clean package`

para gerar o arquivo .war 


## API


As URLs utilizadas são uma primeira versão, e ainda podem ser remodeladas para que fiquem mais claras ou mais padronizadas/enxutas. Para o momento são as seguintes.


* Gerar uma nova senha. Obs: tipoSenha = 1 (preferencial) ou 2 (normal)

Exemplo:

	curl -X POST -H 'Content-Type: application/json' -d '{"tipoSenha": 1}' http://localhost:8080/gestaosenhas/api/senha/nova/

	{"senha":{"siglaTipoSenha":"P","numero":1,"senhaFormatada":"P0001"},"error":null}

	
* Obter a ultima senha chamada

Exemplo:
 
	curl -X GET -H 'Content-Type: application/json' http://localhost:8080/gestaosenhas/api/senha/ultima/
 
	{"senha":{"siglaTipoSenha":"P","numero":1,"senhaFormatada":"P0001"},"error":null}


 
* Chamar a próxima senha (apenas usuário com role de GERENTE tem essa permissão)

Exemplo:

	curl -X POST -H 'Content-Type: application/json' -d '' http://localhost:8080/gestaosenhas/api/admin/senha/proxima/ -u gerente:password

	{"senha":{"siglaTipoSenha":"P","numero":2,"senhaFormatada":"P0002"},"error":null}


* Reiniciar os contadores (apenas usuário com role de GERENTE tem essa permissão)

Por tipo (reinicia apenas o contador do tipo informado):

	curl -X DELETE -H 'Content-Type: application/json' -d '{"tipoSenha": 2}' http://localhost:8080/gestaosenhas/api/admin/senha/reset/ -u gerente:password

	
	{"contadores":[{"tipoSenha":"N","numeroAtual":0},{"tipoSenha":"P","numeroAtual":1}],"error":null}

Ambos os tipos:

	curl -X DELETE -H 'Content-Type: application/json' -d '{}' http://localhost:8080/gestaosenhas/api/admin/senha/reset/ -u gerente:password 

	
	{"contadores":[{"tipoSenha":"N","numeroAtual":0},{"tipoSenha":"P","numeroAtual":0}],"error":null}



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












