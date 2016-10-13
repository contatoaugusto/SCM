/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.scm.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author prohgy
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomanutencao.findAll", query = "SELECT t FROM Tipomanutencao t"),
    @NamedQuery(name = "Tipomanutencao.findByIdTipoManutencao", query = "SELECT t FROM Tipomanutencao t WHERE t.idTipoManutencao = :idTipoManutencao"),
    @NamedQuery(name = "Tipomanutencao.findByTxTipoManutencao", query = "SELECT t FROM Tipomanutencao t WHERE t.txTipoManutencao = :txTipoManutencao"),
    @NamedQuery(name = "Tipomanutencao.findByIcAtivo", query = "SELECT t FROM Tipomanutencao t WHERE t.icAtivo = :icAtivo")})
public class Tipomanutencao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idTipoManutencao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String txTipoManutencao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    private String icAtivo;

    public Tipomanutencao() {
    }

    public Tipomanutencao(Integer idTipoManutencao) {
        this.idTipoManutencao = idTipoManutencao;
    }

    public Tipomanutencao(Integer idTipoManutencao, String txTipoManutencao, String icAtivo) {
        this.idTipoManutencao = idTipoManutencao;
        this.txTipoManutencao = txTipoManutencao;
        this.icAtivo = icAtivo;
    }

    public Integer getIdTipoManutencao() {
        return idTipoManutencao;
    }

    public void setIdTipoManutencao(Integer idTipoManutencao) {
        this.idTipoManutencao = idTipoManutencao;
    }

    public String getTxTipoManutencao() {
        return txTipoManutencao;
    }

    public void setTxTipoManutencao(String txTipoManutencao) {
        this.txTipoManutencao = txTipoManutencao;
    }

    public String getIcAtivo() {
        return icAtivo;
    }

    public void setIcAtivo(String icAtivo) {
        this.icAtivo = icAtivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoManutencao != null ? idTipoManutencao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomanutencao)) {
            return false;
        }
        Tipomanutencao other = (Tipomanutencao) object;
        if ((this.idTipoManutencao == null && other.idTipoManutencao != null) || (this.idTipoManutencao != null && !this.idTipoManutencao.equals(other.idTipoManutencao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.scm.entity.Tipomanutencao[ idTipoManutencao=" + idTipoManutencao + " ]";
    }
    
}
