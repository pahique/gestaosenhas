insert into tipo_senha(codigo, sigla, descricao) values (1, 'P', 'Preferencial');
insert into tipo_senha(codigo, sigla, descricao) values (2, 'N', 'Normal');

insert into tipo_usuario(codigo, descricao) values (1, 'Gerente');
insert into tipo_usuario(codigo, descricao) values (2, 'Cliente');

insert into contador_senha(codigo_tipo_senha, numero_atual) values (1, 0);
insert into contador_senha(codigo_tipo_senha, numero_atual) values (2, 0);
