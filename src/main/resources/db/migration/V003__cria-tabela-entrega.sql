use algalog;
create table entrega(
	id int not null auto_increment primary key,
	cliente_id bigint not null,
    taxa decimal(10,2) not null,
    status varchar(20) not null,
    data_pedido datetime not null,
    data_entrega datetime,
    
    destinatario_nome varchar(60) not null,
    destinatario_lougradouro varchar(255) not null,
	destinatario_numero varchar(20) not null,
    destinatario_complemento varchar(200) not null,
    destinatario_bairro varchar(30) not null
);

alter table entrega add constraint fk_entrega_cliente
foreign key (cliente_id) references cliente (id)