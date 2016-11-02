CREATE DATABASE `tcc_scm`;

USE tcc_scm;

create table TipoEquipamento(
    idTipoEquipamento 				int not null auto_increment,
    txTipoEquipamento				varchar(100)  unique not null,
    idAtivoTipoEquipamento			enum('S', 'N') default 'S',
    primary key (idTipoEquipamento))
    default charset=utf8;

create table TipoManutencao(
    idTipoManutencao	 			int not null auto_increment,
    txTipoManutencao				varchar(100)  unique not null,
    idAtivoTipoManutencao			enum('S', 'N') default 'S',
    primary key (idTipoManutencao))
    default charset=utf8;

create table TipoOcorrencia(
    idTipoOcorrencia	 			int not null auto_increment,
    txTipoOcorrencia				varchar(100)  unique not null,
    idAtivoTipoOcorrencia			enum('S', 'N') default 'S',
    primary key ( idTipoOcorrencia))
    default charset=utf8;

create table MotivoAtraso(
    idMotivoAtraso	 			int not null auto_increment,
    txMotivoAtraso				varchar(100)  unique not null,
    idAtivoMotivoAtraso			enum('S', 'N') default 'S',
    primary key ( idMotivoAtraso))
    default charset=utf8;

create table MotivoCancelamento(
    idMotivoCancelamento	 		int not null auto_increment,
    txMotivoCancelamento			varchar(100)  unique not null,
    idAtivoMotivoCancelamento		enum('S', 'N') default 'S',
    primary key (idMotivoCancelamento))
    default charset=utf8;

Create table BemPatrimonial (
   	idBemPatrimonial				int not null auto_increment,
    marca 							varchar(100) not null,
	nip								varchar(10) not null,
	descricao						varchar(200), 
	dtCompra						date,
	campi							varchar(100),
primary key (idBemPatrimonial))
default charset=utf8;

create table Fornecedor(
	idFornecedor				int not null auto_increment,
	idCpfCnpjFornecedor			enum('1', '2') not null,
	nrCpfCnpjFornecedor			varchar(14) not null,
	nmFornecedor				varchar(100) not null,
	Endereco					varchar(100) not null,
	Cidade						varchar(40),
	Uf							varchar(2),
	Cep						    int,
	Telefone					char(20),
	Fax						    char(20),
	Email						varchar(100),
	Contato 				    varchar(100),
   primary key(idFornecedor))
  default charset=utf8;

create table Manutencao(
	idManutencao				int not null auto_increment,
	cdSgi						char(15) not null,
	dtInclusao					date not null,
    idTipoEquipamento			int,
    idTipoOcorrencia			int,
    primary key (idManutencao),
    foreign key (idTipoEquipamento) references tipoequipamento (idTipoEquipamento),
    foreign key (idTipoOcorrencia) references tipoocorrencia (idTipoOcorrencia))
    default charset=utf8;

create table FornecedorTipoEquipamento(
	idFornecedorTipoEquipamento		int not null auto_increment,
	idFornecedor					int not null,
	idTipoEquipamento				int not null,	
primary key (idFornecedorTipoEquipamento),
foreign key (idFornecedor) references fornecedor (idFornecedor),
foreign key (idTipoEquipamento) references tipoequipamento (idTipoEquipamento))
default charset=utf8;

create table Orcamento (
	idOrcamento					int not null auto_increment,
    qtDiaValidade   		    int,
	qtDiaGarantia			 	int,
    vlOrcamento					decimal (15,2) not null,
	vlDesconto					decimal (10,2),
    qtDiaEntrega				int,
    dtOrcamento					date not null,
    idManutencao				int,
	primary key (idOrcamento),
  	foreign key (idManutencao) references manutencao (idManutencao))
    default charset=utf8;

create table OrdemServico (
	idOrdemServico			int not null auto_increment,
	idOrcamento				int not null,
    nrOrdemServico			varchar(10) not null,
	dtIncioOrdemServico		date not null,
    dtEntregaPrevista 		date,
    dtAtraso				date,
    dtCancelamento			date,
    idMotivoCancelamento	int,
    idMotivoAtraso      	int,
    notaFornecedor			int,
    dtFimOrdemServico		date,
	primary key (idOrdemServico),
	foreign key (idOrcamento) references orcamento (idOrcamento),
	foreign key (idMotivoCancelamento) references motivocancelamento (idMotivoCancelamento),
    foreign key (idMotivoAtraso) references motivoatraso (idMotivoAtraso))
	default charset=utf8;

Create table Usuario (
   	idMatriculaUsuario				int unique not null,
   	nmFuncionarioUsuario			char(60) not null,
	idPerfilUsuario					int not null,
	idSenhaUsuario					char(30) not null,
    primary key (idMatriculaUsuario))
    default charset=utf8;
    
Create table Registro (
	idManutencao					int unique not null,
   	idBemPatrimonial				int unique not null,
    primary key (idManutencao, idBemPatrimonial),
    foreign key (idManutencao) references manutencao (idManutencao),
	foreign key (idBemPatrimonial) references bempatrimonial (idBempatrimonial))
    default charset=utf8;  

Create table AtribuiTipoEquipamento (
	idTipoEquipamento				int unique not null,
   	idFornecedor					int unique not null,
    primary key (idTipoEquipamento, idFornecedor),
    foreign key (idTipoEquipamento) references tipoequipamento (idTipoEquipamento),
	foreign key (idFornecedor) references fornecedor (idFornecedor))
    default charset=utf8;  

Create table OrcamentoFornecedor (
	idFornecedor					int unique not null,
   	idOrcamento						int unique not null,
    primary key (idFornecedor, idOrcamento),
    foreign key (idFornecedor) references fornecedor (idFornecedor),
	foreign key (idOrcamento) references orcamento (idOrcamento))
    default charset=utf8;  
    
    
Insert into tipoequipamento
( idTipoEquipamento, txTipoEquipamento, idAtivoTipoEquipamento)
Values
(DEFAULT, 'Ar Condicionado', DEFAULT),
(DEFAULT, 'Automóveis', DEFAULT),
(DEFAULT, 'Laboratoriais Hospitalar', DEFAULT),
(DEFAULT, 'Computadores', DEFAULT),
(DEFAULT, 'Elevador', DEFAULT),
(DEFAULT, 'Gerador de energia', DEFAULT);



insert into tipomanutencao 
(    idTipoManutencao, txTipoManutencao, idAtivoTipoManutencao)
values
(DEFAULT, 'Periódica', DEFAULT),
(DEFAULT, 'Corretiva Planejada', DEFAULT),
(DEFAULT, 'Detectiva', DEFAULT),
(DEFAULT, 'Corretiva', DEFAULT);



insert into tipoocorrencia
( idTipoOcorrencia, txTipoOcorrencia,  idAtivoTipoOcorrencia)  
values
(DEFAULT, 'Corrosão', DEFAULT),
(DEFAULT, 'Corte', DEFAULT),
(DEFAULT, 'Desgaste', DEFAULT),
(DEFAULT, 'Desligamento', DEFAULT),
(DEFAULT, 'Desregulagem', DEFAULT),
(DEFAULT, 'Entupimento', DEFAULT),
(DEFAULT, 'Folga', DEFAULT),
(DEFAULT, 'Furo', DEFAULT),
(DEFAULT, 'Mal Contato', DEFAULT),
(DEFAULT, 'Oxidação', DEFAULT),
(DEFAULT, 'Quebra', DEFAULT),
(DEFAULT, 'Queda', DEFAULT),
(DEFAULT, 'Queima', DEFAULT),
(DEFAULT, 'Rompimento', DEFAULT);



Insert into MotivoAtraso
( idMotivoAtraso, txMotivoAtraso, idAtivoMotivoAtraso)
Values
(DEFAULT, 'Atraso na entrega por terceiros', DEFAULT),
(DEFAULT, 'Condições climáticas', DEFAULT),
(DEFAULT, 'Condições externas', DEFAULT),
(DEFAULT, 'Equipamento não liberado pelo setor', DEFAULT),
(DEFAULT, 'Falta de material em estoque', DEFAULT),
(DEFAULT, 'Falta de mão de obra', DEFAULT),
(DEFAULT, 'Troca imprevista de material', DEFAULT);



Insert into MotivoCancelamento
( idMotivoCancelamento, txMotivoCancelamento,  idAtivoMotivoCancelamento)
Values
(DEFAULT, 'Condições climáticas', DEFAULT),
(DEFAULT, 'Condições externas', DEFAULT),
(DEFAULT, 'Serviço já foi realizado em outra O.S.', DEFAULT),
(DEFAULT, 'Serviço realizado em manutenção corretiva', DEFAULT),
(DEFAULT, 'Serviço será reprogramado', DEFAULT);



Insert into BemPatrimonial
(idBemPatrimonial, marca, nip, descricao, dtCompra, campi)
Values
(DEFAULT, 'Apple','0000030101','iMac de 21,5 polegadas - 1,6GHz 1 TB','2012-01-09','Asa Norte' ),
(DEFAULT, 'Apple','0000030102','iMac de 21,5 polegadas - 1,6GHz 1 TB','2012-01-09','Asa Norte' ),
(DEFAULT, 'Apple','0000030103','iMac de 21,5 polegadas - 1,6GHz 1 TB','2012-01-09','Asa Norte' ),
(DEFAULT, 'Apple','0000030104','iMac de 21,5 polegadas - 1,6GHz 1 TB','2012-01-09','Asa Norte' ),
(DEFAULT, 'Apple','0000030105','iMac de 21,5 polegadas - 1,6GHz 1 TB','2012-01-09','Asa Norte' ),
(DEFAULT, 'Apple','0000030106','iMac de 21,5 polegadas - 1,6GHz 1 TB','2012-01-09','Asa Norte' ),
(DEFAULT, 'Apple','0000030107','iMac de 21,5 polegadas - 1,6GHz 1 TB','2012-01-09','Asa Norte' ),
(DEFAULT, 'Apple','0000030108','iMac de 21,5 polegadas - 1,6GHz 1 TB','2012-01-09','Asa Norte' ),
(DEFAULT, 'Nikon','0000030109','Microscópio Biológico Nikon E200','2012-08-27','Asa Norte' ),
(DEFAULT, 'Nikon','0000030110','Microscópio Biológico Nikon E200','2012-08-27','Asa Norte' ),
(DEFAULT, 'Nikon','0000030111','Microscópio Biológico Nikon E200','2012-08-27','Asa Norte' ),
(DEFAULT, 'Nikon','0000030112','Microscópio Biológico Nikon E200','2012-08-27','Asa Norte' ),
(DEFAULT, 'Nikon','0000030113','Microscópio Biológico Nikon E200','2012-08-27','Asa Norte' ),
(DEFAULT, 'Nikon','0000030114','Microscópio Biológico Nikon E200','2012-08-27','Asa Norte' ),
(DEFAULT, 'Nikon','0000030115','Microscópio Biológico Nikon E200','2012-08-27','Asa Norte' ),
(DEFAULT, 'Nikon','0000030116','Microscópio Biológico Nikon E200','2012-08-27','Asa Norte' ),
(DEFAULT, 'Nikon','0000030117','Microscópio Biológico Nikon E200','2012-08-27','Asa Norte' ),
(DEFAULT, 'Visa Cooler','0000030118','Geladeira / Refrigerador Expositor 800L','2012-08-27','Asa Norte' ),
(DEFAULT, 'Visa Cooler','0000030119','Geladeira / Refrigerador Expositor 800L','2012-08-27','Asa Norte' ),
(DEFAULT, 'Visa Cooler','0000030120','Geladeira / Refrigerador Expositor 800L','2012-08-27','Asa Norte' ),
(DEFAULT, 'Visa Cooler','0000030121','Geladeira / Refrigerador Expositor 800L','2012-08-27','Asa Norte' ),
(DEFAULT, 'LG','0000030122','Ar Condicionado Split Cassete Lg Inverter 54.000 Btus','2012-08-27','Asa Norte' ),
(DEFAULT, 'LG','0000030123','Ar Condicionado Split Cassete Lg Inverter 54.000 Btus','2012-08-27','Asa Norte' ),
(DEFAULT, 'LG','0000030124','Ar Condicionado Split Cassete Lg Inverter 54.000 Btus','2012-08-27','Asa Norte' ),
(DEFAULT, 'LG','0000030125','Ar Condicionado Split Cassete Lg Inverter 54.000 Btus','2012-08-27','Asa Norte' ),
(DEFAULT, 'LG','0000030126','Ar Condicionado Split Cassete Lg Inverter 54.000 Btus','2012-08-27','Asa Norte' ),
(DEFAULT, 'LG','0000030127','Ar Condicionado Split Cassete Lg Inverter 54.000 Btus','2012-08-27','Asa Norte' ),
(DEFAULT, 'LG','0000030128','Ar Condicionado Split Cassete Lg Inverter 54.000 Btus','2012-08-27','Asa Norte' ),
(DEFAULT, 'LG','0000030129','Ar Condicionado Split Cassete Lg Inverter 54.000 Btus','2012-08-27','Asa Norte' ),
(DEFAULT, 'LG','0000030130','Ar Condicionado Split Cassete Lg Inverter 54.000 Btus','2012-08-27','Asa Norte' );



Insert into Fornecedor
( idFornecedor, idCpfCnpjFornecedor, nrCpfCnpjFornecedor, nmFornecedor, Endereco, Cidade, Uf, Cep, Telefone, Fax, Email, Contato )
Values
(DEFAULT,'2','96737374000163','Total Informatica Ltda Me','CND 01 LOTE 20 LOJA 3 Taguatinga Norte',' Brasília','DF',7212015,'61 40425177','61 9929-7308','totalinfor@contato.br','Zé das Couves'),
(DEFAULT,'2','09426633000130','Itss Informacao, Tecnologia e Servicos Ltda - Epp ','R Joao De Abreu, 116, Quadrae8 Lote 42E Sala B-902 Edif Euro Working Concept,','Goiania','GO',74120110,'62 3353-5754','62 3353-5754','egfcontabilidade@hotmai.com','Rodrigo'),
(DEFAULT,'2','01644731000132','CTIS Tecnologia SA',' Sepn 511 Bl A, 0 Ant ',' Brasília','DF', 70750541,'61 3433-9207','61 3433-9207','trabalheconoscodf@ctis.com.br','Geraldo');