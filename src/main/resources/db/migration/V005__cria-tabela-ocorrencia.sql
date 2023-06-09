create table `algalog`.`ocorrencia` (

	id bigint not null auto_increment primary key,
    entrega_id int not null,
    descricao text not null,
    dataRegisto datetime not null
    
);

alter table `algalog`.`ocorrencia` add constraint fk_ocorrencia_entrega
foreign key (entrega_id) references `algalog`.`entrega` (id);