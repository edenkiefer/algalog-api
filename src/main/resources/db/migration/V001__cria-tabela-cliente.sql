CREATE SEQUENCE cliente_id_seq;

create table cliente (
	id bigint primary key DEFAULT nextval('cliente_id_seq'),
	nome varchar(60) not null,
	email varchar(255) not null,
	telefone varchar(20) not null
);

alter sequence cliente_id_seq owned by cliente.id;