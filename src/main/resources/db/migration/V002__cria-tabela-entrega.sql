CREATE SEQUENCE entrega_id_seq;

create table entrega (
	id bigint primary key DEFAULT nextval('entrega_id_seq'),
	cliente_id bigint not null,
	taxa decimal(10, 2) not null,
	status varchar(20) not null,
	data_pedido timestamp not null,
	data_finalizacao timestamp,
	
	destinatario_nome varchar(60) not null,
	destinatario_logradouro varchar(255) not null,
	destinatario_numero varchar(30) not null,
	destinatario_complemento varchar(60) not null,
	destinatario_bairro varchar(30) not null,
	constraint fk_entrega_cliente foreign key (cliente_id) references cliente(id)
);

alter sequence entrega_id_seq owned by entrega.id;