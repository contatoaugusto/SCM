CREATE DATABASE `tcc_scm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE tcc_scm;

create table TipoEquipamento(
    idTipoEquipamento 				int not null auto_increment,
    txTipoEquipamento				varchar(100)  unique not null,
    icAtivo 						enum('S', 'N') default 'S',
    primary key (idTipoEquipamento))
    default charset=utf8;

create table TipoManutencao(
    idTipoManutencao	 			int not null auto_increment,
    txTipoManutencao				varchar(100)  unique not null,
    icAtivo 						enum('S', 'N') default 'S',
    primary key (idTipoManutencao))
    default charset=utf8;

create table TipoOcorrencia(
    idTipoOcorrencia	 			int not null auto_increment,
    txTipoOcorrencia				varchar(100)  unique not null,
    icAtivo 						enum('S', 'N') default 'S',
    primary key ( idTipoOcorrencia))
    default charset=utf8;

create table MotivoAtraso(
    idMotivoAtraso	 			int not null auto_increment,
    txMotivoAtraso				varchar(100)  unique not null,
    icAtivo 						enum('S', 'N') default 'S',
    primary key ( idMotivoAtraso))
    default charset=utf8;

create table MotivoCancelamento(
    idMotivoCancelamento	 		int not null auto_increment,
    txMotivoCancelamento			varchar(100)  unique not null,
    icAtivo 						enum('S', 'N') default 'S',
    primary key (idMotivoCancelamento))
    default charset=utf8;

create table QuesitoAvaliacao(
    idQuesitoAvaliacao	 				int not null auto_increment,
    txQuesitoAvaliacao					varchar(100)  unique not null,
    icAtivo 							enum('S', 'N') default 'S',
    primary key (idQuesitoAvaliacao))
    default charset=utf8;

Create table BemPatrimonial (
   	idBemPatrimonial				int not null auto_increment,
	txNipBemPatrimonial				varchar(10) not null,
	nmBemPatrimonial				varchar(200),
	dtGarantiaBemPatrimonial			date,
	dtManutencaoBemPatrimonial			date,
	icAtivo 							enum('S', 'N') default 'S',
	nmResponsavelBemParimonial			varchar(100),
	telResponsavelBemPatrimonial			char(15),
primary key (idBemPatrimonial))
default charset=utf8;

create table MovimentacaoPatrimonial (
	idMovimentacaoPatrimonial			int not null auto_increment,
	idBemPatrimonial					int  not null,
	dtInicialMovimentacaoPatrimonial		date	not null,
	dtFinalMovimentacaoPatrimonial		date,
	nmCampiMovimentacaoPatrimonial		varchar(100) not null,
	txEnderecoMovimentacaoPatrimonial		varchar(100) not null,
   primary key(idMovimentacaoPatrimonial),
   foreign key (idBemPatrimonial) references BemPatrimonial (idBemPatrimonial))
   default charset=utf8;

create table Fornecedor(
	idFornecedor				int not null auto_increment,
	idCpfCnpjFornecedor			enum('1', '2') not null,
	nrCpfCnpjFornecedor			varchar(14) not null,
	nmRazaoSocialFornecedor		varchar(150) not null,
	nmFantasiaFornecedor		varchar(150) not null,
	txEnderecoFornecedor		varchar(100) not null,
	txComplementoFornecedor		varchar(100),
	txCidadeFornecedor			varchar(40),
	txUfFornecedor				varchar(2),
	nrCepFornecedor			    int,
	nrTelefone1Fornecedor		char(20),
	nTelefone2Fornecedor		char(20),
	nrFaxFornecedor			    char(20),
	txEmailFornecedor			varchar(60),
	icAtivo 				    enum('S', 'N') default 'S',
   primary key(idFornecedor))
  default charset=utf8;

create table Solicitacao(
	idSolicitacao				int not null auto_increment,
	cdSgiSolicitacao			char(15) not null,
	nrMatriculaFuncionarioSolicitacao	int,
	txNomeFuncionarioSolicitacao		varchar(60)  not null,
	dtSolicitacaoSgi				date not null,
    txSolicitacaoSgi				varchar(10000) not null,
    primary key (idSolicitacao))
    default charset=utf8;

Create table BemPatrimonialTipoEquipamento (
	idBemPatrimonialTipoEquipamento		int not null auto_increment,
   	idBemPatrimonial				int not null,
	idTipoEquipamento				int,
   	primary key (idBemPatrimonialTipoEquipamento),
foreign key (idBemPatrimonial) references bempatrimonial (idBemPatrimonial),
foreign key (idTipoEquipamento) references tipoequipamento (idTipoEquipamento))
default charset=utf8;

create table FornecedorTipoEquipamento(
	idFornecedorTipoEquipamento		int not null auto_increment,
	idFornecedor				int not null,
	idTipoEquipamento		int not null,	
primary key (idFornecedorTipoEquipamento),
foreign key (idFornecedor) references fornecedor (idFornecedor),
foreign key (idTipoEquipamento) references tipoequipamento (idTipoEquipamento))
default charset=utf8;

create table Manutencao(
	idManutencao	 			int not null auto_increment,
   	idSgiManutencao				int not null,
	dtSolicitacaoManutencao		date,
	idMotivoCancelamento		int,
	dtCancelamentoManutencao	date,
    primary key (idManutencao),
	foreign key (idSgiManutencao) references solicitacao (idSolicitacao),
	foreign key (idMotivoCancelamento) references motivocancelamento (idMotivoCancelamento))
    default charset=utf8;

create table ManutencaoBemPatrimonial(
   	idManutencaoBemPatrimonial	int not null auto_increment,
	idManutencao				int not null,
   	idBemPatrimonial			int not null,
   	idTipoOcorrencia     		int not null,
    idTipoManutencao     		int not null,
   primary key (idManutencaoBemPatrimonial),
   foreign key (idManutencao) references manutencao (idManutencao),
   foreign key (idBemPatrimonial) references bempatrimonial (idBemPatrimonial),
   foreign key (idTipoOcorrencia) references tipoocorrencia (idTipoOcorrencia),
   foreign key (idTipoManutencao) references tipoManutencao (idTipoManutencao))
   default charset=utf8;

create table Orcamento (
	idOrcamento					int not null auto_increment,
	idManutencaoOrcamento       int not null,
	idManutencaoBemPatrimonial 	int not null,
	idBemPatrimonial			int not null,
	idFornecedor				int not null,
	dtOrcamento					date not null,
	vlOrcamento					decimal (15,2) not null,
	vlDesconto					decimal (10,2),
	qtDiaEntregaOrcamento		int,
	qtDiaPagamentoOrcamento		int,
	qtDiaGarantiaOrcamento		int,
	qtDiaPrazoOrcamento			int,
    icAtivo 				    enum('S', 'N') default 'S',
	primary key (idOrcamento),
  	foreign key (idManutencaoOrcamento) references manutencao (idManutencao),
    foreign key (idManutencaoBemPatrimonial) references manutencaobempatrimonial (idManutencaoBemPatrimonial),
   	foreign key (idBemPatrimonial) references bempatrimonial (idBemPatrimonial),
  	foreign key (idFornecedor)  references fornecedor (idFornecedor))
    default charset=utf8;

create table OrdemServico (
	idOrdemServico 			int not null auto_increment,
	idOrcamento				int not null,
	idManutencao    		int not null,
	idMotivoCancelamento	int,
	idFornecedor			int not null,
	dtIncioOrdemServico		date not null,
	dtFimOrdemServico		date not null,
	idMotivoCancelamentoOrdemServico	int,
	dtCancelamentoOrdemServico			date,
    idBemPatrimonial		int not null,	
	primary key (idOrdemServico),
	foreign key (idOrcamento) references orcamento (idOrcamento),
	foreign key (idManutencao) references manutencao (idManutencao),
	foreign key (idBemPatrimonial) references bempatrimonial (idBemPatrimonial),
	foreign key (idMotivoCancelamento) references motivocancelamento (idMotivoCancelamento),
	foreign key (idFornecedor) references fornecedor (idFornecedor))
	default charset=utf8;

Create table AtrasoOrdemServico (
	idAtrasoOrdemServico				int not null auto_increment,
	idOrdemServico						int,
	idMotivoAtraso						int,
	dtPrevistaEntregaAtrasoOrdemServico	date,
	nmPessoaInformaAtrasoOrdemServico	char(60) not null,
	dtRegistroAtrasoOrdemServico		date,
	primary key (idAtrasoOrdemServico),
	foreign key (idOrdemServico) references ordemservico (idOrdemServico),
	foreign key (idMotivoAtraso) references motivoatraso (idMotivoAtraso))
	default charset=utf8;

Create table Usuario (
	idLogonUsuario					char(30) not null,
   	idMatriculaUsuario				int unique not null,
   	nmFuncionarioUsuario			char(60) not null,
	idPerfilUsuario				int not null,
	idSenhaUsuario				char(30) not null,
    primary key (idLogonUsuario))
    default charset=utf8;

Create table AvaliarFornecedor (
	idAvaliarFornecedor				int not null auto_increment,
	idOrdemServico					int not null,
	idFornecedor					int not null,
	idQuesitoAvaliacao				int not null,
	dtAvaliacaoAvaliarFornecedor	date not null,
	idMatriculaUsuario				int not null,
	idNotaAvaliarFornecedor			int not null,
	primary key (idAvaliarFornecedor),
	foreign key (idOrdemServico) references ordemservico (idOrdemServico),
	foreign key (idFornecedor) references fornecedor (idFornecedor),
	foreign key (idMatriculaUsuario) references usuario (idMatriculaUsuario),
	foreign key (idQuesitoAvaliacao) references quesitoavaliacao (idQuesitoAvaliacao))
	default charset=utf8;
	

create table Garantia(
	idGarantia			int not null auto_increment,
	idOrdemServico		int not null,
   	idBemPatrimonial	int not null,
   	dtIncioGarantia		date not null,
   	dtFimGarantia		date not null,
   primary key (idGarantia),
   foreign key (idOrdemServico) references ordemservico (idOrdemServico),
   foreign key (idBemPatrimonial) references bempatrimonial (idBemPatrimonial))
   default charset=utf8;

