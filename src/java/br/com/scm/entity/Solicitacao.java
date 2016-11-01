/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scm.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "solicitacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitacao.findAll", query = "SELECT s FROM Solicitacao s"),
    @NamedQuery(name = "Solicitacao.findByIdSolicitacao", query = "SELECT s FROM Solicitacao s WHERE s.idSolicitacao = :idSolicitacao"),
    @NamedQuery(name = "Solicitacao.findByCdSgiSolicitacao", query = "SELECT s FROM Solicitacao s WHERE s.cdSgiSolicitacao = :cdSgiSolicitacao"),
    @NamedQuery(name = "Solicitacao.findByNrMatriculaFuncionarioSolicitacao", query = "SELECT s FROM Solicitacao s WHERE s.nrMatriculaFuncionarioSolicitacao = :nrMatriculaFuncionarioSolicitacao"),
    @NamedQuery(name = "Solicitacao.findByTxNomeFuncionarioSolicitacao", query = "SELECT s FROM Solicitacao s WHERE s.txNomeFuncionarioSolicitacao = :txNomeFuncionarioSolicitacao"),
    @NamedQuery(name = "Solicitacao.findByDtSolicitacaoSgi", query = "SELECT s FROM Solicitacao s WHERE s.dtSolicitacaoSgi = :dtSolicitacaoSgi"),
    @NamedQuery(name = "Solicitacao.findByTxSolicitacaoSgi", query = "SELECT s FROM Solicitacao s WHERE s.txSolicitacaoSgi = :txSolicitacaoSgi")})
public class Solicitacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSolicitacao")
    private Integer idSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cdSgiSolicitacao")
    private String cdSgiSolicitacao;
    @Column(name = "nrMatriculaFuncionarioSolicitacao")
    private Integer nrMatriculaFuncionarioSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "txNomeFuncionarioSolicitacao")
    private String txNomeFuncionarioSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtSolicitacaoSgi")
    @Temporal(TemporalType.DATE)
    private Date dtSolicitacaoSgi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10000)
    @Column(name = "txSolicitacaoSgi")
    private String txSolicitacaoSgi;

    public Solicitacao() {
    }

    public Solicitacao(Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Solicitacao(Integer idSolicitacao, String cdSgiSolicitacao, String txNomeFuncionarioSolicitacao, Date dtSolicitacaoSgi, String txSolicitacaoSgi) {
        this.idSolicitacao = idSolicitacao;
        this.cdSgiSolicitacao = cdSgiSolicitacao;
        this.txNomeFuncionarioSolicitacao = txNomeFuncionarioSolicitacao;
        this.dtSolicitacaoSgi = dtSolicitacaoSgi;
        this.txSolicitacaoSgi = txSolicitacaoSgi;
    }

    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getCdSgiSolicitacao() {
        return cdSgiSolicitacao;
    }

    public void setCdSgiSolicitacao(String cdSgiSolicitacao) {
        this.cdSgiSolicitacao = cdSgiSolicitacao;
    }

    public Integer getNrMatriculaFuncionarioSolicitacao() {
        return nrMatriculaFuncionarioSolicitacao;
    }

    public void setNrMatriculaFuncionarioSolicitacao(Integer nrMatriculaFuncionarioSolicitacao) {
        this.nrMatriculaFuncionarioSolicitacao = nrMatriculaFuncionarioSolicitacao;
    }

    public String getTxNomeFuncionarioSolicitacao() {
        return txNomeFuncionarioSolicitacao;
    }

    public void setTxNomeFuncionarioSolicitacao(String txNomeFuncionarioSolicitacao) {
        this.txNomeFuncionarioSolicitacao = txNomeFuncionarioSolicitacao;
    }

    public Date getDtSolicitacaoSgi() {
        return dtSolicitacaoSgi;
    }

    public void setDtSolicitacaoSgi(Date dtSolicitacaoSgi) {
        this.dtSolicitacaoSgi = dtSolicitacaoSgi;
    }

    public String getTxSolicitacaoSgi() {
        return txSolicitacaoSgi;
    }

    public void setTxSolicitacaoSgi(String txSolicitacaoSgi) {
        this.txSolicitacaoSgi = txSolicitacaoSgi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitacao != null ? idSolicitacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitacao)) {
            return false;
        }
        Solicitacao other = (Solicitacao) object;
        if ((this.idSolicitacao == null && other.idSolicitacao != null) || (this.idSolicitacao != null && !this.idSolicitacao.equals(other.idSolicitacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.scm.entity.Solicitacao[ idSolicitacao=" + idSolicitacao + " ]";
    }
    
}
