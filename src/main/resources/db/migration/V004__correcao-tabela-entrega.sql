ALTER TABLE `algalog`.`entrega` 
CHANGE COLUMN `destinatario_lougradouro` `destinatario_logradouro` VARCHAR(255) NOT NULL ,
CHANGE COLUMN `destinatario_complemento` `destinatario_complemento` VARCHAR(200) NULL;

    