CREATE DATABASE `tcc_scm` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `atrasoordemservico` (
  `idAtrasoOrdemServico` int(11) NOT NULL AUTO_INCREMENT,
  `idOrdemServico` int(11) DEFAULT NULL,
  `idMotivoAtraso` int(11) DEFAULT NULL,
  `dtPrevistaEntregaAtrasoOrdemServico` date DEFAULT NULL,
  `nmPessoaInformaAtrasoOrdemServico` char(60) NOT NULL,
  `dtRegistroAtrasoOrdemServico` date DEFAULT NULL,
  PRIMARY KEY (`idAtrasoOrdemServico`),
  KEY `idOrdemServico` (`idOrdemServico`),
  KEY `idMotivoAtraso` (`idMotivoAtraso`),
  CONSTRAINT `atrasoordemservico_ibfk_1` FOREIGN KEY (`idOrdemServico`) REFERENCES `ordemservico` (`idOrdemServico`),
  CONSTRAINT `atrasoordemservico_ibfk_2` FOREIGN KEY (`idMotivoAtraso`) REFERENCES `motivoatraso` (`idMotivoAtraso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `avaliarfornecedor` (
  `idAvaliarFornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `idOrdemServico` int(11) NOT NULL,
  `idFornecedor` int(11) NOT NULL,
  `idQuesitoAvaliacao` int(11) NOT NULL,
  `dtAvaliacaoAvaliarFornecedor` date NOT NULL,
  `idMatriculaUsuario` int(11) NOT NULL,
  `idNotaAvaliarFornecedor` int(11) NOT NULL,
  PRIMARY KEY (`idAvaliarFornecedor`),
  KEY `idOrdemServico` (`idOrdemServico`),
  KEY `idFornecedor` (`idFornecedor`),
  KEY `idMatriculaUsuario` (`idMatriculaUsuario`),
  KEY `idQuesitoAvaliacao` (`idQuesitoAvaliacao`),
  CONSTRAINT `avaliarfornecedor_ibfk_1` FOREIGN KEY (`idOrdemServico`) REFERENCES `ordemservico` (`idOrdemServico`),
  CONSTRAINT `avaliarfornecedor_ibfk_2` FOREIGN KEY (`idFornecedor`) REFERENCES `fornecedor` (`idFornecedor`),
  CONSTRAINT `avaliarfornecedor_ibfk_3` FOREIGN KEY (`idMatriculaUsuario`) REFERENCES `usuario` (`idMatriculaUsuario`),
  CONSTRAINT `avaliarfornecedor_ibfk_4` FOREIGN KEY (`idQuesitoAvaliacao`) REFERENCES `quesitoavaliacao` (`idQuesitoAvaliacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bempatrimonial` (
  `idBemPatrimonial` int(11) NOT NULL AUTO_INCREMENT,
  `txNipBemPatrimonial` varchar(10) NOT NULL,
  `nmBemPatrimonial` varchar(200) DEFAULT NULL,
  `dtGarantiaBemPatrimonial` date DEFAULT NULL,
  `dtManutencaoBemPatrimonial` date DEFAULT NULL,
  `idAtivoBemPatrimonial` enum('S','N') DEFAULT 'S',
  `nmResponsavelBemParimonial` varchar(100) DEFAULT NULL,
  `telResponsavelBemPatrimonial` char(15) DEFAULT NULL,
  PRIMARY KEY (`idBemPatrimonial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bempatrimonialtipoequipamento` (
  `idBemPatrimonialTipoEquipamento` int(11) NOT NULL AUTO_INCREMENT,
  `idBemPatrimonial` int(11) NOT NULL,
  `idTipoEquipamento` int(11) DEFAULT NULL,
  PRIMARY KEY (`idBemPatrimonialTipoEquipamento`),
  KEY `idBemPatrimonial` (`idBemPatrimonial`),
  KEY `idTipoEquipamento` (`idTipoEquipamento`),
  CONSTRAINT `bempatrimonialtipoequipamento_ibfk_1` FOREIGN KEY (`idBemPatrimonial`) REFERENCES `bempatrimonial` (`idBemPatrimonial`),
  CONSTRAINT `bempatrimonialtipoequipamento_ibfk_2` FOREIGN KEY (`idTipoEquipamento`) REFERENCES `tipoequipamento` (`idTipoEquipamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fornecedor` (
  `idFornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `idCpfCnpjFornecedor` enum('1','2') NOT NULL,
  `nrCpfCnpjFornecedor` varchar(14) NOT NULL,
  `nmRazaoSocialFornecedor` varchar(150) NOT NULL,
  `nmFantasiaFornecedor` varchar(150) NOT NULL,
  `txEnderecoFornecedor` varchar(100) NOT NULL,
  `txComplementoFornecedor` varchar(100) DEFAULT NULL,
  `txCidadeFornecedor` varchar(40) DEFAULT NULL,
  `txUfFornecedor` varchar(2) DEFAULT NULL,
  `nrCepFornecedor` int(11) DEFAULT NULL,
  `nrTelefone1Fornecedor` char(20) DEFAULT NULL,
  `nTelefone2Fornecedor` char(20) DEFAULT NULL,
  `nrFaxFornecedor` char(20) DEFAULT NULL,
  `txEmailFornecedor` varchar(60) DEFAULT NULL,
  `idExcluidoFornecedor` enum('S','N') DEFAULT 'N',
  PRIMARY KEY (`idFornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `fornecedortipoequipamento` (
  `idFornecedorTipoEquipamento` int(11) NOT NULL AUTO_INCREMENT,
  `idFornecedor` int(11) NOT NULL,
  `idTipoEquipamento` int(11) NOT NULL,
  `idAtivoFornecedor` enum('S','N') DEFAULT 'S',
  `idExcluidoFornecedor` enum('S','N') DEFAULT 'N',
  PRIMARY KEY (`idFornecedorTipoEquipamento`),
  KEY `idFornecedor` (`idFornecedor`),
  KEY `idTipoEquipamento` (`idTipoEquipamento`),
  CONSTRAINT `fornecedortipoequipamento_ibfk_1` FOREIGN KEY (`idFornecedor`) REFERENCES `fornecedor` (`idFornecedor`),
  CONSTRAINT `fornecedortipoequipamento_ibfk_2` FOREIGN KEY (`idTipoEquipamento`) REFERENCES `tipoequipamento` (`idTipoEquipamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `garantia` (
  `idGarantia` int(11) NOT NULL AUTO_INCREMENT,
  `idOrdemServico` int(11) NOT NULL,
  `idBemPatrimonial` int(11) NOT NULL,
  `dtIncioGarantia` date NOT NULL,
  `dtFimGarantia` date NOT NULL,
  PRIMARY KEY (`idGarantia`),
  KEY `idOrdemServico` (`idOrdemServico`),
  KEY `idBemPatrimonial` (`idBemPatrimonial`),
  CONSTRAINT `garantia_ibfk_1` FOREIGN KEY (`idOrdemServico`) REFERENCES `ordemservico` (`idOrdemServico`),
  CONSTRAINT `garantia_ibfk_2` FOREIGN KEY (`idBemPatrimonial`) REFERENCES `bempatrimonial` (`idBemPatrimonial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `manutencao` (
  `idManutencao` int(11) NOT NULL AUTO_INCREMENT,
  `idSgiManutencao` int(11) NOT NULL,
  `dtSolicitacaoManutencao` date DEFAULT NULL,
  `idMotivoCancelamento` int(11) DEFAULT NULL,
  `dtCancelamentoManutencao` date DEFAULT NULL,
  PRIMARY KEY (`idManutencao`),
  KEY `idSgiManutencao` (`idSgiManutencao`),
  KEY `idMotivoCancelamento` (`idMotivoCancelamento`),
  CONSTRAINT `manutencao_ibfk_1` FOREIGN KEY (`idSgiManutencao`) REFERENCES `solicitacao` (`idSolicitacao`),
  CONSTRAINT `manutencao_ibfk_2` FOREIGN KEY (`idMotivoCancelamento`) REFERENCES `motivocancelamento` (`idMotivoCancelamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `manutencaobempatrimonial` (
  `idManutencaoBemPatrimonial` int(11) NOT NULL AUTO_INCREMENT,
  `idManutencao` int(11) NOT NULL,
  `idBemPatrimonial` int(11) NOT NULL,
  `idTipoOcorrencia` int(11) NOT NULL,
  PRIMARY KEY (`idManutencaoBemPatrimonial`),
  KEY `idManutencao` (`idManutencao`),
  KEY `idBemPatrimonial` (`idBemPatrimonial`),
  KEY `idTipoOcorrencia` (`idTipoOcorrencia`),
  CONSTRAINT `manutencaobempatrimonial_ibfk_1` FOREIGN KEY (`idManutencao`) REFERENCES `manutencao` (`idManutencao`),
  CONSTRAINT `manutencaobempatrimonial_ibfk_2` FOREIGN KEY (`idBemPatrimonial`) REFERENCES `bempatrimonial` (`idBemPatrimonial`),
  CONSTRAINT `manutencaobempatrimonial_ibfk_3` FOREIGN KEY (`idTipoOcorrencia`) REFERENCES `tipoocorrencia` (`idTipoOcorrencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `motivoatraso` (
  `idMotivoAtraso` int(11) NOT NULL AUTO_INCREMENT,
  `txMotivoAtraso` varchar(100) NOT NULL,
  `idAtivoMotivoAtraso` enum('S','N') DEFAULT 'S',
  `idExcluidoMotivoAtraso` enum('S','N') DEFAULT 'N',
  PRIMARY KEY (`idMotivoAtraso`),
  UNIQUE KEY `txMotivoAtraso` (`txMotivoAtraso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `motivocancelamento` (
  `idMotivoCancelamento` int(11) NOT NULL AUTO_INCREMENT,
  `txMotivoCancelamento` varchar(100) NOT NULL,
  `idAtivoMotivoCancelamento` enum('S','N') DEFAULT 'S',
  `idExcluidoMotivoCancelamento` enum('S','N') DEFAULT 'N',
  PRIMARY KEY (`idMotivoCancelamento`),
  UNIQUE KEY `txMotivoCancelamento` (`txMotivoCancelamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `movimentacaopatrimonial` (
  `idMovimentacaoPatrimonial` int(11) NOT NULL AUTO_INCREMENT,
  `idBemPatrimonial` int(11) NOT NULL,
  `dtInicialMovimentacaoPatrimonial` date NOT NULL,
  `dtFinalMovimentacaoPatrimonial` date DEFAULT NULL,
  `nmCampiMovimentacaoPatrimonial` varchar(100) NOT NULL,
  `txEnderecoMovimentacaoPatrimonial` varchar(100) NOT NULL,
  PRIMARY KEY (`idMovimentacaoPatrimonial`),
  KEY `idBemPatrimonial` (`idBemPatrimonial`),
  CONSTRAINT `movimentacaopatrimonial_ibfk_1` FOREIGN KEY (`idBemPatrimonial`) REFERENCES `bempatrimonial` (`idBemPatrimonial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orcamento` (
  `idOrcamento` int(11) NOT NULL AUTO_INCREMENT,
  `idManutencaoOrcamento` int(11) NOT NULL,
  `idManutencaoBemPatrimonial` int(11) NOT NULL,
  `idBemPatrimonial` int(11) NOT NULL,
  `idFornecedor` int(11) NOT NULL,
  `dtOrcamento` date NOT NULL,
  `vlOrcamento` decimal(15,2) NOT NULL,
  `vlDesconto` decimal(10,2) DEFAULT NULL,
  `qtDiaEntregaOrcamento` int(11) DEFAULT NULL,
  `qtDiaPagamentoOrcamento` int(11) DEFAULT NULL,
  `qtDiaGarantiaOrcamento` int(11) DEFAULT NULL,
  `qtDiaPrazoOrcamento` int(11) DEFAULT NULL,
  `idAtivoOrcamento` enum('S','N') DEFAULT 'S',
  `idExcluidoOrcamento` enum('S','N') DEFAULT 'N',
  PRIMARY KEY (`idOrcamento`),
  KEY `idManutencaoOrcamento` (`idManutencaoOrcamento`),
  KEY `idManutencaoBemPatrimonial` (`idManutencaoBemPatrimonial`),
  KEY `idBemPatrimonial` (`idBemPatrimonial`),
  KEY `idFornecedor` (`idFornecedor`),
  CONSTRAINT `orcamento_ibfk_1` FOREIGN KEY (`idManutencaoOrcamento`) REFERENCES `manutencao` (`idManutencao`),
  CONSTRAINT `orcamento_ibfk_2` FOREIGN KEY (`idManutencaoBemPatrimonial`) REFERENCES `manutencaobempatrimonial` (`idManutencaoBemPatrimonial`),
  CONSTRAINT `orcamento_ibfk_3` FOREIGN KEY (`idBemPatrimonial`) REFERENCES `bempatrimonial` (`idBemPatrimonial`),
  CONSTRAINT `orcamento_ibfk_4` FOREIGN KEY (`idFornecedor`) REFERENCES `fornecedor` (`idFornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ordemservico` (
  `idOrdemServico` int(11) NOT NULL AUTO_INCREMENT,
  `idOrcamento` int(11) NOT NULL,
  `idManutencao` int(11) NOT NULL,
  `idMotivoCancelamento` int(11) NOT NULL,
  `idFornecedor` int(11) NOT NULL,
  `dtIncioOrdemServico` date NOT NULL,
  `dtFimOrdemServico` date NOT NULL,
  `idMotivoCancelamentoOrdemServico` int(11) DEFAULT NULL,
  `dtCancelamentoOrdemServico` date DEFAULT NULL,
  PRIMARY KEY (`idOrdemServico`),
  KEY `idOrcamento` (`idOrcamento`),
  KEY `idManutencao` (`idManutencao`),
  KEY `idMotivoCancelamento` (`idMotivoCancelamento`),
  KEY `idFornecedor` (`idFornecedor`),
  CONSTRAINT `ordemservico_ibfk_1` FOREIGN KEY (`idOrcamento`) REFERENCES `orcamento` (`idOrcamento`),
  CONSTRAINT `ordemservico_ibfk_2` FOREIGN KEY (`idManutencao`) REFERENCES `manutencao` (`idManutencao`),
  CONSTRAINT `ordemservico_ibfk_3` FOREIGN KEY (`idMotivoCancelamento`) REFERENCES `bempatrimonial` (`idBemPatrimonial`),
  CONSTRAINT `ordemservico_ibfk_4` FOREIGN KEY (`idMotivoCancelamento`) REFERENCES `motivocancelamento` (`idMotivoCancelamento`),
  CONSTRAINT `ordemservico_ibfk_5` FOREIGN KEY (`idFornecedor`) REFERENCES `fornecedor` (`idFornecedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `quesitoavaliacao` (
  `idQuesitoAvaliacao` int(11) NOT NULL AUTO_INCREMENT,
  `txQuesitoAvaliacao` varchar(100) NOT NULL,
  `idAtivoQuesitoAvaliacao` enum('S','N') DEFAULT 'S',
  `idExcluidoQuesitoAvaliacao` enum('S','N') DEFAULT 'N',
  PRIMARY KEY (`idQuesitoAvaliacao`),
  UNIQUE KEY `txQuesitoAvaliacao` (`txQuesitoAvaliacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `solicitacao` (
  `idSolicitacao` int(11) NOT NULL AUTO_INCREMENT,
  `cdSgiSolicitacao` char(15) NOT NULL,
  `nrMatriculaFuncionarioSolicitacao` int(11) DEFAULT NULL,
  `txNomeFuncionarioSolicitacao` varchar(60) NOT NULL,
  `dtSolicitacaoSgi` date NOT NULL,
  `txSolicitacaoSgi` varchar(10000) NOT NULL,
  PRIMARY KEY (`idSolicitacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tipoequipamento` (
  `idTipoEquipamento` int(11) NOT NULL AUTO_INCREMENT,
  `txTipoEquipamento` varchar(100) NOT NULL,
  `idAtivoTipoEquipamento` enum('S','N') DEFAULT 'S',
  `idExcluidoTipoEquipamento` enum('S','N') DEFAULT 'N',
  PRIMARY KEY (`idTipoEquipamento`),
  UNIQUE KEY `txTipoEquipamento` (`txTipoEquipamento`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

CREATE TABLE `tipomanutencao` (
  `idTipoManutencao` int(11) NOT NULL AUTO_INCREMENT,
  `txTipoManutencao` varchar(100) NOT NULL,
  `idAtivoTipoManutencao` enum('S','N') DEFAULT 'S',
  `idExcluidoTipoManutencao` enum('S','N') DEFAULT 'N',
  PRIMARY KEY (`idTipoManutencao`),
  UNIQUE KEY `txTipoManutencao` (`txTipoManutencao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tipoocorrencia` (
  `idTipoOcorrencia` int(11) NOT NULL AUTO_INCREMENT,
  `txTipoOcorrencia` varchar(100) NOT NULL,
  `idAtivoTipoOcorrencia` enum('S','N') DEFAULT 'S',
  `idExcluidoTipoOcorrencia` enum('S','N') DEFAULT 'N',
  PRIMARY KEY (`idTipoOcorrencia`),
  UNIQUE KEY `txTipoOcorrencia` (`txTipoOcorrencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usuario` (
  `idLogonUsuario` char(30) NOT NULL,
  `idMatriculaUsuario` int(11) NOT NULL,
  `nmFuncionarioUsuario` char(60) NOT NULL,
  `idPerfilUsuario` int(11) NOT NULL,
  `idSenhaUsuario` char(30) NOT NULL,
  PRIMARY KEY (`idLogonUsuario`),
  UNIQUE KEY `idMatriculaUsuario` (`idMatriculaUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



